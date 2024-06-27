package com.example.blogapis.services;


import com.example.blogapis.payloads.UserDTO;
import java.util.List;

public interface UserService {

    public UserDTO createUser (UserDTO user);

    public UserDTO getUserById(Integer id);

    public List<UserDTO> getAllUsers();

    public UserDTO updateUser( UserDTO user, Integer id);

    public void deleteUser(Integer id);

}
