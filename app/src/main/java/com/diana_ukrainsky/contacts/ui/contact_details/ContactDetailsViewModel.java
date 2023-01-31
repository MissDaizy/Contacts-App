package com.diana_ukrainsky.contacts.ui.contact_details;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diana_ukrainsky.contacts.common.Constants;
import com.diana_ukrainsky.contacts.data.model.Contact;
import com.diana_ukrainsky.contacts.repository.Repository;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class ContactDetailsViewModel extends ViewModel {
    private MutableLiveData<Contact> contactDetailsLiveData;
    private MutableLiveData<Contact> selectedItem;
    private Repository repository;
    private CompositeDisposable disposables;
    private PublishSubject<Contact> contactDetailsSubject;



    public ContactDetailsViewModel( ) {
        this.repository = Repository.getInstance();


        init();
    }

    private void init() {
        contactDetailsLiveData = new MutableLiveData<>();
        selectedItem = new MutableLiveData<>();
        disposables = new CompositeDisposable();

        contactDetailsSubject = PublishSubject.create();
    }

    public void subscribeSubject(String id) {
        Disposable disposable =
                repository.getContactDetails(id)
                        .subscribeOn(Schedulers.io())
                        .subscribe(contactDetailsSubject::onNext, throwable -> {
                            Log.e(Constants.LOG, "subscribeSubject error: " + throwable.getMessage());
                        });
        disposables.add(disposable);

    }

    public MutableLiveData<Contact> getContactDetailsLiveData() {
        return contactDetailsLiveData;
    }

    public MutableLiveData<Contact> getSelectedItem() {
        return selectedItem;
    }

    public void getMenuItemDetails() {
        contactDetailsSubject
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Contact>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Contact contactDetails) {
                        contactDetailsLiveData.setValue(contactDetails);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(Constants.LOG, "getContactDetails error: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        // Nothing to do here

                    }
                });

    }

    public void onEventRecipeList(ContactDetailsEvent contactDetailsEvent, Object object) {
        switch (contactDetailsEvent) {
            case GET_CONTACT_DETAILS:
                Contact contact = (Contact) object;
                selectedItem.setValue(contact);
                subscribeSubject(String.valueOf(contact.getId()));
                getMenuItemDetails();
                break;

        }

    }
}
