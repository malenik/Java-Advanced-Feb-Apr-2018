package com.flowergarden.dao.impl;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.ConnectionPool;
import com.flowergarden.dao.FlowerDAO;
import com.flowergarden.dao.MarriedBouquetDAO;
import com.flowergarden.flowers.GeneralFlower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MarriedBouquetDAOImpl implements MarriedBouquetDAO {

    ConnectionPool connectionPool;

    FlowerDAO flowerDAO;

    @Autowired
    public MarriedBouquetDAOImpl(ConnectionPool connectionPool, FlowerDAO flowerDAO) {
        this.connectionPool = connectionPool;
        this.flowerDAO = flowerDAO;
    }

    public MarriedBouquetDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        flowerDAO = new FlowerDAOImpl(connectionPool);
    }

    public MarriedBouquetDAOImpl() {
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void setFlowerDAO(FlowerDAO flowerDAO) {
        this.flowerDAO = flowerDAO;
    }

    @Override
    public int createBouquet(MarriedBouquet marriedBouquet) {
        return 0; // This method I'll finished soon
    }

    @Override
    public void updateBouquet(int key, MarriedBouquet marriedBouquet) throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE bouquet SET " +
                     //"name = ?, " +
                     "assemble_price = ? " +
                     "WHERE id = ? "
             )
        ) {
            pst.setFloat(1, marriedBouquet.getAssemblePrice());
            pst.setInt(2, key);

            pst.executeUpdate();
        }
    }

    @Override
    public void deleteBouquet(int key) throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM bouquet WHERE id = ?")
        ) {
            pst.setInt(1, key);
            pst.executeQuery();
        }
    }

    @Override
    public List<Bouquet> getAllBouquets() throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM bouquet");
             ResultSet rs = pst.executeQuery()
        ) {
            List<Bouquet> bouquetsList = new ArrayList<>();

            while (rs.next()) {
                bouquetsList.add(getBouquet(rs));
            }
            return bouquetsList;
        }
    }

    @Override
    public Bouquet getBouquetById(int key) throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM bouquet WHERE id = ?")
        ) {
            pst.setInt(1, key);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return getBouquet(rs);
                }
                throw new SQLException("empty result");
            }
        }
    }

    private Bouquet getBouquet(ResultSet rs) throws SQLException {
        Bouquet<GeneralFlower> bouquet;
        String name = rs.getString("name");
        switch (name) {
            case "married":
                bouquet = new MarriedBouquet(
                        rs.getInt("id"),
                        rs.getFloat("assemble_price")
                );
                bouquet.addFlowers(flowerDAO.getAllFlowersFromBouquet(((MarriedBouquet) bouquet).getBouquetId()));
                break;
            default:
                bouquet = new Bouquet<GeneralFlower>() {
                    @Override
                    public float getPrice() {
                        try {
                            return rs.getFloat("assemble_price");
                        } catch (SQLException e) {
                            return 0;
                        }
                    }

                    @Override
                    public void addFlower(GeneralFlower flower) {

                    }

                    @Override
                    public void addFlowers(Collection flowers) {

                    }

                    @Override
                    public Collection<GeneralFlower> searchFlowersByLength(int start, int end) {
                        return null;
                    }

                    @Override
                    public void sortByFreshness() {

                    }

                    @Override
                    public Collection<GeneralFlower> getFlowers() {
                        return null;
                    }
                };
        }
        return bouquet;
    }
}