package com.flowergarden.dao;

import com.flowergarden.flowers.*;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;

import java.sql.SQLException;
import java.util.List;

public interface FlowerDAO {

    void updateFlower(int key, AbstractPetalable petalable) throws SQLException;

    void updateFlower(int key, Rose rose) throws SQLException;

    void deleteFlower(int key) throws SQLException;

    List<GeneralFlower> getAllFlowers() throws SQLException;

    Flower getFlowerById(int key) throws SQLException;

    List<GeneralFlower> getAllFlowersFromBouquet(int bouquetId) throws SQLException;


}
