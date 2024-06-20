package com.todo.karan.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.karan.core.TodoTask;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TodoTableDAO extends AbstractDAO<TodoTask> {

    private static final Logger logger = LoggerFactory.getLogger(TodoTableDAO.class);
    ObjectMapper objectMapper = new ObjectMapper();

    public TodoTableDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Map<String, Object>> getAllTasks() throws JsonProcessingException {
        logger.info("Fetching all tasks from DB");
        return objectMapper.convertValue(namedQuery("todotable.db.findAll").getResultList(),
                        new TypeReference<List<Map<String, Object>>>() {});
    }

    public TodoTask getTaskById(int id){
        logger.info("Fetching task from DB with ID : " + id);
        return get(id);
    }

    public List<Map<String, Object>> getAllTasksByUserId(String userid) throws JsonProcessingException {
        List<Map<String, Object>> allTasks = getAllTasks();
        logger.info("Filtering tasks for User : " + userid);
        List<Map<String, Object>> userTasks = new ArrayList<>();
        for (int i = 0 ; i < allTasks.size() ; i++ ){
            if (userid.equalsIgnoreCase((String) allTasks.get(i).get("userid"))){
                userTasks.add(allTasks.get(i));
            }
        }
        return userTasks;
    }

    public List<Map<String, Object>> getAllTasksByStatus(String status) throws JsonProcessingException {
        List<Map<String, Object>> allTasks = getAllTasks();
        logger.info("Filtering tasks for Status : " + status);
        List<Map<String, Object>> statusTasks = new ArrayList<>();
        for (int i = 0 ; i < allTasks.size() ; i++ ){
            if (status.equalsIgnoreCase((String) allTasks.get(i).get("status"))){
                statusTasks.add(allTasks.get(i));
            }
        }
        return statusTasks;
    }

    public String createTask(TodoTask todoTask){

        //TODO What about a ID that is already in DB : Can be handled at frontend
        // Gets Updated though

        todoTask.setUserid(todoTask.getUserid().toUpperCase());
        logger.info("Creating task for User : " + todoTask.getUserid());
        return "Task Saved Successfully with ID : " + persist(todoTask).getId();
    }

    public String updateTask(TodoTask todoTask){
        String userId = todoTask.getUserid().toUpperCase();
        todoTask.setUserid(userId);
        logger.info("Updating task for User : " + todoTask.getUserid());
        return "Tasked Updated Successfully with ID : " + persist(todoTask).getId();
    }

    public String deleteTask(int id, String userid){
        TodoTask task = getTaskById(id);
        if ((task != null) && (task.getUserid().equalsIgnoreCase(userid))){
            Session session = this.currentSession();
            TodoTask todoTask = session.get(TodoTask.class, id);
            session.delete(todoTask);
            return "Deletion successful";
        }
        return "Task not found for given ID and USERID";
    }
}
