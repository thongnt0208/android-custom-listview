package com.example.lab3customlistview;

public class Fruit {
    private String name;
    private String description;
    private int image;
    private String imageLink;

    public Fruit(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Fruit(String name, String description, String imageLink) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.imageLink = imageLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
