package com.demo.Contact.service;


import com.demo.Contact.domain.Users;

import com.demo.Contact.exception.UserNotFoundException;
import com.demo.Contact.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsersServiceImpl implements UsersService{

    private static final Logger LOGGER= LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public void createUser(Users users) {
        Users user=usersRepository.save(users);
        LOGGER.info("User Saved Successfully: "+user);

    }

    @Override
    public void updateUser(Users users) {
       Optional<Users> usersOptional=usersRepository.findById(users.getId());
       if (usersOptional.isPresent()){
           Users newUsers = usersOptional.get();
           newUsers.setFirstName(users.getFirstName());
           newUsers.setLastName(users.getLastName());
           newUsers.setSalary(users.getSalary());
           newUsers.setActive(users.isActive());
           Users updatedUser=usersRepository.save(newUsers);
           LOGGER.info("User Updated Successfully: "+updatedUser);

       }else
       {
           throw new UserNotFoundException("User with given Id does not exist.");
       }

    }

    @Override
    public Users getUser(int id) {
        Optional<Users> byId= usersRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }else
            throw new RuntimeException("User not found");

    }

    @Override
    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<Users> getUsersByLastname(String lastName) {
        return usersRepository.findByLastName(lastName);
    }
}
