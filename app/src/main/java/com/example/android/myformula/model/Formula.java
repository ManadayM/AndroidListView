package com.example.android.myformula.model;

public class Formula {

    private int id;
    private String title;
    private String expression;
    private String description;

    public Formula() {
    }

//    public Formula(String title, String expression) {
//        this.title = title;
//        this.expression = expression;
//    }

    public Formula(int id, String title, String expression, String description) {
        this.id = id;
        this.title = title;
        this.expression = expression;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}