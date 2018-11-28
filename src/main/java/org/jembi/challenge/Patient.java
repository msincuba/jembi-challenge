package org.jembi.challenge;

import java.util.Objects;

public class Patient {

    private long id;
    private String identityNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String gender;
    private String race;

    public Patient() {
    }

    public Patient(String identityNumber, String firstName, String lastName, String phoneNumber, String address, String gender, String race) {
        this.identityNumber = identityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.race = race;
    }

    public long getId() {
        return id;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Patient identityNumber(String identityNumber) {
        setIdentityNumber(identityNumber);
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Patient firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Patient lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Patient phoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Patient address(String address) {
        setAddress(address);
        return this;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Patient gender(String gender) {
        setGender(gender);
        return this;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Patient race(String race) {
        setRace(race);
        return this;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", identityNumber='" + identityNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", race='" + race + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return identityNumber.equals(patient.identityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identityNumber);
    }

}
