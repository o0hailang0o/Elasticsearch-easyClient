package com.demo;

import com.demo.config.EsUtils;
import com.demo.config.init.SpringContextHolder;
import com.demo.dao.ScaClientRepository;
import com.demo.dao.ScaTransactionRepository;
import com.demo.model.ScaClient;
import com.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class LbsTests {

   // @Autowired
    private ScaClientRepository scaClientRepository;

    private static int threadNum = 10;

    private static int pageSize = 100;

   // @Test
    public void uploadScaClient()throws Exception {
        // scaClientRepository.deleteScaClient();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        ExecutorService pool = Executors.newFixedThreadPool(threadNum);
        List<Callable<String>> callers = new ArrayList<>();
        Integer count = scaClientRepository.countScaClient();
        for (int i = 0; i <=count/pageSize; i++) {
            callers.add(new UploadScaClient(atomicInteger.getAndAdd(1)));
        }
        pool.invokeAll(callers);
        pool.shutdown();
    }

    private class UploadScaClient implements Callable<String> {

        private Integer pageNum;

        public UploadScaClient(Integer pageNum){
            this.pageNum = pageNum;
        }

        @Override
        public String call(){
            int page = this.pageNum;
            try {
                UploadScaClientCall(page);
                return "";
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(page+"失败");
                UploadScaClientCall(page);
                return "failed";
            }

        }
    }

    private void UploadScaClientCall(Integer pageNum){
        List<ScaClient> scaClientList = scaClientRepository.getScaClientList(pageNum,pageSize);
        scaClientRepository.bulkScaClient(scaClientList);
        System.out.println(pageNum+"完成");
    }

   // @Autowired
    private EsUtils esUtils;
   // @Test
    public void  test(){
        String idsString="447,1013,3291,4485,5294,6608,10176,10533,11083,11259,12244,14168,14967,15087,16582,16746,16972,17495,17927,18966,19363,21180,22092,22891,23101,23412,24042,24366,24512,25179,25848,26119,26745,27136,28462,29594,30799,31634,32236,33939,34562,35053,35201,36234,36318,36584,36673,37551,38645,39156,39774,39934,41670,42230,42233,43339,44495,44513,46005,46733,47535,47923,48162,48762,51099,52016,52220,54131,54242,55631,55736,55881,56107,56751,56755,57500,58395,60144,61977,62292,64702,65435,65509,66187,66299,67841,68301,68550,68961,70694,70698,71337,72344,72774,73079,73333,73941,75315,75792,81057,81372,81409,81559,83026,84344,84464,84836,86410,86875,87308,88718,90021,91985,93821,94241,95664,95891,96474,98261,98481,98585,99492,99532,100790,101737,102222,103071,103471,103588,103864,104223,104536,105554,105716,105768,105783,106433,107873,110355,110707,110759,110803,111408,111411,112539,112753,113779,114677,116784,116832,117185,117959,118051,118570,118886,119407,119468,121347,123153,124096,124172,125055,126210,126372,126974,127641,127737,128291,130943,130973,132460,133685,133884,133954,134263,134365,134753,135721,135796,135845,136033,136421,139009,140616,140881,140997,141189,141541,143048,143315,143834,144641,145460,145565,151078,151572,151917,152055,154111,157568,157762,158738,160452,162803,163359,163529,164327,164708,165733,167486,167594,168395,169257,172039,172941,173088,173791,174399,175540,175704,176263,178789,178942,179843,184499,185295,188012,188287,188513,189458,190643,190699,191330,192744,192808,192892,193144,195050,195123,195231,195910,195933,196188,198009,198269,198297,198445,199498,199824,199906,200902,200908,201037,201450,201792,202002,202059,202438,202527,202820,203013,203172,203331,203749,203956,203975,204016,204153,204329,204608,204691,204826,205030,205127,205429,205789,205927,206005,206227,206340,206558,206760,206804,206940,207397,207861,207898,208120";
        String[] ids = idsString.split(",");
        for(int i=0;i<ids.length;i++){
            try{
                esUtils.delete("/sca-transaction/sca-transaction/"+ids[i]);
            }catch (Exception e){
                continue;
            }

        }
    }

   // @Test
    public void  test1(){
        String idsString="601117,601142,601151,601180,601181,601189,601197,601201,601245,601250,601272,601933,601939,601948,601951,601966,601970,602031,602037,602038,602042,389426,389747,389761,389791,389806,390009,390074,390079,390088,390095,390102,390108,390124,390144,390147,390151,390157,390160,390162,390165,390172,390175,389935,389415,389424,389892,389904,389923,389937,389938,389939,389995";
        String[] ids = idsString.split(",");
        for(int i=0;i<ids.length;i++){
            try{
                esUtils.delete("/sca-client/sca-client/"+ids[i]);
            }catch (Exception e){
                continue;
            }
        }
    }

}
