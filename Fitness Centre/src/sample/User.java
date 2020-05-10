package sample;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String gender;
    private int age;
    private String activity;

    public User() {}
    public User(String name, String surname, String login, String password, String gender, int age) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }
    public User(String name, String surname, String login, String password, String gender, int age, String activity) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.activity = activity;
    }
    public User(int id, String name, String surname, String login, String password, String gender, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }
    public User(int id, String name, String surname, String login, String password, String gender, int age, String activity) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.activity = activity;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getActivity() {
        return activity;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", activity='" + activity + '\'' +
                '}';
    }
}
