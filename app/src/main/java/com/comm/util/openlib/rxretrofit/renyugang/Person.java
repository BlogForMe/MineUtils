package com.comm.util.openlib.rxretrofit.renyugang;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int age;
    private List<Plan> planList = new ArrayList<>();

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, List<Plan> planList) {
        this.name = name;
        this.planList = planList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Plan> getPlanList() {
        return planList;
    }
    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }
}
