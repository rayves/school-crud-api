package com.rvo.schoolcrudapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvo.schoolcrudapi.model.Authority;
import com.rvo.schoolcrudapi.repository.IAuthorityRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorityService implements IAuthorityService {

  private IAuthorityRepository authorityRepository;

  @Override
  @Transactional
  public void createAuthority(Authority authority) {
    authorityRepository.save(authority);
    System.out.println("Authorities created... " + authority);
  }

  @Override
  @Transactional
  public void deleteAuthorityById(Long id) {
    authorityRepository.deleteById(id);
    System.out.println("Authorities deleted... " + id);
  }

  @Override
  @Transactional(readOnly = true)
  public Authority getAuthorityByName(String authorityName) {
    Authority authority = authorityRepository.findByAuthorityName(authorityName);
    System.out.println("Authority found..." + authority);
    return authority;
  }

}
