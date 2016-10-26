package pl.com.pollub.db.entities;

import pl.com.pollub.user.UserType;

import javax.persistence.*;

/**
 * Created by Maciek on 2016-10-21.
 */
@Entity
@Table(name = "Users") // word 'user' is reserved keyword in PostreSQL :)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer UserId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Firm firmId;

    @Column(length = 100)
    private String login;

    @Column(length = 32)
    private String password;

    @Column(nullable = false, length = 150)
    private String firstName;

    @Column(nullable = false, length = 200)
    private String lastName;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(length = 45)
    private String phone;

    @Column(length = 45)
    private String albumNo;

    @Column(length = 45)
    private String nationalNo;

    @Column(nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Column(nullable = false)
    private Boolean active;

    public User() {
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public Firm getFirmId() {
        return firmId;
    }

    public void setFirmId(Firm firmId) {
        this.firmId = firmId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlbumNo() {
        return albumNo;
    }

    public void setAlbumNo(String albumNo) {
        this.albumNo = albumNo;
    }

    public String getNationalNo() {
        return nationalNo;
    }

    public void setNationalNo(String nationalNo) {
        this.nationalNo = nationalNo;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
