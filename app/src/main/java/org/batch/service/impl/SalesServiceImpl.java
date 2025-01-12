package org.batch.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.batch.model.entity.MProduct;
import org.batch.model.entity.MUser;
import org.batch.model.entity.TSale;
import org.batch.model.repository.TSaleRepository;
import org.batch.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl implements SalesService {

  @Autowired
  private TSaleRepository tSaleRepository;
  
  @Override
  public List<TSale> getSalesData() {
    return tSaleRepository.findAll();
  }
  
  @Override
  public List<TSale> createDummySales(List<MUser> users, List<MProduct> products) {
    List<TSale> saveItems = new ArrayList<>();
    System.out.println("登録開始 対象ユーザー数:" + users.size());

    for(MUser u: users) {
      // ユーザーごとに商品データをシャッフルしランダム数分のデータを作成
      List<MProduct> shuffleProducts = new ArrayList<>(products);
      int index = new Random().nextInt(shuffleProducts.size());
      System.out.println("登録index:" + index);
      for (int i = 0; i < index; i++) {
        MProduct product = shuffleProducts.get(i);
        
        int quantity = new Random().nextInt(10) + 1;
        BigDecimal totalAmount = product.getUnitAmount().multiply(BigDecimal.valueOf(quantity));

        TSale saveItem = new TSale(); 
        saveItem.setUserId(u.getId());
        saveItem.setProductId(product.getId());
        saveItem.setQuantity(quantity);
        saveItem.setUnitAmount(product.getUnitAmount());
        saveItem.setTotalAmount(totalAmount);
        saveItem.setTaxAmount(
          totalAmount.multiply(
            product.getTaxRate().divide(BigDecimal.valueOf(100L))
          )
        );
        // TODO: 支払い方法は一旦適当なので1固定
        saveItem.setPaymentMethodId(1);
        saveItem.setStatus("完了");
        // TODO: ストア情報は一旦適当なので1固定
        saveItem.setStoreId(1);

        saveItems.add(saveItem);
      }
    }
    List<TSale> savedItems = tSaleRepository.saveAll(saveItems);
    System.out.println("登録完了 登録件数:" + savedItems.size());
    
    return savedItems;
  }
}
