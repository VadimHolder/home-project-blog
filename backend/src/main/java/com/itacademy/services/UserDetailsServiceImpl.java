package com.itacademy.services;

import com.itacademy.entities.User;
import com.itacademy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
//реализация логики получения юзера по юзернейму
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
//    доставляем сущность обратно в спринг секьюрити
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found in the DB for security");
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
//        grantedAuthorities.add(new SimpleGrantedAuthority("blogger"));
//        grantedAuthorities.add(new SimpleGrantedAuthority("moderator"));
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),true,true,true, true, grantedAuthorities);
    }
}
