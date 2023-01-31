package com.diana_ukrainsky.contacts.ui.contact_list.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.diana_ukrainsky.contacts.R;
import com.diana_ukrainsky.contacts.common.Constants;
import com.diana_ukrainsky.contacts.data.model.Contact;
import com.diana_ukrainsky.contacts.databinding.ActivityContactListBinding;
import com.diana_ukrainsky.contacts.ui.ContactListViewModel;
import com.diana_ukrainsky.contacts.ui.callback.CustomItemClickListener;
import com.diana_ukrainsky.contacts.ui.contact_details.ContactDetailsEvent;
import com.diana_ukrainsky.contacts.ui.contact_details.ContactDetailsViewModel;
import com.diana_ukrainsky.contacts.ui.contact_details.activity.ContactDetailsActivity;
import com.diana_ukrainsky.contacts.ui.contact_list.ContactListEvent;
import com.diana_ukrainsky.contacts.ui.contact_list.FilterType;
import com.diana_ukrainsky.contacts.ui.contact_list.adapter.ContactListAdapter;
import com.google.gson.Gson;

import java.util.List;

public class ContactListActivity extends AppCompatActivity implements LifecycleOwner, CustomItemClickListener {
    private ContactListViewModel contactListViewModel;
    private ContactDetailsViewModel contactDetailsViewModel;
    private RecyclerView recyclerView;
    private ContactListAdapter contactListAdapter;
    private ProgressBar progressBar;

    private ActivityContactListBinding activityContactListBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        activityContactListBinding = ActivityContactListBinding.inflate(getLayoutInflater());
        View view= activityContactListBinding.getRoot();
        setContentView(view);

        setViewModels();
        setViews();
        setObservers();

        setListeners();
        setRecyclerView();
        setAdapter();
        setMenuItemListUI();
    }

    private void setViews() {
        progressBar=activityContactListBinding.activityContactListPBProgressBar;
    }

    private void setViewModels() {
        contactListViewModel = new ViewModelProvider(this).get(ContactListViewModel.class);
        contactDetailsViewModel = new ViewModelProvider(this).get(ContactDetailsViewModel.class);
    }

    private void setObservers() {
        contactListViewModel.getContactListLiveData().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactListAdapter.updateRecipeListItems(contacts);
            }
        });
        contactListViewModel.getFilteredContactListLiveData().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> filteredContacts) {
                contactListAdapter.updateRecipeListItems(filteredContacts);
            }
        });
        contactListViewModel.getCurrentSearchTextLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String searchText) {
                activityContactListBinding.activityContactListSVSearchView.setQuery(searchText,false);
            }
        });
        // TODO: CONTACT DETAILS OBSERVE
        contactDetailsViewModel.getContactDetailsLiveData().observe(this, new Observer<Contact>() {
            @Override
            public void onChanged(Contact contact) {
                startContactDetailsActivity();
            }
        });

        contactListViewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading)
                    progressBar.setVisibility(View.VISIBLE);
                else
                    progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void startContactDetailsActivity() {
        String selectedItemJson = new Gson().toJson (contactDetailsViewModel.getSelectedItem().getValue());
        String itemDetailsJson = new Gson().toJson (contactDetailsViewModel.getContactDetailsLiveData().getValue());
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle ();
        bundle.putString(Constants.SELECTED_ITEM,selectedItemJson);
        bundle.putString(Constants.ITEM_DETAILS,itemDetailsJson);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setListeners() {
        setFilterButtonsListeners();
        activityContactListBinding.activityContactListSVSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactListViewModel.onEventMenuItemList(ContactListEvent.SEARCH,newText);
                return true;
            }
        });

    }

    private void setFilterButtonsListeners() {
        activityContactListBinding.activityContactListBTNAllFilter.setOnClickListener(v -> {
            contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, FilterType.ALL);
        });
        activityContactListBinding.activityContactListBTNAscAgeFilter.setOnClickListener(v -> {
            contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST,FilterType.ASC_AGE);
        });
        activityContactListBinding.activityContactListBTNDescAgeFilter.setOnClickListener(v -> {
            contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST,FilterType.DESC_AGE);
        });
        activityContactListBinding.activityContactListBTNNameFilter.setOnClickListener(v -> {
            contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST,FilterType.NAME);
        });
    }

    private void setRecyclerView() {
        recyclerView = activityContactListBinding.activityContactListRVRecyclerView;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setAdapter() {
        contactListAdapter = new ContactListAdapter(this);
        recyclerView.setAdapter(contactListAdapter);
    }

    private void setMenuItemListUI() {
        contactListViewModel.getContacts();
    }

    @Override
    public void onClick(Object object) {
        contactDetailsViewModel
                .onEventRecipeList(
                        ContactDetailsEvent.GET_CONTACT_DETAILS,
                        object
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactListViewModel.disposeComposite();
    }
}