package com.diana_ukrainsky.contacts.data.remote;

import com.diana_ukrainsky.contacts.data.model.Contact;
import com.diana_ukrainsky.contacts.data.model.ContactList;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonApiContacts {
    @GET("users")
    Observable<ContactList> getAllContacts();

    @GET("users/{contactId}")
    Observable<Contact> getContactDetails(
            @Path("contactId") String id
    );
}
