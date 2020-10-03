package com.dbs.hibernate.compositecolumn.seperate;

public class Contact {

    private String student_id;

    private String method;

    private String address;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String studentId) {
        student_id = studentId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact [student_id=" + student_id + ", method=" + method
                + ", address=" + address + "]";
    }
}
