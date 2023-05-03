package com.project.taskmanagerapp.serviceImpl;

import com.project.taskmanagerapp.JWT.CustomUserDetailsService;
import com.project.taskmanagerapp.JWT.JwtFilter;
import com.project.taskmanagerapp.JWT.JwtUtil;
import com.project.taskmanagerapp.JWT.SecurityConfig;
import com.project.taskmanagerapp.POJO.Task;
import com.project.taskmanagerapp.POJO.TaskRequest;
import com.project.taskmanagerapp.POJO.User;
import com.project.taskmanagerapp.POJO.UserRequest;
import com.project.taskmanagerapp.constants.Constants;
import com.project.taskmanagerapp.dao.TaskDao;
import com.project.taskmanagerapp.dao.UserDao;
import com.project.taskmanagerapp.service.UserService;
import com.project.taskmanagerapp.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    TaskDao taskDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    SecurityConfig securityConfig;
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Override
    public ResponseEntity<String> signUp(UserRequest userRequest) {
        try{
            if(userDao.findByEmail(userRequest.getEmail())==null){

                System.out.println("Inside sign up");
                userDao.save(getUserFromUserEntity(userRequest));
                return UserUtils.getResponseEntity("Successfully Registered",HttpStatus.OK);
            }
            else{
                return UserUtils.getResponseEntity("User Already Exist",HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> map) {
        try{
            Authentication auth=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(map.get("email"),map.get("password")));
            if(auth.isAuthenticated())
            {
               // if(customUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")){
                    return new ResponseEntity<>(""+jwtUtil.generateToken(customUserDetailsService.getUserDetails().getEmail(),
                            customUserDetailsService.getUserDetails().getRole()),HttpStatus.OK);
               // }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Set<User>> getAllUsers() {
        try{
            if(jwtFilter.isManager()) {
                return new ResponseEntity<>(new HashSet<>(userDao.findAll()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new HashSet<>(),HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new HashSet<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(UserRequest userRequest) {
        return null;
    }

    @Override
    public ResponseEntity<String> addTask(int id,TaskRequest taskRequest) {
        System.out.println("inside add task UserServiceImpl");
        try{
            if(jwtFilter.isManager()) {
                User user = userDao.findById(id).get();
                if (user != null) {
                    System.out.println("List task  " + user.getTasks());
                    Task task = new Task();
                    task.setName(taskRequest.getName());
                    task.setPriority(taskRequest.getPriority());
                    task.setProgress(taskRequest.getProgress());
                    task.setStartDate(taskRequest.getStartDate());
                    task.setEndDate(taskRequest.getEndDate());
                    task.setStatus(taskRequest.getStatus());
                    user.getTasks().add(task);
                    userDao.save(user);
                    return UserUtils.getResponseEntity("Successfully added task to user", HttpStatus.OK);
                } else {
                    return UserUtils.getResponseEntity("Invalid id", HttpStatus.BAD_REQUEST);
                }
            }
            return UserUtils.getResponseEntity(Constants.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Set<Task>> getAllTasks(String email) {
        try{
            User user = userDao.findByEmail(email);
            System.out.println(user);
             if(jwtFilter.isManager()||jwtFilter.getCurrentUser().equals(user.getEmail())) {
                 if (user != null) {
                     return new ResponseEntity<>(user.getTasks(), HttpStatus.OK);
                     //findByTasksUser
                 }
                 return new ResponseEntity<>(new HashSet<>(), HttpStatus.BAD_REQUEST);
             }
             return new ResponseEntity<>(new HashSet<>(),HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new HashSet<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Task> updateTaskById(int taskId,TaskRequest taskRequest) {
        try{
                System.out.println("id "+taskId);
                Task task =taskDao.findById(taskId).get();
                System.out.println("id "+taskId+" task="+task);
                if(task!=null) {
                    if(taskRequest.getName()!=null)
                        task.setName(taskRequest.getName());
                    if(taskRequest.getPriority()!=null)
                        task.setPriority(taskRequest.getPriority());
                    if(taskRequest.getProgress()!=null)
                        task.setProgress(taskRequest.getProgress());
                    if(taskRequest.getStartDate()!=null)
                        task.setStartDate(taskRequest.getStartDate());
                    if(taskRequest.getEndDate()!=null)
                        task.setEndDate(taskRequest.getEndDate());
                    if(taskRequest.getStatus()!=null)
                        task.setStatus(taskRequest.getStatus());
                    taskDao.save(task);
                    return new ResponseEntity<>(task, HttpStatus.OK);
                }
                return new ResponseEntity<>(new Task(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Task(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteTaskById(int id) {
        try{
            Task task=taskDao.findById(id).get();
            if(task!=null){
                taskDao.deleteById(id);
                return UserUtils.getResponseEntity("Successfully Deleted Task",HttpStatus.OK);
            }
            return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return UserUtils.getResponseEntity(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> home() {
        try{
            return new ResponseEntity(
                    "{\"message\":\""+jwtFilter.getCurrentUser()+"\"}"

                     ,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(Constants.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
    }


    private User getUserFromUserEntity(UserRequest userRequest)
    {
        User user=new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(securityConfig.passwordEncoder().encode(userRequest.getPassword()));
        System.out.println(user.getPassword());
        if(userRequest.getStatus()!=null)
            user.setStatus(userRequest.getStatus());
        else
            user.setStatus("true");
        user.setContactNumber(userRequest.getContactNumber());
        user.setRole(userRequest.getRole());
        Set<Task> set=new HashSet<Task>();
        user.setTasks(set);
        return user;
    }
}
