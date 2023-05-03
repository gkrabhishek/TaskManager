package com.project.taskmanagerapp.dao;

import com.project.taskmanagerapp.POJO.Task;
import com.project.taskmanagerapp.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface TaskDao extends JpaRepository<Task,Integer> {

}
