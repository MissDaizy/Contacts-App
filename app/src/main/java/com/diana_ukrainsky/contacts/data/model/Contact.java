package com.diana_ukrainsky.contacts.data.model;

import static java.util.stream.Collectors.joining;

import android.view.MenuItem;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;
import java.util.stream.Stream;

public class Contact {
    @SerializedName("id")
    private int id;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("age")
    private int age;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("ip")
    private String ip;
    @SerializedName("image")
    private String image;

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAdditionalAttributes(String email, String phone, String ip){
        this.email=email;
        this.phone=phone;
        this.ip=ip;
    }
    public String getFullName() {
        return Stream.of(firstName, lastName)
                .filter(x -> x != null && !x.isEmpty())
                .collect(joining(" "));
    }
    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj!=null) {
            Contact contact=(Contact) obj;
            return id==contact.getId();
        }
        return false;
    }

    public static class SortByName implements Comparator<Contact> {
        // Used for sorting title
        public int compare(Contact c1, Contact c2) {
            return c1.getFirstName().compareTo(c2.getFirstName());
        }
    }
    public static class SortByAge implements Comparator<Contact> {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Contact c1, Contact c2) {
            return c1.getAge() - c2.getAge();
        }
    }

}
