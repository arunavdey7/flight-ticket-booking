package com.nagarro.Assignment_4v1.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_data")
public class User {

    @Id
    @Column(name="user_name")
    private String user_name;

    @Column(name="pass_word")
    private String password;

    public void setUserName(String user_name)
    {
        this.user_name = user_name;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getUserName()
    {
        return this.user_name;
    }
    public String getPassword()
    {
        return password;
    }

    public String toString()
    {
        return user_name;
    }
}
