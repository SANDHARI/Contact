package com.demo.Contact.controller;


import com.demo.Contact.domain.Users;
import com.demo.Contact.exception.UserNotFoundException;
import com.demo.Contact.service.UsersService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class UsersController {

    private static final Logger LOGGER= LoggerFactory.getLogger(UsersController.class);
    private UsersService usersService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody Users users){
        usersService.createUser(users);
        return new ResponseEntity<>("User Saved Successfully", HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody Users users){
        try {
            usersService.updateUser(users);
            return ResponseEntity.ok("User Updated Successfully");
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id){
        try{
            return  ResponseEntity.ok(usersService.getUser(id));
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(usersService.getUsers());
    }


    @GetMapping("/users/last-name/{lastName}")
    public ResponseEntity<?> getUsers(@PathVariable("lastName") String lastName){
        return ResponseEntity.ok(usersService.getUsersByLastname(lastName));
    }

}
