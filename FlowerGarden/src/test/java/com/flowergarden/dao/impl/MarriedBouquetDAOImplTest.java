package com.flowergarden.dao.impl;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@ContextConfiguration(locations = {"classpath*:dao-test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MarriedBouquetDAOImplTest {

    @Autowired
    private MarriedBouquetDAOImpl marriedBouquetDAO;


    @Test
    public void updateBouquet() throws SQLException {
        Bouquet bouquet = new MarriedBouquet();
        ((MarriedBouquet) bouquet).setAssembledPrice(16);
        marriedBouquetDAO.updateBouquet(((MarriedBouquet) bouquet).getBouquetId(), (MarriedBouquet) bouquet);
        Assert.assertEquals(16, bouquet.getAssemblePrice(), 0.01);

    }

    @Test
    public void deleteBouquet() throws SQLException {
        /*Bouquet bouquet = new MarriedBouquet();
        int bouquetId = ((MarriedBouquet) bouquet).getBouquetId();
        marriedBouquetDAO.deleteBouquet(bouquetId);
        Assert.assertEquals(1, marriedBouquetDAO.getAllBouquets().size());*/
    }

    @Test
    public void getAllBouquets() throws SQLException {
        Assert.assertEquals(1, marriedBouquetDAO.getAllBouquets().size());
    }

    @Test
    public void getBouquetById() throws SQLException {
        Bouquet bouquet = marriedBouquetDAO.getBouquetById(1);
        Assert.assertTrue(bouquet instanceof MarriedBouquet);
        Assert.assertEquals(1, ((MarriedBouquet) bouquet).getBouquetId());
    }
}
