package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.requset.UserCreateRequest;
import com.group.libraryapp.dto.user.requset.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // UserController 클래스를 API 진입지점으로 만들 뿐 아니라 스프링 빈으로 등록시킨다.
public class UserController {
    private final UserServiceV2 userService;

    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }
    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }
    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }
    @PutMapping("/user")
    private void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) { // name이라는 키를 가진 쿼리가 여기로 들어옴
        userService.deleteUser(name);
    }
}
