package com.flowergarden.dao;

import com.flowergarden.model.AbstractPetalable;
import com.flowergarden.model.Flower;
import com.flowergarden.model.GeneralFlower;
import com.flowergarden.model.Rose;

import java.sql.SQLException;
import java.util.List;

public interface FlowerDAO {

    void updateFlower(int key, GeneralFlower flower) throws SQLException;

    void updateFlower(int key, AbstractPetalable petalable) throws SQLException;

    void updateFlower(int key, Rose rose) throws SQLException;

    void deleteFlower(int key) throws SQLException;

    List<GeneralFlower> getAllFlowers() throws SQLException;

    Flower getFlowerById(int key) throws SQLException;

    List<GeneralFlower> getAllFlowersFromBouquet(int bouquetId) throws SQLException;


}
