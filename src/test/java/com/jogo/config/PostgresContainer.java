package com.jogo.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainer extends PostgreSQLContainer<PostgresContainer> {

    private static PostgresContainer container;

    private PostgresContainer() {
        super("postgres:latest");
    }

    public static PostgresContainer getInstance() {
        if (container == null)
            container = new PostgresContainer();
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("jdbc:postgresql://localhost:5432/postgres?currentSchema=lps-tier", container.getJdbcUrl());
        System.setProperty("postgres", container.getUsername());
        System.setProperty("postgres", container.getPassword());
    }

    @Override
    public void stop() {
    }
}
