package com.rvo.schoolcrudapi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvo.schoolcrudapi.model.Authority;
import com.rvo.schoolcrudapi.model.User;
import com.rvo.schoolcrudapi.repository.IAuthorityRepository;
import com.rvo.schoolcrudapi.repository.IUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

  private IUserRepository userRepository;

  private IAuthorityRepository authorityRepository;

  @Override
  @Transactional
  public void createUser(User user) {
    userRepository.save(user);
    System.out.println("New User created..." + user);
  }

  @Transactional
  public void createUserWithAuthorities(User user, List<String> authorities) {
    Set<Authority> authoritiesSet = new HashSet<>();
    for (String authorityName : authorities) {
      Authority authority = authorityRepository.findByAuthorityName(authorityName.toLowerCase());
      if (authority != null)
        authoritiesSet.add(authority);
    }
    user.setAuthorities(authoritiesSet);
    this.createUser(user);
  }

  @Override
  @Transactional
  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
    System.out.println("New User deleted..." + id);
  }
}
