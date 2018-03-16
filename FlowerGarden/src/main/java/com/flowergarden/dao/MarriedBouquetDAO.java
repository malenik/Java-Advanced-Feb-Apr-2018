package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.Flower;

import java.sql.SQLException;
import java.util.List;

public interface MarriedBouquetDAO {

    int createBouquet(MarriedBouquet marriedBouquet) throws SQLException;

    void updateBouquet(int key, MarriedBouquet marriedBouquet) throws SQLException;

    void deleteBouquet(int key) throws SQLException;;

    List<Bouquet> getAllBouquets() throws SQLException;

    Bouquet getBouquetById(int key) throws SQLException;


}
