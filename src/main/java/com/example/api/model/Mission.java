package com.example.api.model;

public class Mission {
    private Long id;
    private String name;
    private String country;
    private String date;
    private String result;

    public Mission(Long id, String name, String country, String date, String result) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.date = date;
        this.result = result;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
    public String getDate() {
        return date;
    }
    public String getResult() {
        return result;
    }
}
