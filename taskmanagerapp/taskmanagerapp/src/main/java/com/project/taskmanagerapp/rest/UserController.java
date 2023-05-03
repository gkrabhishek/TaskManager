package com.project.taskmanagerapp.rest;


import com.project.taskmanagerapp.POJO.Task;
import com.project.taskmanagerapp.POJO.TaskRequest;
import com.project.taskmanagerapp.POJO.User;
import com.project.taskmanagerapp.POJO.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestMapping("/user")
@CrossOrigin(origins = "*")
public interface UserController {

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody(required = true)UserRequest userRequest);

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String,String> requestMap);

    @GetMapping("/home")
    public ResponseEntity<String> home();

    @GetMapping("/getUsers")
    public ResponseEntity<Set<User>> getAllUser();
    @PostMapping("/{id}/addTask")
    public ResponseEntity<String> addTaskToUser(@PathVariable("id") int id,@RequestBody(required = true)
                                                   TaskRequest taskRequest);


    @GetMapping("/{email}/getAllTasks")
    public ResponseEntity<Set<Task>> getAllTasks(@PathVariable("email") String email);

    @PatchMapping("/task/{taskId}/update")
    public ResponseEntity<Task> updateTaskById(@PathVariable("taskId") int taskId,@RequestBody TaskRequest taskRequest);

    @DeleteMapping("/task/{taskId}/delete")
    public ResponseEntity<String> deleteTaskById(@PathVariable("taskId")int id);



}
