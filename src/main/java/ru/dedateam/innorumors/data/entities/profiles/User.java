package ru.dedateam.innorumors.data.entities.profiles;


import javax.persistence.*;

//@Getter
//@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

//    private Date logUpTime;
//    private Date lastLogInTime;

    private Integer age;

//    private Integer rating;

    public User(String username, String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String nickName) {
        this.username = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "id " + getId()
                + "\nnick " + getUsername()
                + "\npass " + getPassword()
//                + "\nlogUpTime " + getLogUpTime()
//                + "\nlastLogInTime " + getLastLogInTime()
                + "\nage " + getAge();
//                + "\nrating " + getRating();
    }
}
