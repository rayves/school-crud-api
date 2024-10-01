package com.rvo.schoolcrudapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvo.schoolcrudapi.model.User;

public interface IUserRepository extends JpaRepository<User, Long>{

}
