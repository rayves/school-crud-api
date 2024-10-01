package com.rvo.schoolcrudapi.service;

import com.rvo.schoolcrudapi.model.Authority;

public interface IAuthorityService {

  public void createAuthority(Authority authority);

  public void deleteAuthorityById(Long id);

  public Authority getAuthorityByName(String authorityName);

}
