package com.indatacore.test.services.impl;


import com.indatacore.test.entities.Role;
import com.indatacore.test.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final AccountService accountService;

    @Autowired
    public UserDetailsServiceImp(AccountService accountService) {
        this.accountService = accountService;
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.indatacore.test.entities.User user = accountService.loadUserByEmail(email);
        if(user==null) throw  new UsernameNotFoundException(String.format("Email %s not found",email));

        String[] roles = user.getRoles().stream().map(Role::getRole).toArray(String[]::new);

        UserDetails userDetails = User.withUsername(user.getEmail())
                        .password(user.getPassword())
                .roles(roles)
                .build();

        return userDetails;
    }
}
