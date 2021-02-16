package com.relationship.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

//import com.authentication.entities.User;
import com.relationship.entities.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(max = 100)
    private String name;

    @Column(name = "userName", nullable = false)
    @Size(max = 30)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "userStatus", nullable = false)
    private UserStatus userStatus;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Authorization> authorizations = new HashSet<>();
    
//    public User() {}
//
	public User(Long id, String name, String userName, String password, UserStatus userStatus, Role role) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        setUserStatus(userStatus);
        this.role = role;
    }
//  
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public void setUserStatus(Integer userStatus) {
//        this.userStatus = userStatus;
//    }
//
//    public String getLogin() {
//        return userName;
//    }
//
//    public void setLogin(String login) {
//        this.userName = login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Set<Authorization> getAuthorizations() {
//        return authorizations;
//    }
//
//    public void setAuthorizations(Set<Authorization> authorizations) {
//        this.authorizations = authorizations;
//    }
//
//    public void setUserStatus(UserStatus userStatus){
//        if (userStatus != null){
//            this.userStatus = userStatus.getCode();
//        }
//    }
//
//    public Integer getUserStatus() {
//        return userStatus;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(id, user.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
