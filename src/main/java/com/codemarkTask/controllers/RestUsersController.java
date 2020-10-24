package com.codemarkTask.controllers;

import com.codemarkTask.entities.User;
import com.codemarkTask.exceptions.UserNotFoundException;
import com.codemarkTask.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class RestUsersController {
    private UsersService usersService;

    @Autowired
    public RestUsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return usersService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneUsers(@PathVariable Long id) {
        if (!usersService.existsById(id)) {
            throw new UserNotFoundException("User not found, id: " + id);
        }
        return new ResponseEntity<>(usersService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteOneUsers(@PathVariable Long id) {
        usersService.deleteById(id);
        return "OK";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveNewUser(@RequestBody User user) {
        if (user.getId() != null) {
            user.setId(null);
        }

        return usersService.saveOrUpdate(user);
    }

    @PutMapping
    public ResponseEntity<?> modifyUser(@RequestBody User user) {
        //String pat = "[+0-9+A-Z]"; Регулярные выражения, Pattern, убрать в отдельный класс.
        if (user.getId() == null || !usersService.existsById(user.getId())) {
            throw new UserNotFoundException("User not found, id: " + user.getId());
        }
        if (user.getLogin() ==null || user.getPassword() ==null){ // !user.getPassword().matches(pat))
            return new ResponseEntity<>("User's parameter is negative", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usersService.saveOrUpdate(user), HttpStatus.OK);//Можно возвращать JSON. Тема AOP
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(UserNotFoundException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);//Можно возвращать JSON. Тема AOP
    }
}