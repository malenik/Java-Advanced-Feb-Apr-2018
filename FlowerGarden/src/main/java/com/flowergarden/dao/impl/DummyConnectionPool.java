package com.flowergarden.dao.impl;

import com.flowergarden.dao.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

@Component
public class DummyConnectionPool implements ConnectionPool {
    private final String url;
    private Connection connection;
    private Connection internal;

    public DummyConnectionPool() {
        this("jdbc:sqlite:file:/Users/vasyachicos/Desktop/Java-Advanced-Feb-Apr-2018/FlowerGarden/flowergarden.db");
    }

    public DummyConnectionPool(String url) {
        this.url = url;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            // TODO: intercept close()
            internal = DriverManager.getConnection(url);
            connection = new Connection() {
                public Statement createStatement() throws SQLException {
                    return internal.createStatement();
                }

                public PreparedStatement prepareStatement(String sql) throws SQLException {
                    return internal.prepareStatement(sql);
                }

                public CallableStatement prepareCall(String sql) throws SQLException {
                    return internal.prepareCall(sql);
                }

                public String nativeSQL(String sql) throws SQLException {
                    return internal.nativeSQL(sql);
                }

                public void setAutoCommit(boolean autoCommit) throws SQLException {
                    internal.setAutoCommit(autoCommit);
                }

                public boolean getAutoCommit() throws SQLException {
                    return internal.getAutoCommit();
                }

                public void commit() throws SQLException {
                    internal.commit();
                }

                public void rollback() throws SQLException {
                    internal.rollback();
                }

                public void close() throws SQLException {
                    if (!getAutoCommit())
                        internal.commit();
                }

                public boolean isClosed() throws SQLException {
                    return internal.isClosed();
                }

                public DatabaseMetaData getMetaData() throws SQLException {
                    return internal.getMetaData();
                }

                public void setReadOnly(boolean readOnly) throws SQLException {
                    internal.setReadOnly(readOnly);
                }

                public boolean isReadOnly() throws SQLException {
                    return internal.isReadOnly();
                }

                public void setCatalog(String catalog) throws SQLException {
                    internal.setCatalog(catalog);
                }

                public String getCatalog() throws SQLException {
                    return internal.getCatalog();
                }

                public void setTransactionIsolation(int level) throws SQLException {
                    internal.setTransactionIsolation(level);
                }

                public int getTransactionIsolation() throws SQLException {
                    return internal.getTransactionIsolation();
                }

                public SQLWarning getWarnings() throws SQLException {
                    return internal.getWarnings();
                }

                public void clearWarnings() throws SQLException {
                    internal.clearWarnings();
                }

                public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
                    return internal.createStatement(resultSetType, resultSetConcurrency);
                }

                public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
                    return internal.prepareStatement(sql, resultSetType, resultSetConcurrency);
                }

                public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
                    return internal.prepareCall(sql, resultSetType, resultSetConcurrency);
                }

                public Map<String, Class<?>> getTypeMap() throws SQLException {
                    return internal.getTypeMap();
                }

                public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
                    internal.setTypeMap(map);
                }

                public void setHoldability(int holdability) throws SQLException {
                    internal.setHoldability(holdability);
                }

                public int getHoldability() throws SQLException {
                    return internal.getHoldability();
                }

                public Savepoint setSavepoint() throws SQLException {
                    return internal.setSavepoint();
                }

                public Savepoint setSavepoint(String name) throws SQLException {
                    return internal.setSavepoint(name);
                }

                public void rollback(Savepoint savepoint) throws SQLException {
                    internal.rollback(savepoint);
                }

                public void releaseSavepoint(Savepoint savepoint) throws SQLException {
                    internal.releaseSavepoint(savepoint);
                }

                public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
                    return internal.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
                }

                public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
                    return internal.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
                }

                public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
                    return internal.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
                }

                public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
                    return internal.prepareStatement(sql, autoGeneratedKeys);
                }

                public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
                    return internal.prepareStatement(sql, columnIndexes);
                }

                public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
                    return internal.prepareStatement(sql, columnNames);
                }

                public Clob createClob() throws SQLException {
                    return internal.createClob();
                }

                public Blob createBlob() throws SQLException {
                    return internal.createBlob();
                }

                public NClob createNClob() throws SQLException {
                    return internal.createNClob();
                }

                public SQLXML createSQLXML() throws SQLException {
                    return internal.createSQLXML();
                }

                public boolean isValid(int timeout) throws SQLException {
                    return internal.isValid(timeout);
                }

                public void setClientInfo(String name, String value) throws SQLClientInfoException {
                    internal.setClientInfo(name, value);
                }

                public void setClientInfo(Properties properties) throws SQLClientInfoException {
                    internal.setClientInfo(properties);
                }

                public String getClientInfo(String name) throws SQLException {
                    return internal.getClientInfo(name);
                }

                public Properties getClientInfo() throws SQLException {
                    return internal.getClientInfo();
                }

                public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
                    return internal.createArrayOf(typeName, elements);
                }

                public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
                    return internal.createStruct(typeName, attributes);
                }

                public void setSchema(String schema) throws SQLException {
                    internal.setSchema(schema);
                }

                public String getSchema() throws SQLException {
                    return internal.getSchema();
                }

                public void abort(Executor executor) throws SQLException {
                    internal.abort(executor);
                }

                public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
                    internal.setNetworkTimeout(executor, milliseconds);
                }

                public int getNetworkTimeout() throws SQLException {
                    return internal.getNetworkTimeout();
                }


                public <T> T unwrap(Class<T> iface) throws SQLException {
                    return internal.unwrap(iface);
                }

                public boolean isWrapperFor(Class<?> iface) throws SQLException {
                    return internal.isWrapperFor(iface);
                }
            };
        }
        return connection;
    }


    @Override
    public void close() throws Exception {
        if (internal != null) {
            internal.close();
        }
    }
}
