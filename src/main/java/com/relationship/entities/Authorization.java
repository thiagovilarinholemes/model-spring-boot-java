package com.relationship.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_authorization")
public class Authorization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(max = 30)
    private String name;
    
    @Column(name = "description", nullable = false)
    @Size(max = 100)
    private String description;
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_authorization_user", // Nome da tabela
            joinColumns = @JoinColumn(name = "authorization_id"), // Chave da tabela
            inverseJoinColumns = @JoinColumn(name = "users_id")) // Chave da tabela estrangeira
    private Set<User> users = new HashSet<>();
    
    public Authorization(){}

    public Authorization(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorization that = (Authorization) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
