package main.java.com.example.usermanagement;

import main.java.com.example.usermanagement.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{name:'?0'}")
    List<User> findUserByName(String name);

}
