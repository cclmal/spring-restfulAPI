package com.example.demo.services.interfaces;
import com.example.demo.models.User;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    User getUserById(Integer id);
    List<User> getUsersFiltered(String prefix);
    User updateUserById(Integer id, User user);
    void deleteUserById(Integer id);
}
