package com.personalproject.blogapi.services.impl;

import com.personalproject.blogapi.constants.AppConstants;
import com.personalproject.blogapi.exceptions.ResourceNotFoundException;
import com.personalproject.blogapi.models.Role;
import com.personalproject.blogapi.models.User;
import com.personalproject.blogapi.payloads.UserDto;
import com.personalproject.blogapi.repository.RoleRepo;
import com.personalproject.blogapi.repository.UserRepo;
import com.personalproject.blogapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user=dtoTomodel(userDto);
        User savedUser=this.userRepo.save(user);
        return this.modelToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User savedUser=this.userRepo.save(user);
        UserDto userDto1=this.modelToDto(user);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));

        return this.modelToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User> users=this.userRepo.findAll();

       List<UserDto>userDtos=users.stream().map(user->this.modelToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
    User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
    this.userRepo.delete(user);
    }

    private User dtoTomodel(UserDto userDto){
        User user=new User();
        user=this.modelMapper.map(userDto,User.class);

        //below is the previous thing we were manually mapping one model with another
        //modelmapper help it in making that automatic.
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        return user;
    }

    public UserDto modelToDto(User user)
    {
        UserDto userDto=new UserDto();
        userDto=this.modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);

        //password encoding
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        //roles
        Role role= this.roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);

        User newUser=this.userRepo.save(user);

        return this.modelMapper.map(newUser,UserDto.class);
    }
}
