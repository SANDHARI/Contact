package com.demo.Contact.service;

import com.demo.Contact.domain.Users;

import java.util.List;

public interface UsersService {

    void createUser(Users users);
    void updateUser(Users users);

    Users getUser(int id);

    List<Users> getUsers();

   List<Users> getUsersByLastname(String lastName);


}
