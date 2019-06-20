package com.example.testpokeapi.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {
    @SerializedName("name")
    private String name;
    @SerializedName("height")
    private String height;
    @SerializedName("weight")
    private String weight;
    @SerializedName("id")
    private int id;

    public Pokemon(String name, String height, String weight, int id) {
        this.height = height;
        this.name = name;
        this.weight = weight;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String text) {
        this.name = text;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
