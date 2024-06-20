package com.todo.karan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.core.Configuration;
import io.dropwizard.db.DataSourceFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TodoConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

//    @javax.validation.Valid
//    @javax.validation.constraints.NotNull
//    @JsonProperty("database")
//    private final DatabaseConfiguration databaseConfiguration;

//    public TodoConfiguration(@JsonProperty("database") DatabaseConfiguration databaseConfiguration) {
//        this.databaseConfiguration = databaseConfiguration;
//    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

//    public DatabaseConfiguration getDatabaseConfiguration() {
//        return databaseConfiguration;
//    }
}