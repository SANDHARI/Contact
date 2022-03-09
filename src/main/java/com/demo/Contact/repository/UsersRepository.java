package com.demo.Contact.repository;

import com.demo.Contact.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    List<Users> findByLastName(String lastName);
}
