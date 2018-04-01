package com.flowergarden.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
@Service
public interface ConnectionPool extends AutoCloseable {

    Connection getConnection() throws SQLException;

}
