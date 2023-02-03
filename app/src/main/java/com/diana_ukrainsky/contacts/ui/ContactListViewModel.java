package com.diana_ukrainsky.contacts.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diana_ukrainsky.contacts.common.Constants;
import com.diana_ukrainsky.contacts.data.model.Contact;
import com.diana_ukrainsky.contacts.data.model.ContactList;
import com.diana_ukrainsky.contacts.repository.Repository;
import com.diana_ukrainsky.contacts.ui.contact_list.ContactListEvent;
import com.diana_ukrainsky.contacts.ui.contact_list.FilterType;
import com.diana_ukrainsky.contacts.ui.contact_list.SortType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class ContactListViewModel extends ViewModel {
    private MutableLiveData<List<Contact>> contactListLiveData;
    private MutableLiveData<List<Contact>> filteredContactListLiveData;

    // Variable for hiding and showing the loading spinner
    private MutableLiveData<Boolean> loading;
    private MutableLiveData<String> currentSearchTextLiveData;
    private CompositeDisposable disposables;
    private Repository repository;
    private FilterType selectedFilter;
    private SortType selectedSort;


    public ContactListViewModel() {
        this.repository = Repository.getInstance();

        init();
    }

    private void init() {
        contactListLiveData = new MutableLiveData<>();
        filteredContactListLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        currentSearchTextLiveData = new MutableLiveData<>("");

        disposables = new CompositeDisposable();
        selectedFilter = FilterType.ALL;
        selectedSort=SortType.ASC;
    }
    public MutableLiveData<List<Contact>> getContactListLiveData() {
        return contactListLiveData;
    }

    public MutableLiveData<List<Contact>> getFilteredContactListLiveData() {
        return filteredContactListLiveData;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public MutableLiveData<String> getCurrentSearchTextLiveData() {
        return currentSearchTextLiveData;
    }

    public void getContacts() {
        repository.getAllContacts()
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(contacts -> {
                    List<Contact> contactList=new ArrayList<>();
                    for (Contact contact:contacts.getContactList()) {
                        contactList.add(contact);
                    }
                    return contactList;
                })
                // No need to map
                .subscribe(new Observer<List<Contact>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        loading.setValue(true);
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<Contact> contacts) {
                        loading.setValue(false);
                        contactListLiveData.setValue(contacts);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(Constants.LOG, "getContacts error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // Nothing to do here
                    }
                });
    }


    public void onEventMenuItemList(ContactListEvent event, Object object) {
        switch (event) {
            //TODO: FILTER

            case FILTER_LIST: {
                if(object instanceof FilterType)
                  selectedFilter = (FilterType) object;
                else if(object instanceof SortType)
                    selectedSort = (SortType)object;
                filterList();
                break;
            }
            case SEARCH: {
                String searchQuery=(String)object;
                searchMenuItems(searchQuery.toLowerCase());
                if(filteredContactListLiveData.getValue()!=null) {
                    if(filteredContactListLiveData.getValue().isEmpty())
                        loading.setValue(false);
                }
                break;
            }
        }
    }

    private void filterList() {
        List<Contact> filteredMenuItems;
        if (contactListLiveData.getValue() == null)
            return;
        else if (filteredContactListLiveData.getValue() == null)
            filteredMenuItems = contactListLiveData.getValue();
        else
            filteredMenuItems = filteredContactListLiveData.getValue();

        filterCases(filteredMenuItems);
    }
    private void filterCases(List<Contact> filteredContacts) {
        switch (selectedFilter) {
            case ALL:
                currentSearchTextLiveData.setValue("");
                filteredContacts =contactListLiveData.getValue();
                break;

            case AGE:
                if(selectedSort.equals(SortType.ASC))
                    Collections.sort(filteredContacts,  new Contact.SortByAge());
                else
                    Collections.sort(filteredContacts, new Contact.SortByAge().reversed());

                break;

            case ID:
                if(selectedSort.equals(SortType.ASC))
                    Collections.sort(filteredContacts, new Contact.SortById());
                else
                    Collections.sort(filteredContacts, new Contact.SortById().reversed());

                break;


            case NAME:
                if(selectedSort.equals(SortType.ASC))
                    Collections.sort(filteredContacts, new Contact.SortByName());
                else
                    Collections.sort(filteredContacts, new Contact.SortByName().reversed());

                break;

        }
        filteredContactListLiveData.setValue(filteredContacts);

    }
    public void disposeComposite() {
        disposables.dispose();
    }
// TODO: SEARCH 2

    public void searchMenuItems(String searchQuery) {
        currentSearchTextLiveData.setValue(searchQuery);
        List<Contact> filteredMenuItems = new ArrayList<>();
        if (contactListLiveData.getValue() != null) {
            for (Contact menuItem : contactListLiveData.getValue()) {
                if (menuItem.getFullName().toLowerCase().contains(searchQuery)) {
                    filteredMenuItems.add(menuItem);
                }
            }
            filteredContactListLiveData.setValue(filteredMenuItems);
        }
    }
}
