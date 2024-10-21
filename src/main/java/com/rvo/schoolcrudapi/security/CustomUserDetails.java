// package com.rvo.schoolcrudapi.security;

// import java.util.Collection;
// import java.util.stream.Collectors;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.rvo.schoolcrudapi.model.User;

// import lombok.AllArgsConstructor;

// // CustomUserDetails generates a class compatible with Spring Securities User
// Details from the provided custom user details

// @AllArgsConstructor
// public class CustomUserDetails implements UserDetails {

// private final User user;

// @Override
// // Convert Set<Authority> to a list of GrantedAuthority for Spring Security
// public Collection<? extends GrantedAuthority> getAuthorities() {

// return user.getAuthorities().stream()
// .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
// .collect(Collectors.toList());
// }

// @Override
// public String getUsername() {
// return user.getUsername();
// }

// @Override
// public String getPassword() {
// return user.getPassword();
// }

// @Override
// public boolean isAccountNonExpired() {
// return true;
// }

// @Override
// public boolean isAccountNonLocked() {
// return true;
// }

// @Override
// public boolean isCredentialsNonExpired() {
// return true;
// }

// @Override
// public boolean isEnabled() {
// return user.isEnabled();
// }
// }
