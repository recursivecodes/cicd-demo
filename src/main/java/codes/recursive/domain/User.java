package codes.recursive.domain;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @AutoPopulated
    private UUID id;
    @NotNull
    @Size(min = 3, max = 75)
    private String firstName;
    @NotNull
    @Size(min = 5, max = 75)
    private String lastName;
    @Size(min = 1, max = 150)
    private int age;
    @Nullable
    @Email
    @Size(max = 500)
    private String email;

    public User(String firstName, String lastName, int age, @Nullable String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }
}