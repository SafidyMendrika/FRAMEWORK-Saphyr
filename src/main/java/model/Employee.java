package model;

import etu1899.framework.annotations.DBMethod;

public class Employee {
    private String name;
    private String adress;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DBMethod(link = "hello")
    public static void hello(){
        System.out.println("method hello : Bonjour badoude");
    }
}
