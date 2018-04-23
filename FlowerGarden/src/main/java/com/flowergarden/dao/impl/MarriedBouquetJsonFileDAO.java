package com.flowergarden.dao.impl;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.JsonFileDAO;
import com.flowergarden.json.JsonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class MarriedBouquetJsonFileDAO implements JsonFileDAO<MarriedBouquet> {

    private File file = new File("C://Users/Nik/Desktop/Java/Java-Advanced-Feb-Apr-2018/FlowerGarden/flowers.json");


    @Override
    public void save(MarriedBouquet marriedBouquet) throws IOException {
        String json = JsonUtil.toPrettyJson(marriedBouquet);
        FileUtils.writeStringToFile(file, json, "UTF-8");
    }

    @Override
    public MarriedBouquet read() throws IOException {
        String json = FileUtils.readFileToString(file, "UTF-8");
        return JsonUtil.parseJson(json, MarriedBouquet.class);
    }
}
