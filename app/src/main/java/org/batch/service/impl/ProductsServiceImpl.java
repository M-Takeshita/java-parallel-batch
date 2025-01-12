package org.batch.service.impl;

import java.util.List;

import org.batch.model.entity.MProduct;
import org.batch.model.repository.MProductRepository;
import org.batch.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsServiceImpl implements ProductsService {
  
  @Autowired
  private MProductRepository mProductRepository;

  @Override
  public List<MProduct> getProducts() {
    return mProductRepository.findAll();
  }
}
