package org.batch.facade;

import java.util.List;

import org.batch.model.entity.MProduct;
import org.batch.model.entity.MUser;
import org.batch.parallel.SalesParallel;
import org.batch.service.ProductsService;
import org.batch.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummySalesDataFacade {
  
  @Autowired
  private UsersService usersService;
  @Autowired
  private ProductsService productsService;
  @Autowired
  private SalesParallel salesParallel;

  public void createDummySalesData() {
    List<MUser> users = usersService.getUsers();
    List<MProduct> product = productsService.getProducts();
    salesParallel.createDummySalesParallel(users, product);
  }
}
