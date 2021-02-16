package com.relationship.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.relationship.entities.Authorization;
import com.relationship.entities.Permission;
import com.relationship.entities.User;
import com.relationship.entities.enums.UserStatus;

public class UserDetailsDTO implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private User user;

	public UserDetailsDTO(User user){
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract list of permissions (name)
        for(Permission p: user.getRole().getPermissions()){
            GrantedAuthority authority = new SimpleGrantedAuthority(p.getName());
            authorities.add(authority);
        }

        // Extract list of authorizations (name)
        for (Authorization a: user.getAuthorizations()){
            GrantedAuthority authority = new SimpleGrantedAuthority(a.getName());
            authorities.add(authority);
        }

        // Extract list of roles (name)
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
        authorities.add(authority);

        return authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.user.getUserStatus() == UserStatus.ACTIVE;
	}


}
