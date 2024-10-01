package com.rvo.schoolcrudapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvo.schoolcrudapi.model.Authority;

public interface IAuthorityRepository extends JpaRepository<Authority, Long> {

  public Authority findByAuthorityName(String authorityName);
}
