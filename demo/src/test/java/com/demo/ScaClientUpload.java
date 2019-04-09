package com.demo;

import com.demo.config.EsUtils;
import com.demo.dao.ScaClientRepository;
import com.demo.model.ScaClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujian on 2018/12/24.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ScaClientUpload {

    @Autowired
    private ScaClientRepository scaClientRepository;

    private static int threadNum = 10;

    private static int pageSize = 100;

    //@Test
    public void uploadScaClient()throws Exception {
        // scaClientRepository.deleteScaClient();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        ExecutorService pool = Executors.newFixedThreadPool(threadNum);
        List<Callable<String>> callers = new ArrayList<>();
     //   Integer count = scaClientRepository.countScaClient();
        for (int i = 0; i <=2000000/pageSize; i++) {
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
        if(!scaClientList.isEmpty()){
            scaClientRepository.bulkScaClient(scaClientList);
        }
        System.out.println(pageNum+"完成");
    }

}
