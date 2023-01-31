package com.diana_ukrainsky.contacts.repository;

import com.diana_ukrainsky.contacts.data.model.Contact;
import com.diana_ukrainsky.contacts.data.model.ContactList;
import com.diana_ukrainsky.contacts.data.remote.ApiService;
import com.diana_ukrainsky.contacts.data.remote.JsonApiContacts;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private static Repository INSTANCE = null;

    private JsonApiContacts jsonApiContacts;
    public static Repository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Repository();

        return INSTANCE;
    }
    private Repository( ) {
        this.jsonApiContacts = ApiService.getInstance().getJsonApiContacts();
    }

    public Observable<ContactList> getAllContacts(){
        return jsonApiContacts.getAllContacts();
    }
    public Observable<Contact> getContactDetails(String id){
        return jsonApiContacts.getContactDetails(id);
    }
}
