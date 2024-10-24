package com.rvo.schoolcrudapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvo.schoolcrudapi.model.User;
import com.rvo.schoolcrudapi.repository.IUserRepository;
import com.rvo.schoolcrudapi.security.CustomUserDetails;

import lombok.AllArgsConstructor;

// CustomUserDetailsService finds Users by searching in the database and
// generates CustomUserDetails (Spring Security credentials) for the user found
// in the database

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private IUserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    return new CustomUserDetails(user);
  }

}
