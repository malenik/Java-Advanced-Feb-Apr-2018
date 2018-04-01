package com.flowergarden.dao.impl;

import com.flowergarden.dao.JsonFileDAO;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.json.JsonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FlowerJsonFileDAO implements JsonFileDAO<GeneralFlower> {

    private File file = new File("/Users/vasyachicos/Desktop/Java-Advanced-Feb-Apr-2018/FlowerGarden/flowers.json");

    @Override
    public void save(GeneralFlower generalFlower) throws IOException {
        String json = JsonUtil.toPrettyJson(generalFlower);
        FileUtils.writeStringToFile(file, json, "UTF-8");
    }

    @Override
    public GeneralFlower read() throws IOException {
        String json = FileUtils.readFileToString(file, "UTF-8");
        return JsonUtil.parseJson(json, GeneralFlower.class);
    }

}
