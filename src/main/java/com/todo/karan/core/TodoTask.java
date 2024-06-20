package com.todo.karan.core;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "todotable")
        @NamedQuery(name = "todotable.db.findAll",
                query = "SELECT e FROM TodoTask e")
public class TodoTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( name = "userid" )
    private String userid;

    @Column( name = "status" )
    private String status;

    @Column( name = "taskname" )
    private String name;

    @Column( name = "description" )
    private String description;

    @Column( name = "startdate" )
    private Date startDate;

    @Column( name = "targetdate" )
    private Date targetDate;

    public TodoTask(){

    }

    public TodoTask(String userid, String status, String name, String description, Date startDate, Date targetDate) {
        this.userid = userid;
        this.status = status;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.targetDate = targetDate;
    }

    @Override
    public String toString(){
        return id + " / " +
                userid + " / " +
                status + " / " +
                name + " / " +
                description + " / " +
                startDate + " / " +
                targetDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }
}
