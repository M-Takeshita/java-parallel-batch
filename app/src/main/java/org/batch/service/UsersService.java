package org.batch.service;

import java.util.List;

import org.batch.model.entity.MUser;

public interface UsersService {
  public List<MUser> getUsers();
}