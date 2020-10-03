package com.dbs.hibernate.mapping.map;

import java.util.HashMap;
import java.util.Map;

public class Team {

    private String id;

    private String teamName;

    private Map<String, Student> students = new HashMap<String, Student>();

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

    public Map<String, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<String, Student> students) {
        this.students = students;
    }
}
