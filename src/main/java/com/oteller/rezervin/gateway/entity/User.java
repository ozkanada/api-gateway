package com.oteller.rezervin.gateway.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotBlank(message = "{pandora.constraint.username.notblank.message}")
    @Size(min = 4, max = 20,message = "{pandora.constraint.username.size.message}")
    @Column(name="username")
    private String username;
    
    @NotBlank(message = "{pandora.constraint.firstname.notblank.message}")
    @Size(min = 3, max = 20,message = "{pandora.constraint.firstname.size.message}")
    @Column(name="first_name")
    private String firstName;
    
    @NotBlank(message = "{pandora.constraint.lastname.notblank.message}")
    @Size(min = 2, max = 20,message = "{pandora.constraint.lastname.size.message}")
    @Column(name="last_name")
    private String lastName;

    @NotBlank(message = "{pandora.constraint.email.notblank.message}")
    @Email(message = "{pandora.constraint.email.format.message}")
    @Column(name="email")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",message = "{pandora.constraint.password.pattern.message}")
    @Column(name="password")
    private String password;

    @Builder.Default
    @Column(name="active")
    private boolean active = false;

    @Column(name="activation_token")
    private String activationToken;

    @Column(name="image")
    private String image;

    @Builder.Default
    @Column(name="updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedDate = new Date();

    @Builder.Default
    @Column(name="created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate = new Date();

    private boolean deleted;

    private String passwordResetToken;
    
    private String role;
}
