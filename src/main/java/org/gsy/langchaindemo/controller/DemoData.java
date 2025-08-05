package org.gsy.langchaindemo.controller;

/**
 * @program: langchain4j-demo
 * @description:
 * @author: GSY
 * @create: 2025-07-30 16:45
 **/
public class DemoData {
    private String id;
    private String name;
    private String age;
    private String email;

    public DemoData() {
    }

    public DemoData(String id, String name, String age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
