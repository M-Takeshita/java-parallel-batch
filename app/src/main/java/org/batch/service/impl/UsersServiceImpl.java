package org.batch.service.impl;

import java.util.List;

import org.batch.model.entity.MUser;
import org.batch.model.repository.MUserRepository;
import org.batch.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
  
  @Autowired
  private MUserRepository mUserRepository;

  @Override
  public List<MUser> getUsers() {
    return mUserRepository.findAll();
  }
}
