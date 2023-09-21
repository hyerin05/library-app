package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository를 상속 받아야한다.

    Optional<User> findByName(String name);
    // 함수 생성


}
