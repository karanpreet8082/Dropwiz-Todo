package com.todo.karan;

import com.todo.karan.core.TodoTask;
import com.todo.karan.db.TodoTableDAO;
import com.todo.karan.resources.TodoResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.dual.HibernateBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TodoApplication extends Application<TodoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TodoApplication().run(args);
    }

    private static final Logger logger = LoggerFactory.getLogger(TodoTableDAO.class);
    private final HibernateBundle<TodoConfiguration> hibernateBundle =
            new HibernateBundle<TodoConfiguration>(TodoTask.class) {
                @Override
                public DataSourceFactory getReadSourceFactory(TodoConfiguration todoConfiguration) {
                    return todoConfiguration.getDataSourceFactory();
                }

                @Override
                public DataSourceFactory getDataSourceFactory(TodoConfiguration todoConfiguration) {
                    return todoConfiguration.getDataSourceFactory();
                }
            };


    @Override
    public void initialize(final Bootstrap<TodoConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final TodoConfiguration config,
                    final Environment env) {

        final TodoTableDAO todoTableDAO = new TodoTableDAO(hibernateBundle.getSessionFactory()) ;
        env.jersey().register(new TodoResource(todoTableDAO));
    }
}
