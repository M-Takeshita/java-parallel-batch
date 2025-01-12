package org.batch.service;

import java.util.List;

import org.batch.model.entity.MProduct;
import org.batch.model.entity.MUser;
import org.batch.model.entity.TSale;

import jakarta.transaction.Transactional;

public interface SalesService {
  public List<TSale> getSalesData ();
  
  @Transactional
  public List<TSale> createDummySales(List<MUser> users, List<MProduct> products);
}