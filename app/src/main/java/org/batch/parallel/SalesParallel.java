package org.batch.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.batch.model.entity.MProduct;
import org.batch.model.entity.MUser;
import org.batch.model.entity.TSale;
import org.batch.service.SalesService;
import org.common.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesParallel {
  private final int parallelNum = 5;
  private final int parallelUsersNum = 20;

  @Autowired
  private SalesService salesService;

  public void createDummySalesParallel(List<MUser> users, List<MProduct> products) {
    List<List<MUser>> parallelUsers = CollectionUtils.chunk(users, parallelUsersNum);

    ExecutorService exec = Executors.newFixedThreadPool(parallelNum);
    
    List<Future<List<TSale>>> futures = new ArrayList<>();
    parallelUsers.forEach(u -> {
      futures.add(exec.submit(() -> salesService.createDummySales(users, products)));
    });

    // TODO: エラーハンドリングは一旦適当
    try {
      futures.forEach(future -> {
        try {
          List<TSale> sales = future.get();
          System.out.println("登録完了。登録件数:" + sales.size());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }
      });
    } catch (Exception e) {
      System.err.println(e);
    } finally {
      exec.shutdown();
    }
  }
}
