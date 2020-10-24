package com.codemarkTask.services;

import com.codemarkTask.entities.User;
import com.codemarkTask.exceptions.UserNotFoundException;
import com.codemarkTask.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private UsersRepository usersRepository;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User saveOrUpdate(User user) {
        return usersRepository.save(user);
    }

    public User findById(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Can't found user with id = " + id));
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return usersRepository.existsById(id);
    }

}
