package com.dbs.hibernate.mapping.one2one.primarykey;

public class IdCard {

    private String id;

    private int number;

    private Student student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "IdCard [id=" + id + ", number=" + number + "]";
    }
}
