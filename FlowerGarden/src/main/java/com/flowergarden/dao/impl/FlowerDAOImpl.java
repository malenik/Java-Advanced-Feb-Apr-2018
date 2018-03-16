package com.flowergarden.dao.impl;

import com.flowergarden.dao.ConnectionPool;
import com.flowergarden.dao.FlowerDAO;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerDAOImpl implements FlowerDAO {

    private final ConnectionPool connectionPool;

    public FlowerDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    //private static final String URL = "jdbc:sqlite:file:/Users/vasyachicos/Desktop/Java-Advanced-Feb-Apr-2018/FlowerGarden/flowergarden.db";

    @Override
    public void updateFlower(int key, Tulip tulip) throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE flowers SET " +
                     "lenght = ?, " +
                     "freshness = ?, " +
                     "price = ?, " +
                     "petals = ?, " +
                     "WHERE id = ? "
             );
        ) {
            pst.setInt(1, tulip.getLenght());
            pst.setInt(2, tulip.getFreshness().getFreshness());
            pst.setFloat(3, tulip.getPrice());
            pst.setInt(4, tulip.getPetals());
            pst.setInt(5, key);

            pst.executeQuery();
        }
    }

    @Override
    public void updateFlower(int key, Chamomile chamomile) throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE flowers SET " +
                     "lenght = ?, " +
                     "freshness = ?, " +
                     "price = ?, " +
                     "petals = ?, " +
                     "WHERE id = ? "
             );
        ) {
            pst.setInt(1, chamomile.getLenght());
            pst.setInt(2, chamomile.getFreshness().getFreshness());
            pst.setFloat(3, chamomile.getPrice());
            pst.setInt(4, chamomile.getPetals());
            pst.setInt(5, key);

            pst.executeQuery();
        }
    }

    @Override
    public void updateFlower(int key, Rose rose) throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE flower SET " +
                     "lenght = ?, " +
                     "freshness = ?, " +
                     "price = ?, " +
                     "spike = ? " +
                     "WHERE id = ? "
             );
        ) {
            pst.setInt(1, rose.getLenght());
            pst.setInt(2, rose.getFreshness().getFreshness());
            pst.setFloat(3, rose.getPrice());
            pst.setBoolean(4, rose.getSpike());
            pst.setInt(5, key);

            pst.executeUpdate();
        }
    }

    @Override
    public void deleteFlower(int key) throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM flowers WHERE id = ?");
        ) {
            pst.setInt(1, key);
            pst.executeQuery();
        }
    }

    @Override
    public List<GeneralFlower> getAllFlowers() throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM flower");
             ResultSet rs = pst.executeQuery();
        ) {
            List<GeneralFlower> flowersList = new ArrayList<>();

            while (rs.next()) {
                flowersList.add(getGeneralFlower(rs));
            }
            return flowersList;
        }
    }

    @Override
    public List<GeneralFlower> getAllFlowersFromBouquet(int bouquetId) throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM flower WHERE bouquet_id = ?");

        ) {
            pst.setInt(1, bouquetId);
            try (ResultSet rs = pst.executeQuery()) {
                List<GeneralFlower> flowersList = new ArrayList<>();
                while (rs.next()) {
                    flowersList.add(getGeneralFlower(rs));
                }
                return flowersList;
            }
        }
    }
    @Override
    public GeneralFlower getFlowerById(int key) throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM flower WHERE id = ?");
        ) {
            pst.setInt(1, key);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return getGeneralFlower(rs);
                }
                throw new SQLException("empty result");
            }
        }
    }

    private static GeneralFlower getGeneralFlower(ResultSet rs) throws SQLException {
        GeneralFlower generalFlower;
        String name = rs.getString("name");
        switch (name) {
            case "rose":
                generalFlower = new Rose(
                        rs.getBoolean("spike"),
                        rs.getInt("lenght"),
                        rs.getFloat("price"),
                        new FreshnessInteger(rs.getInt("freshness"))
                );
                break;
            case "tulip":
                generalFlower = new Tulip(
                        rs.getInt("petals"),
                        rs.getInt("lenght"),
                        rs.getFloat("price"),
                        new FreshnessInteger(rs.getInt("freshness"))
                );
                break;
            case "chamomile":
                generalFlower = new Chamomile(
                        rs.getInt("petals"),
                        rs.getInt("lenght"),
                        rs.getFloat("price"),
                        new FreshnessInteger(rs.getInt("freshness"))
                );
                break;
            default:
                generalFlower = new GeneralFlower(new FreshnessInteger(2), 10.0f, 4);
        }
        generalFlower.setId(rs.getInt("id"));
        return generalFlower;
    }
}
