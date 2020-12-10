package ru.itis.javalab.utils.formcreaters.model;

import jdk.nashorn.internal.objects.annotations.Getter;


public class Form {
    String action;
    String method;

    public Form(String action, String method) {
        this.action = action;
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public String getMethod() {
        return method;
    }
}