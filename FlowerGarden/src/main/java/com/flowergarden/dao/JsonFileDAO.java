package com.flowergarden.dao;

import java.io.IOException;

public interface JsonFileDAO<T> {

    void save(T t) throws IOException;

    T read() throws IOException;
}
