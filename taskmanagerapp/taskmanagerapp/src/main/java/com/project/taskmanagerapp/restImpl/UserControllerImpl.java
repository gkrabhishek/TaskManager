package com.project.taskmanagerapp.restImpl;

import com.project.taskmanagerapp.POJO.Task;
import com.project.taskmanagerapp.POJO.TaskRequest;
import com.project.taskmanagerapp.POJO.User;
import com.project.taskmanagerapp.POJO.UserRequest;
import com.project.taskmanagerapp.constants.Constants;
import com.project.taskmanagerapp.rest.UserController;
import com.project.taskmanagerapp.service.UserService;
import com.project.taskmanagerapp.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signup(UserRequest userRequest) {
        try{
           return  userService.signUp(userRequest);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:4200/")
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            return userService.login(requestMap);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<String> home() {
        try{
            return userService.home();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Set<User>> getAllUser() {
        try{
            System.out.println("inside getAllUsers ");
           return  userService.getAllUsers();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new HashSet<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addTaskToUser(int id,TaskRequest taskRequest) {
        System.out.print("Inside addTaskToUser");
        try{
           return userService.addTask(id,taskRequest);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:4200/")
    public ResponseEntity<Set<Task>> getAllTasks(String email) {
        try{
            return userService.getAllTasks(email);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new HashSet<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Task> updateTaskById( int taskId,TaskRequest taskRequest) {
        try{
            return userService.updateTaskById(taskId,taskRequest);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Task(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteTaskById(int id) {
        try{
            return userService.deleteTaskById(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
