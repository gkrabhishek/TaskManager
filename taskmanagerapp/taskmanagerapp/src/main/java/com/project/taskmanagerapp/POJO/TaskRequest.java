package com.project.taskmanagerapp.POJO;

import com.project.taskmanagerapp.enums.TaskPriority;
import com.project.taskmanagerapp.enums.TaskStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class TaskRequest {


    private Integer id;
    private String name;


    private LocalDate startDate;


    private  LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Column(name = "progress")
    private Integer progress;

}
