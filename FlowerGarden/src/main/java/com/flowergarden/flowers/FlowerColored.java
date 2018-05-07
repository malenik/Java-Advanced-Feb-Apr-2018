package com.flowergarden.flowers;

public class FlowerColored extends FlowerDecorator {

    private int color;

    public FlowerColored(GeneralFlower f) {
        super(f);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
