package com.flowergarden.dao;

import com.flowergarden.flowers.GeneralFlower;

import java.io.IOException;

public interface JsonFileDAO<T> {

    void save(T t) throws IOException;

    T read() throws IOException;
}
