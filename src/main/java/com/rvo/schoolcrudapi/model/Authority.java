package com.rvo.schoolcrudapi.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

  // All Ignore/Exclude to avoid infinite recursion (StackOverFlow exceptions)
  @JsonIgnore
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
  private Set<User> users = new HashSet<>();

  public Authority(String authorityName) {
    this.authorityName = authorityName;
  }

  /**
   * Add user method to explicitly add User to Authority and if the Authority is
   * not in the User, adds the Authority to the User.
   */
  public void addUser(User user) {
    this.users.add(user);
    if (!user.getAuthorities().contains(this)) {
      user.getAuthorities().add(this);
    }
  }

  /**
   * Delete user method to explicitly remove the User from the Authority and
   * removes the Authority from the User.
   */
  public void deleteUser(User user) {
    this.users.remove(user);
    user.getAuthorities().remove(this);
  }
}
