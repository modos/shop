package com.modos.shop.models;

public class Product {
    private String name, descriptions, produceDate, type;
    private int width, height, weight, counts;

    public static final String TABLE_NAME = "product";

    public static final String NAME = "name";
    public static final String PRODUCE_DATE = "produce_date";
    public static final String TYPE = "type";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String COUNTS = "counts";

    public static final String QUERY_TABLE = "CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            PRODUCE_DATE + " TEXT, " +
            TYPE + " TEXT, " +
            WIDTH + " INTEGER, " +
            HEIGHT + " INTEGER, " +
            WEIGHT + " INTEGER, " +
            COUNTS + " INTEGER)";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
}
