package serivce.impl;

import exeption.UserNotFoundException;
import model.User;
import repository.impl.UserRepositoryImpl;

import java.nio.file.OpenOption;
import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl {
    private UserRepositoryImpl userRepository ;


    public UserServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository ;
    }




    public User login(String username, String password) {
        try {
            Optional<User> optionalUser = userRepository.findByUsernameAndPassword(username, password);
            if(!optionalUser.isPresent()) {
                throw new UserNotFoundException("Username or password is incorrect");
            }
            return optionalUser.get();
        }catch (SQLException e) {
            System.out.println("user not found");
        }
        return null;
    }


}
