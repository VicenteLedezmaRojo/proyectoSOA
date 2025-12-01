package com.example.soa_r.model;

public class usuario {
    String Email,name,password;

    public usuario(){
    }

    public usuario(String email, String name, String password) {
        this.Email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
