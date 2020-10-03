package com.dbs.hibernate.mapping.set;

public class Student implements Comparable<Student> {

    private String id;

    private String name;

    private String cardId;

    private int age;

    private Team team;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", cardId=" + cardId
                + ", age=" + age + ", team=" + team + "]";
    }


    @Override
    public int compareTo(Student o) {
        return this.getCardId().compareTo(((Student) o).getCardId());
    }
}
