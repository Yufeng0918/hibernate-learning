package com.dbs.hibernate.mapping.bag;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String id;

    private String teamName;

    private List<Student> students = new ArrayList<Student>(); // Hibernateʹ��List��ģ��Bag

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
