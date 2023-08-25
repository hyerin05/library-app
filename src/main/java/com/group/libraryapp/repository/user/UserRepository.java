package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository { // sql을 사용해 실제 db와 통신을 담당

    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveUser(String name, Integer age) {
        String sql = "INSERT INTO (name, age) VALUE (?, ?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> { // user정보를 user rsponse로 바꿔주는 역할을 수행
            long id = rs.getLong("id"); // ResultSet에 getType("필드이름")을 사용해 실제 값을 가져올 수 있다.
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        }); // RowMapper : 쿼리의 결과를 받아 객체를 반환한다.
    }
    public boolean isUserNotExist(long id) {
        String readSql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateUserName (String name, long id) {
        String sql = "UPDATE user SET name = ? WHERE id =? ";
        jdbcTemplate.update(sql, name, id);
    }
    public boolean isUserNotExist(String name) {
        String readSql = "SELECT * FROM user WHERE name =?"; // id를 기준으로 유저가 존재하는지 확인하는 select
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public void deleteUser(String name) {
        String sql = "DELETE FROM user WHERE name =?";
        jdbcTemplate.update(sql, name);
    }

}
