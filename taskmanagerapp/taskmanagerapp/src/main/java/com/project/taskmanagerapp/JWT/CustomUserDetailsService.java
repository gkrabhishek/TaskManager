package com.project.taskmanagerapp.JWT;


import com.project.taskmanagerapp.POJO.User;
import com.project.taskmanagerapp.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;


    private User userDetails;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        userDetails=userDao.findByEmail(username);
        if(!Objects.isNull(userDetails)){
            return new org.springframework.security.core.userdetails.User(userDetails.getEmail(),userDetails.getPassword(),new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("Username Not found");
        }
    }

    public User getUserDetails(){
       return  userDetails;
    }
}
