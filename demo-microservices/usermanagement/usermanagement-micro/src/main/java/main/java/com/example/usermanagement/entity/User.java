package main.java.com.example.usermanagement.entity;

import lombok.*;
import javax.persistence.*;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true, nullable=false, precision=10)
    private Long id;

    private String name;

    private Date birthdate;

    private Date creationDate = new Date();

    private String address;
}
