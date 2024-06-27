package com.example.blogapis.services.impl;

import com.example.blogapis.entities.User;
import com.example.blogapis.exception.ResourceNotFoundException;
import com.example.blogapis.payloads.UserDTO;
import com.example.blogapis.repositories.UserRepo;
import com.example.blogapis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private  ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO user) {

        User dbUser = userRepo.save(dtoToUser(user));
        return UsertoUserDTO(dbUser);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User dbUser = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
        return UsertoUserDTO(dbUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> usersList = userRepo.findAll();
        return usersList.stream().map(new UserServiceImpl()::UsertoUserDTO).collect(Collectors.toList());
    }


    @Override
    public UserDTO updateUser(UserDTO user, Integer id) {

        User dbUser = userRepo.findById(id).orElseThrow(()->(new ResourceNotFoundException("User","id",id)));

       //Setting the updated values
       dbUser.setId(id);
       dbUser.setUsername(user.getUsername());
       dbUser.setPassword(user.getPassword());
       dbUser.setEmail(user.getUsername());
       dbUser.setAbout(user.getAbout());

       //saving the user back into DB
       User updatedUser = userRepo.save(dbUser);



       return UsertoUserDTO(dbUser);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
        userRepo.delete(user);
    }

    private  User dtoToUser(UserDTO user){
        return modelMapper.map(user,User.class);
    }

    private  UserDTO UsertoUserDTO(User user){
        return modelMapper.map(user,UserDTO.class);
    }

}
