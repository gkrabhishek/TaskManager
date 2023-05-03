package com.project.taskmanagerapp.service;


import com.project.taskmanagerapp.POJO.Task;
import com.project.taskmanagerapp.POJO.TaskRequest;
import com.project.taskmanagerapp.POJO.User;
import com.project.taskmanagerapp.POJO.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {

    ResponseEntity<String> signUp(UserRequest userRequest);

    ResponseEntity<String> login(Map<String,String> map);

    ResponseEntity<Set<User>> getAllUsers();

    ResponseEntity<String> update(UserRequest userRequest);


    ResponseEntity<String> addTask(int id,TaskRequest taskRequest);

    ResponseEntity<Set<Task>> getAllTasks(String email);

    ResponseEntity<Task> updateTaskById(int taskId,TaskRequest taskRequest);

    ResponseEntity<String> deleteTaskById(int id);

    ResponseEntity<String> home();
}
