package com.project.taskmanagerapp.POJO;

import com.project.taskmanagerapp.enums.TaskPriority;
import com.project.taskmanagerapp.enums.TaskStatus;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

//@NamedQuery(name="Task.findTasksByUser",query = "select t from Task t where t.user=:user")
@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private  LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Column(name = "progress")
    private Integer progress;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;



}
