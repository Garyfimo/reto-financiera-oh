package com.garyfimo.retofinancieraoh.domain.clients;

import java.util.Date;

public class Client {

    private String name;
    private String lastname;
    private int age;
    private Date birthday;

    public Client() {
    }

    public Client(String name, String lastname, int age, Date birthday) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}