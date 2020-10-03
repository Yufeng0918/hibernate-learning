package com.dbs.hibernate.hql;

import java.util.Set;

public class Team {

    private String id;

    private String teamName;

    private Set<Student> students;

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", teamName=" + teamName + "]";
    }
}
