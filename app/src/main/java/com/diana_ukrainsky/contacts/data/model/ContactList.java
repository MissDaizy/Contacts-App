package com.diana_ukrainsky.contacts.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactList {
    @SerializedName("users")
    private List<Contact> contactList;

    public ContactList() {
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
