package com.dbs.hibernate.mapping.inherit.join;

public class Teacher extends Person {

    private int salary;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher [salary=" + salary + ", getId()=" + getId()
                + ", getName()=" + getName() + "]";
    }
}
