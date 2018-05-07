package com.flowergarden.dao;

import com.flowergarden.model.MarriedBouquet;

import java.sql.SQLException;
import java.util.List;

public interface MarriedBouquetDAO {

    void updateBouquet(int key, MarriedBouquet marriedBouquet) throws SQLException;

    void deleteBouquet(int key) throws SQLException;;

    List<MarriedBouquet> getAllBouquets() throws SQLException;

    MarriedBouquet getBouquetById(int key) throws SQLException;


}
