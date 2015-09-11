package com.example.android.myformula.model;

/**
 * Created by manaday.mavani on 11/09/15.
 */
public class Formula {

    private String title;
    private String description;

    public Formula() {

    }

    public Formula(String title, String description) {

        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
