package org.example.user.web.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户领域对象
 *
 * @since 1.0
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Min(value = 1, message = "id为大于0整数")
    private Long id;

    @Column
    private String name;

    @Column
    @Range(min = 6, max = 32, message = "密码在6到32位之间")
    private String password;

    @Column
    @Email
    private String email;

    @Column
    @Range(min = 11, max = 11, message = "请输入正确的手机号码")
    private String phoneNumber;


    public User() {
    }

    public User(String name, String password, String email, String phoneNumber) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = createId ();
    }

    private Long createId() {
        return (long) ((Math.random () * 9 + 1) * 10000000);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass () != o.getClass ()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals (id, user.id) && Objects.equals (name, user.name) && Objects.equals (password, user.password) && Objects.equals (email, user.email) && Objects.equals (phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash (id, name, password, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
