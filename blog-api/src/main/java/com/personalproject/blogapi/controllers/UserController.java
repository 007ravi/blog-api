package com.personalproject.blogapi.controllers;

import com.personalproject.blogapi.models.User;
import com.personalproject.blogapi.payloads.ApiResponse;
import com.personalproject.blogapi.payloads.UserDto;
import com.personalproject.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable ("userId") Integer uId){
       UserDto updatedUser= this.userService.updateUser(userDto,uId);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
      //  return new ResponseEntity(Map.of("message","User deleted successfully"),HttpStatus.OK);
        return new ResponseEntity(new ApiResponse("User deleted successsfully",true),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','DBADMIN','NORMAL')")
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){

        return ResponseEntity.ok(this.userService.getUserById(userId));
    }


}
