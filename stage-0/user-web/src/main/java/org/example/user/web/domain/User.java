package org.example.user.web.domain;

public class User {
    private String id;
    private String nickname;
    private String password;
    private String phoneNumber;


    /**
     * id应用户实例化而随之创建。为确保id唯一性，不提供setter方法防止id篡改
     *
     * @param id
     */
    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
