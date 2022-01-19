package app.planningApp.entities.user;

import app.planningApp.dto.UserInfo;
import app.planningApp.entities.BaseEntity;
import app.planningApp.exceptions.UserFriendlyException;
import app.planningApp.helpers.Validator;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends BaseEntity {

    @Column(name = "email", unique = true, length = 50, nullable = false)
    private String email;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(UserInfo userInfo) throws UserFriendlyException {
        setEmail(userInfo.getEmail());
        setName(userInfo.getName());
        setPassword(userInfo.getPassword());
        userRole = UserRole.USER;
    }

    public void setEmail(String email) throws UserFriendlyException {
        if(!Validator.isEmailValid(email)){
            throw new UserFriendlyException("Invalid email");
        }
        this.email = email.trim();
    }

    public void setName(String name) throws UserFriendlyException {
        String nameToCheck = name.trim();
        for(String namePart: nameToCheck.split(" ")){
            if(!Validator.containsOnlyLetters(namePart)){
                throw new UserFriendlyException("Name have to contain only letters");
            }
        }
        this.name = nameToCheck;
    }

    public void setPassword(String password) throws UserFriendlyException {
        if(!Validator.isPasswordValid(password)){
            throw new UserFriendlyException("Password does not meet requirements");
        }
        this.password = password.trim();
    }

}
