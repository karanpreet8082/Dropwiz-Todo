package com.todo.karan.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.todo.karan.core.TodoTask;
import com.todo.karan.dao.TodoTableDAO;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    private TodoTableDAO todoTableDAO;

    public TodoResource(TodoTableDAO todoTableDAO) {
        this.todoTableDAO = todoTableDAO;
    }

    @GET
    @UnitOfWork
    @Path("/health")
    public String healthCheck(){
        return "Tch Tch Working Fine  ;]  ;]";
    }

    @GET
    @UnitOfWork
    @Path("/tasks")
    public Response getAllTasks() throws JsonProcessingException {
        return Response.ok(todoTableDAO.getAllTasks()).build();
    }

    @GET
    @UnitOfWork
    @Path("/task/{id}")
    public Response getTaskById(@PathParam("id") int id ){
        return Response.ok(todoTableDAO.getTaskById(id)).build();
    }

    @GET
    @UnitOfWork
    @Path("/task/user/{userid}")
    public Response getAllTasksByUserId(@PathParam("userid") String userid ) throws JsonProcessingException {
        return Response.ok(todoTableDAO.getAllTasksByUserId(userid)).build();
    }

    @GET
    @UnitOfWork
    @Path("/task/status/{status}")
    public Response getAllTasksByStatus(@PathParam("status") String status ) throws JsonProcessingException {
        return Response.ok(todoTableDAO.getAllTasksByStatus(status.toUpperCase())).build();
    }

    @POST
    @UnitOfWork
    @Path("/newTask")
    public Response createTask(@Valid TodoTask todoTask){
        return Response.ok(todoTableDAO.createTask(todoTask)).build();
    }

    @PUT
    @UnitOfWork
    @Path("/updateTask")
    public Response updateTask( @Valid TodoTask todoTask ){
        return Response.ok(todoTableDAO.updateTask(todoTask)).build();
    }

    @DELETE
    @UnitOfWork
    @Path("/deleteTask/{id}/{userid}")
    public Response deleteTask(@PathParam("id") int id, @PathParam("userid") String userid){
        return Response.ok(todoTableDAO.deleteTask(id, userid)).build();
    }

}
