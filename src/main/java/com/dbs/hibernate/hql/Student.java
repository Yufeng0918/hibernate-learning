package com.dbs.hibernate.hql;

import java.util.Set;

public class Student {

    private String id;

    private String cardId;

    private int age;

    private String name;

    private Set<Course> courses;

    private Team team;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }


    public Team getTeam() {
        return team;
    }


    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", cardId=" + cardId + ", age=" + age
                + ", name=" + name + ", team=" + team + "]";
    }

}
