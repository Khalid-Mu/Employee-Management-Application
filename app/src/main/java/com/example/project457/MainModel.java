package com.example.project457;

public class MainModel {
    String Id,Name,Age,Experience,Salary;

    MainModel()
    {


    }

    public MainModel(String id, String name, String age, String experience, String salary) {
        Id = id;
        Name = name;
        Age = age;
        Experience = experience;
        Salary = salary;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }
}
