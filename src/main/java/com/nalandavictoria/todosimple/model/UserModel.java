package com.nalandavictoria.todosimple.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {
    public interface UserCreate{}
    public interface UserUpdate{}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(groups = UserCreate.class, message = "O nome é obrigatório.")
    @Length(groups = UserCreate.class, min = 5, max = 150, message = "Deve haver de 5 a 150 caracteres.")
    private String name;

    @Column(name = "password", nullable = false)
    @NotBlank(groups = {UserCreate.class, UserUpdate.class}, message = "A senha é obrigatória.")
    @Length(groups = {UserCreate.class, UserUpdate.class}, min = 6, max = 30, message = "Deve haver de 6 a 30 caracteres.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<TaskModel> tasks = new ArrayList<TaskModel>();
}
