package com.example.trialone.model;

public class User {
    private String id;
    private String password;
    private String name;
    private String username;
    private String role;

    public User(){}
    public User(String id,String password,String name,String username,String role){
        this.id=id;
        this.password=password;
        this.name=name;
        this.username=username;
        this.role=role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getRole() {
        return role;
    }
}
