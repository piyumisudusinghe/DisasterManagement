package com.example.piumi.disaster_management;

/**
 * Created by Piumi on 2/24/2018.
 */

public class AdminUser {

    private String first_name;
    private String last_name;
    private String position;
    private String attach_company;
    private String email;

    public AdminUser(){

    }

    public AdminUser(String first_name, String last_name, String position, String attach_company, String email){
        this.first_name=first_name;
        this.attach_company =attach_company;
        this.email= email;
        this.position=position;
        this.last_name=last_name;

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAttach_company() {
        return attach_company;
    }

    public void setAttach_company(String attach_company) {
        this.attach_company = attach_company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
