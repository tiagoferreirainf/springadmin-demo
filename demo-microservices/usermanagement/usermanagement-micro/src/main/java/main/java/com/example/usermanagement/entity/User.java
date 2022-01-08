package main.java.com.example.usermanagement.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

@Document("User")
public class User {
    @Id
    private String id;

    private String name;

    private Date birthdate;

    private Date creationDate = new Date();

    private String address;
}
