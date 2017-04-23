package com.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Hung on 4/23/2017.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{


    @Autowired
    private UserService userService;

    static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map<String, Object> userMap = userService.getUserByUsername(s);
        if(userMap == null){
            throw new UsernameNotFoundException("User details not found with this username: " + s);
        }
        String username = (String)userMap.get("username");
        String password = (String)userMap.get("password");
        String role = (String)userMap.get("role");
        List<SimpleGrantedAuthority> authList = getAuthorities(role);
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, authList);
        return user ;
    }

    private List<SimpleGrantedAuthority> getAuthorities(String role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(role));
        return authList;
    }
}
