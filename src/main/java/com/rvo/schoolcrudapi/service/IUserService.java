package com.rvo.schoolcrudapi.service;

import java.util.List;

import com.rvo.schoolcrudapi.model.User;

public interface IUserService {

  public void createUser(User user);

  public void deleteUserById(Long id);

  public void createUserWithAuthorities(User user, List<String> authorities);

}
