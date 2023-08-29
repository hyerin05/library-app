package com.group.libraryapp.domain.user;

import jakarta.persistence.*;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;
    @Column(nullable = false, length = 20) // 객체의 name과 table의 name을 mapping
    private String name;
    private Integer age;

    protected User() {

    }

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

    public Long getId() {
        return id;
    }
}

