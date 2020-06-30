package com.tuananhdo.security;

import com.tuananhdo.entity.User;
import com.tuananhdo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Username not found !");
        }
        return new MyUserDetails(user);
    }
}
