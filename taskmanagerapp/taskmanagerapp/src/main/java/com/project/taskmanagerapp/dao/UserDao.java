package com.project.taskmanagerapp.dao;

import com.project.taskmanagerapp.POJO.Task;
import com.project.taskmanagerapp.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    public User findByEmail(String email);

}
