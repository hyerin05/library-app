package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.requset.UserCreateRequest;
import com.group.libraryapp.dto.user.requset.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate; // jdbcTemplate을 이용해 SQL을 날릴 수 있다.

    public UserController(JdbcTemplate jdbcTemplate) { // 생성자를 만들어 jdbcTemplate을 파라미터로 넣으면 자동을 들어온다.
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user") //POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        String sql = "INSERT INTO (name, age) VALUE (?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() { // Users를 userResponse로 바꿔서 반환
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> { // user정보를 user rsponse로 바꿔주는 역할을 수행
            long id = rs.getLong("id"); // ResultSet에 getType("필드이름")을 사용해 실제 값을 가져올 수 있다.
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        }); // RowMapper : 쿼리의 결과를 받아 객체를 반환한다.
    }


    @PutMapping("/user") // 수정
    public void updateUser(@RequestBody UserUpdateRequest request) {
        String readSql = "SELECT * FROM user WHERE id =?"; // id를 기준으로 유저가 존재하는지 확인하는 select
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty(); // DB에 데이터가 있는지 확인
        // SELECT * FROM user WHERE id = request.getId(), SELECT SQL의 결과가 있으면 0으로 변환
        // query는 반환된 값을들 list로 감싸준다.
        if (isUserNotExist) { // 만약에 user가 존재하지 않는다면
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE user SET name = ? WHERE id =?"; // user에 있는 정보 중 해당하는 id를 name =? -> 이 이름으로 바꿔
        jdbcTemplate.update(sql, request.getName(), request.getId());
    }

    @DeleteMapping("/user") // 삭제
    public void deleteUser(@RequestParam String name) {

        String readSql = "SELECT * FROM user WHERE name =?"; // id를 기준으로 유저가 존재하는지 확인하는 select
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
        if(isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String sql = "DELETE FROM user WHERE name =?";
        jdbcTemplate.update(sql, name);
    }

}
