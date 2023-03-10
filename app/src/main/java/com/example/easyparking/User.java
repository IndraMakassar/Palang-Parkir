package com.example.easyparking;

public class User {
    public String email, password, nama;

    public User() {
    }

    public User(String email, String password, String nama) {

        this.email = email;
        this.password = password;
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
