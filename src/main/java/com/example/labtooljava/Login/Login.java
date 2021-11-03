//package com.example.labtooljava.Login;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import java.io.Serializable;
//
//@Entity
////@Table(name = "Login")
//public class Login implements Serializable {
//
//    @Id
//    @Column(name = "ds_username", nullable = false, updatable = false)
//    private String dsUsername;
//    @Column(name = "user_password")
//    private String password;
//
//    protected Login() {}
//
//    public Login(String dsUsername, String password) {
//        this.dsUsername = dsUsername;
//        this.password = password;
//    }
//
//    public void setDsUsername(String username) {
//        this.dsUsername = username;
//    }
//
//    public String getDsUsername() {
//        return dsUsername;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String toString() {
//        return dsUsername;
//    }
//
//}
