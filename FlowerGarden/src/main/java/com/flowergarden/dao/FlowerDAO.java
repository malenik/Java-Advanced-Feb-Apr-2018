package com.flowergarden.dao;

import com.flowergarden.flowers.*;

import java.sql.SQLException;
import java.util.List;

public interface FlowerDAO {

    void updateFlower(int key, Tulip tulip) throws SQLException;

    void updateFlower(int key, Chamomile chamomile) throws SQLException;

    void updateFlower(int key, Rose rose) throws SQLException;

    void deleteFlower(int key) throws SQLException;

    List<GeneralFlower> getAllFlowers() throws SQLException;

    Flower getFlowerById(int key) throws SQLException;

    List<GeneralFlower> getAllFlowersFromBouquet(int bouquetId) throws SQLException;


}
