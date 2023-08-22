package com.group.libraryapp.domain.user;

public class User {

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        if(name == null || name.isBlank()) { // name이 null이거나 name이 비어있다면
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        } //예외
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public Integer getAge() {
        return age;
    }
}

