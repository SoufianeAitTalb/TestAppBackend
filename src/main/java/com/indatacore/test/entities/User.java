package com.indatacore.test.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED) // or other strategies like SINGLE_TABLE
//@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name", length = 100)
    private String firstName;
    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "user_name", length = 100,unique = true)
    private String userName;

    @Column(name = "email", length = 100,unique = true)
    private String email;
    @Column(name = "password", length = 100)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;


}
