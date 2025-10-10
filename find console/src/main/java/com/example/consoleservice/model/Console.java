package com.example.consoleservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "console-index")
public class Console {
    @Id
    private String id;
    private String name;
    private String manufacturer;
    private String generation;
    private Double price;

    public Console() {}

    public Console(String id, String name, String manufacturer, String generation, Double price) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.generation = generation;
        this.price = price;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public String getGeneration() { return generation; }
    public void setGeneration(String generation) { this.generation = generation; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
