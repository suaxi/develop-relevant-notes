package com.sw.flinkwordcountdemo.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author：suaxi
 * @date：2026/11/7 21:05
 */
public class User implements Serializable {

    public String id;
    public String username;
    public String classNo;
    public Integer money;

    public User() {
    }

    public User(String id, String username, String classNo) {
        this.id = id;
        this.username = username;
        this.classNo = classNo;
    }

    public User(String id, String username, String classNo, Integer money) {
        this.id = id;
        this.username = username;
        this.classNo = classNo;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", classNo='" + classNo + '\'' +
                ", money=" + money +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(classNo, user.classNo) && Objects.equals(money, user.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, classNo, money);
    }
}
