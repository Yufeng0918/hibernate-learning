package com.dbs.hibernate.mapping.one2one.team;

public class Student {

    private String id;

    private String name;

    private IdCard idCard;

    private Team team;

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

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", idCard=" + idCard
                + ", team=" + team + "]";
    }
}
