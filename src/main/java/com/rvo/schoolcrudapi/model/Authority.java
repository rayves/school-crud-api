package com.rvo.schoolcrudapi.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
public class Authority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Setter(AccessLevel.NONE)
  private Long id;

  @Column(name = "authority_name", nullable = false, unique = true)
  private String authorityName;

  @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
  private Set<User> users = new HashSet<>();

  @Autowired
  public Authority(String authorityName) {
    this.authorityName = authorityName;
  }
}
