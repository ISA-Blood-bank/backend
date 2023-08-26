package com.bloodbank.BloodBank.model;

import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.model.enums.Gender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class RegistredUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Column(unique = true, nullable = false)
    private String jmbg;
    private Gender gender;
    private String email;
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    private String occupation;
    private String jobOrSchoolInfo;

    private float points;

    private Category category;

    private int penalties;

    private String phone;
    private boolean enabled;
    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    private float weight;
    @ManyToMany(fetch = FetchType.EAGER)

    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public RegistredUser() {}

    public RegistredUser(int id, String name, String surname, String jmbg, Gender gender, String email, String password, Address address, String occupation, String jobOrSchoolInfo, float points, Category category, int penalties, String phone, float weight) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jmbg = jmbg;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.address = address;
        this.occupation = occupation;
        this.jobOrSchoolInfo = jobOrSchoolInfo;
        this.points = points;
        this.category = category;
        this.penalties = penalties;
        this.phone = phone;
        this.weight = weight;
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Gender getGender() {
        return gender;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getJobOrSchoolInfo() {
        return jobOrSchoolInfo;
    }

    public void setJobOrSchoolInfo(String jobOrSchoolInfo) {
        this.jobOrSchoolInfo = jobOrSchoolInfo;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    @Override
    public String toString() {
        return "RegistredUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", jmbg='" + jmbg + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", occupation='" + occupation + '\'' +
                ", jobOrSchoolInfo='" + jobOrSchoolInfo + '\'' +
                ", points=" + points +
                ", category=" + category +
                ", penalties=" + penalties +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistredUser)) return false;
        RegistredUser that = (RegistredUser) o;
        return getId() == that.getId() && Float.compare(that.getPoints(), getPoints()) == 0 && getPenalties() == that.getPenalties() && isEnabled() == that.isEnabled() && Float.compare(that.getWeight(), getWeight()) == 0 && Objects.equals(getName(), that.getName()) && Objects.equals(getSurname(), that.getSurname()) && Objects.equals(getJmbg(), that.getJmbg()) && getGender() == that.getGender() && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getOccupation(), that.getOccupation()) && Objects.equals(getJobOrSchoolInfo(), that.getJobOrSchoolInfo()) && getCategory() == that.getCategory() && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getLastPasswordResetDate(), that.getLastPasswordResetDate()) && Objects.equals(getRoles(), that.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getJmbg(), getGender(), getEmail(), getPassword(), getAddress(), getOccupation(), getJobOrSchoolInfo(), getPoints(), getCategory(), getPenalties(), getPhone(), isEnabled(), getLastPasswordResetDate(), getWeight(), getRoles());
    }
}
