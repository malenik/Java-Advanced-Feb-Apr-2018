package com.flowergarden.dao.impl;

import com.flowergarden.dao.ConnectionPool;
import com.flowergarden.dao.FlowerDAO;
import com.flowergarden.flowers.*;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
public class FlowerDAOImpl implements FlowerDAO {

    private static final String SQL_FLOWER_ID = "sqlFlowersById";
    private static final String SQL_UPDATE_FLOWER1 = "sqlUpdateFlower1";
    private static final String SQL_UPDATE_FLOWER2 = "sqlUpdateFlower2";
    private static final String SQL_DELETE_FLOWER = "sqlDeleteFlower";
    private static final String SQL_ALL_FLOWERS = "sqlAllFlowers";
    private static final String SQL_ALL_FLOWERS_FROM_BOUQUET = "sqlAllFlowersFromBouquet";
    private static final Properties sql = new Properties();

    static {
        try {
            ClassLoader loader = FlowerDAOImpl.class.getClassLoader();
            sql.load(loader.getResourceAsStream("sql-scripts.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionPool connectionPool;

    public FlowerDAOImpl() {
    }

    @Autowired
    public FlowerDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void updateFlower(int key, AbstractPetalable petalable) throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql.getProperty(SQL_UPDATE_FLOWER1))
        ) {
            pst.setInt(1, petalable.getLength());
            pst.setInt(2, petalable.getFreshness().getFreshness());
            pst.setFloat(3, petalable.getPrice());
            pst.setInt(4, petalable.getPetals());
            pst.setInt(5, key);

            pst.executeQuery();
        }
    }

    @Override
    public void updateFlower(int key, Rose rose) throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql.getProperty(SQL_UPDATE_FLOWER2))
        ) {
            pst.setInt(1, rose.getLength());
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
             PreparedStatement pst = conn.prepareStatement(sql.getProperty(SQL_DELETE_FLOWER))
        ) {
            pst.setInt(1, key);
            pst.executeQuery();
        }
    }

    @Override
    public List<GeneralFlower> getAllFlowers() throws SQLException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql.getProperty(SQL_ALL_FLOWERS));
             ResultSet rs = pst.executeQuery()
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
             PreparedStatement pst = conn.prepareStatement(sql.getProperty(SQL_ALL_FLOWERS_FROM_BOUQUET))

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
             PreparedStatement pst = conn.prepareStatement(sql.getProperty(SQL_FLOWER_ID))
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
                        rs.getInt("length"),
                        rs.getFloat("price"),
                        new FreshnessInteger(rs.getInt("freshness"))
                );
                break;
            case "tulip":
                generalFlower = new Tulip(
                        rs.getInt("petals"),
                        rs.getInt("length"),
                        rs.getFloat("price"),
                        new FreshnessInteger(rs.getInt("freshness"))
                );
                break;
            case "chamomile":
                generalFlower = new Chamomile(
                        rs.getInt("petals"),
                        rs.getInt("length"),
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
