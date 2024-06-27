package com.example.blogapis.controllers;

import com.example.blogapis.payloads.APIResponse;
import com.example.blogapis.payloads.UserDTO;
import com.example.blogapis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDTO>  createUser(@Valid @RequestBody UserDTO user){
            return new ResponseEntity<UserDTO>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userid){
        return new ResponseEntity<>(userService.getUserById(userid),HttpStatus.OK);
    }

    @PutMapping("/{userid}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO user, @PathVariable Integer userid){
        return new ResponseEntity<>(userService.updateUser(user,userid),HttpStatus.OK);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Integer userid){
        userService.deleteUser(userid);
        return ResponseEntity.ok(new APIResponse("Userid: "+userid+" deleted successfully",true,HttpStatus.OK.toString()));
    }
}
