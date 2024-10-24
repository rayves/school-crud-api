package com.rvo.schoolcrudapi.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Setter(AccessLevel.NONE)
  private Long id;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "enabled", nullable = false)
  private boolean enabled = true;

  // FetchType.EAGER as Lazy can cause LazyInitializationException if there is no
  // manual initialization of the User
  @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  @JoinTable(name = "user_authorities", // Join table to link users to authorities
      joinColumns = @JoinColumn(name = "user_id"), // Foreign key to User
      inverseJoinColumns = @JoinColumn(name = "authority_id") // Foreign key to Authority
  )
  @EqualsAndHashCode.Exclude
  private Set<Authority> authorities = new HashSet<>();

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(String username, String password, Set<Authority> authorities) {
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  /**
   * Add Authority method to explicitly add Authority to User and if the User is
   * not in the Authority, adds the User to the Authority.
   */
  public void addAuthority(Authority authority) {
    this.authorities.add(authority);
    if (!authority.getUsers().contains(this)) {
      authority.getUsers().add(this);
    }
  }

  /**
   * Remove Authority method to explicitly remove Authority from User and
   * explicitly removes the User from the Authority.
   */
  public void removeAuthority(Authority authority) {
    this.authorities.remove(authority);
    authority.getUsers().remove(this);
  }
}
