package com.demo;

import com.demo.dao.ScaTransactionRepository;
import com.demo.model.ScaTransaction;
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
 * Created by hewei on 2018/11/13.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ElasticSearchTests{

    @Autowired
    private ScaTransactionRepository scaTransactionRepository;

    private static int threadNum = 10;

    private static int pageSize = 100;

   // @Test
    public void uploadTransaction() throws Exception {
        //scaTransactionRepository.deleteScaTransaction();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        ExecutorService pool = Executors.newFixedThreadPool(threadNum);
        List<Callable<String>> callers = new ArrayList<>();
        Integer count = scaTransactionRepository.countScaTransaction();
        for (int i = 0; i <= count / pageSize; i++) {
            callers.add(new UploadScaTransaction(atomicInteger.getAndAdd(1)));
        }
        pool.invokeAll(callers);
        pool.shutdown();
    }


    public class UploadScaTransaction implements Callable<String> {

        private Integer pageNum;

        public UploadScaTransaction(Integer pageNum) {
            this.pageNum = pageNum;
        }

        @Override
        public String call() throws Exception {
            try {
                uploadScaTransaction(this.pageNum);
                return  "";
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(this.pageNum + "失败");
                uploadScaTransaction(this.pageNum);
                return "failed";
            }
        }

        private void uploadScaTransaction(Integer pageNum){
            List<ScaTransaction> scaTransactionList = scaTransactionRepository.findAll(pageNum,pageSize);
            scaTransactionRepository.bulkScaTransaction(scaTransactionList);
            System.out.println(this.pageNum + "完成");
        }
    }
}
