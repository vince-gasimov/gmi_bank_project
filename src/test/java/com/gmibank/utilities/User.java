package com.gmibank.utilities;

import java.util.Map;

public class User {

    private String ssn;

    private String firstName;

    private String lastName;

    private String address;

    private String mobilePhoneNumber;

    private String username;

    private String email;

    private String password;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public String getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles = profiles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAccount1() {
        return account1;
    }

    public void setAccount1(String account1) {
        this.account1 = account1;
    }

    public String getAccount2() {
        return account2;
    }

    public void setAccount2(String account2) {
        this.account2 = account2;
    }

    public boolean isZelloEnrolled() {
        return zelloEnrolled;
    }

    public void setZelloEnrolled(boolean zelloEnrolled) {
        this.zelloEnrolled = zelloEnrolled;
    }

    ////
    private  String zipCode;

    private String city;

    private String state;

    private String phoneNumber;

    private boolean activation;

    private String profiles;

    private int id;

    private String createdDate;

    private String account1;

    private String account2;

    private boolean zelloEnrolled;

    public User(String ssn, String firstName, String lastName, String address, String mobilePhoneNumber, String username, String email, String password, String zipCode, String city, String state, String phoneNumber, boolean activation, String profiles, int id, String createdDate, String account1, String account2, boolean zelloEnrolled) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.username = username;
        this.email = email;
        this.password = password;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.activation = activation;
        this.profiles = profiles;
        this.id = id;
        this.createdDate = createdDate;
        this.account1 = account1;
        this.account2 = account2;
        this.zelloEnrolled = zelloEnrolled;
    }

    public User(Map<String, String> userInfoMap) {
        this.ssn = userInfoMap.get("ssnNumber");
        this.firstName = userInfoMap.get("firstName");
        this.lastName = userInfoMap.get("lastName");
        this.address = userInfoMap.get("address");
        this.mobilePhoneNumber = userInfoMap.get("mobilePhoneNumber");
        this.username = userInfoMap.get("userName");
        this.email = userInfoMap.get("email");
        this.password = userInfoMap.get("newPassword");
        this.zipCode = userInfoMap.get("zipCode");
        this
        private  String zipCode;

        private String city;

        private String state;

        private String phoneNumber;

        private boolean activation;

        private String profiles;

        private int id;

        private String createdDate;

        private String account1;

        private String account2;

        private boolean zelloEnrolled;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

