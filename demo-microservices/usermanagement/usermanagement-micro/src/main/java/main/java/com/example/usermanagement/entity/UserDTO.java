package main.java.com.example.usermanagement.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class UserDTO implements Serializable {
    private String name;
    private String birthdate;
    private String address;
}
