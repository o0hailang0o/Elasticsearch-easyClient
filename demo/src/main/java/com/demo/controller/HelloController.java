package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.config.EsUtils;
import com.demo.dao.MysqlDao;
import com.demo.model.Column;
import com.demo.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liujian on 2019/1/4.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

     @Autowired
     private EsUtils esUtils;
     //@Value("${spring.datasource.url}")
     private String jdbcUrl = "sales_sca";
     @Value("${es_version}")
     private String esVersion;

     @PostMapping("/search")
     public PageResult search(@RequestBody Model model){
         ConditionString conditionString = new ConditionString(model);
         String json= conditionString.getDslQuery();
         String index = model.getIndex();
         String url = "/"+index+"-v"+esVersion+"/"+index+"/_search";
         JSONObject result = esUtils.get(url,json);
         ResultHandler resultHandler = new ResultHandler(model);
         return resultHandler.getResult(result);
     }

     @Autowired
     private MysqlDao mysqlDao;

     @GetMapping("/query/table-name")
     public List<String> listTable(){
        String baseName = jdbcUrl;
        return mysqlDao.listTableName(baseName);
     }

     @PostMapping("/build/mapping")
     public Result putMapping(String tableName){
         String baseName =jdbcUrl;
         List<Column> columnList = mysqlDao.getFieldList(tableName,baseName);
         String index = tableName.replace("_","-");
         String url = "/"+index+"-v"+esVersion;

         String mapping = "{\n" +
                 likeAnalyzerString()+"\n"+
                 " ,\"mappings\":{\n" +
                 "    \""+index+"\": {\n" +
                 "      \"properties\": {";

         for(int i=0;i<columnList.size();i++ ){
             Column column = columnList.get(i);
             String fieldName = column.getColumnName();
             String dateType = column.getDataType();
             if(i!=0){
                 mapping+=",";
             }
             mapping+="\""+BeanHump.underlineToCamel2(fieldName)+"\":"+mapType(dateType);
         }

         mapping+="      }\n" +
                 "    }\n" +
                 " }\n" +
                 "}";
         esUtils.put(url,mapping);
         return ResultUtils.success();
     }

     @DeleteMapping("/delete/data")
     public Result deleteData(String tableName){
         String index = tableName.replace("_","-");
         String url = "/"+index+"-v"+esVersion+"/"+index+"/_delete_by_query";
         String json="{\n" +
                 "\"query\": {\n" +
                 "\"match_all\": {}\n" +
                 "}\n" +
                 "}";
         esUtils.post(url,json);
         return ResultUtils.success();
     }

    @DeleteMapping("/delete/index")
    public Result deleteIndex(String tableName){
        String index = tableName.replace("_","-");
        String url = "/"+index+"-v"+esVersion;
        esUtils.delete(url);
        return ResultUtils.success();
    }


     @GetMapping("/query/field/info")
     public List<Column> getFieldList(@RequestParam String tableName){
         String baseName = jdbcUrl;
         List<Column> columns = mysqlDao.getFieldList(tableName,baseName);
         for(Column column : columns){
             column.setColumnName(BeanHump.underlineToCamel2(column.getColumnName()));
         }
         return columns;
     }

     private static final int pageSize = 100;

    private static int threadNum = 10;

     @PostMapping("/sync/es")
     public Result syncEs(String tableName,Long startId,Long endId)throws Exception{
         try {
             if(startId == null){
                 startId = mysqlDao.minId(tableName);
             }
             if(endId == null){
                 endId = mysqlDao.maxId(tableName);
             }
             ExecutorService pool = Executors.newFixedThreadPool(threadNum);
             List<Callable<String>> callers = new ArrayList<>();
             //由于我们都是自增id
             Long pageNum = startId;
             for (int i = 0; i <= (endId-startId) / pageSize; i++) {
                 callers.add(new UploadData(pageNum,tableName));
                 pageNum = pageNum+pageSize;
             }
             pool.invokeAll(callers);
             pool.shutdown();
         }catch (Exception e){
             e.printStackTrace();
             return ResultUtils.error();
         }
         return ResultUtils.success();
     }

     private class UploadData implements Callable<String>{

         private Long pageNum;
         private String tableName;

         public UploadData(Long pageNum,String tableName){
             this.pageNum = pageNum;
             this.tableName = tableName;
         }
         @Override
         public String call() throws Exception {
             try {
                 uploadData(this.pageNum,this.tableName);
                 return  "";
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println(this.pageNum + "失败");
                 uploadData(this.pageNum,this.tableName);
                 return "failed";
             }
         }

         private void uploadData(Long pageNum,String tableName){
             List<Map<String,Object>> results = mysqlDao.getDatas(tableName,pageNum,pageNum+pageSize);
             List<Map<String,String>> esMaps = new ArrayList<>();
             if(results!=null&&!results.isEmpty()){
                 for(Map<String,Object> map : results){
                     Map<String,String> esMap = new HashMap<>();
                     for(Map.Entry<String,Object> entry : map.entrySet()){
                         if(entry.getValue().getClass().equals(Timestamp.class)){
                             Timestamp timestamp = (Timestamp)entry.getValue();
                             esMap.put(BeanHump.underlineToCamel2(entry.getKey()),timestamp.getTime()+"");
                         }else{
                             esMap.put(BeanHump.underlineToCamel2(entry.getKey()),entry.getValue().toString());
                         }
                     }
                     esMaps.add(esMap);
                 }
                 String url = "_bulk";
                 String json = "";
                 for(Map<String,String> esMap:esMaps){
                     json += "{ \"index\" : { \"_index\" : \""+tableName.replace("_","-")+"-v"+esVersion+"\", \"_type\" : \""+tableName.replace("_","-")+"\", \"_id\" : \"" + esMap.get("id") + "\" } }\n";
                     json+= JSONObject.toJSONString(esMap)+"\n";
                 }
                 esUtils.post(url, json);
             }
             System.out.println(pageNum + "完成");
     }
    }


     private String mapType(String dataType){
         switch (dataType){
             case "tinyint":
                 return integerString();
             case "double":
                 return doubleString();
             case "int":
                 return longString();
             case  "bigint":
                 return longString();
             case "decimal":
                 return doubleString();
             case "varchar":
                 return stringString();
             case "text":
                 return stringString();
             case "datetime":
                 return dataString();
             default:
                throw new RuntimeException("not find data type，create mapping failed");
         }
     }

     private String longString(){
        return "{\n" +
                "    \"type\": \"long\"\n" +
                " }";
     }

    private String dataString(){
        return "{\n" +
                "    \"type\": \"date\"\n" +
                " }";
    }

     private String integerString(){
         return "{\n" +
                 "    \"type\": \"integer\"\n" +
                 "}";
     }

     private String doubleString(){
         return "{\n" +
                 "    \"type\": \"double\"\n" +
                 "}";
     }

     private String stringString(){
         return  "{\n" +
             "      \"type\": \"text\",\n" +
             "      \"fields\": {\n" +
             "        \"ikSmart\": {\n" +
             "            \"type\": \"text\",\n" +
             "             \"analyzer\": \"ik_smart\"\n" +
             "            },\n" +
             "            \"ikMaxWord\": {\n" +
             "               \"type\": \"text\",\n" +
             "               \"analyzer\": \"ik_max_word\"\n" +
             "            },\n" +
             "            \"keyword\": {\n" +
             "              \"type\": \"text\",\n" +
             "              \"analyzer\": \"keyword\",\n" +
             "              \"fielddata\": true\n" +
             "            }\n" +
             "          },\n" +
             "      \"analyzer\": \"my_english_analyzer\"\n" +
             "   }";
     }

     private String likeAnalyzerString(){
         return " \"settings\": {\n" +
                 "    \"analysis\": {\n" +
                 "      \"analyzer\": {\n" +
                 "        \"my_english_analyzer\": {\n" +
                 "          \"type\": \"standard\",\n" +
                 "          \"max_token_length\": 1,\n" +
                 "          \"stopwords\": \"_english_\"\n" +
                 "        }\n" +
                 "      }\n" +
                 "    }\n" +
                 "  }";
     }
}
