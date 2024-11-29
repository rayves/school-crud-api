package com.rvo.schoolcrudapi.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

  @Override
  @Transactional
  public void createUserWithAuthorities(User user, List<String> authoritiesNames) {
    System.out.println("Creating users with Authorities..." + user);

    Set<Authority> authoritiesSet = authoritiesNames.stream()
        .map(authorityName -> {
          Authority authority = authorityRepository.findByAuthorityName(authorityName);
          authority.addUser(user);
          return authority;
        })
        .collect(Collectors.toSet());

    user.setAuthorities(authoritiesSet);

    if (!user.getAuthorities().isEmpty()) {
      userRepository.save(user);
      System.out.println("New User created with authorities..." + user);
      ;
    }
  }

  @Override
  @Transactional
  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
    System.out.println("New User deleted..." + id);
  }
}
