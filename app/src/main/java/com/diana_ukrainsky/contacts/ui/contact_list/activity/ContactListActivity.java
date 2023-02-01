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
import android.widget.RadioGroup;
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
import com.diana_ukrainsky.contacts.ui.contact_list.SortType;
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
        View view = activityContactListBinding.getRoot();
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
        progressBar = activityContactListBinding.activityContactListPBProgressBar;
        // Set the default sort direction to ascending
        activityContactListBinding.radioAscending.setChecked(true);
        activityContactListBinding.radioAll.setChecked(true);
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
                activityContactListBinding.activityContactListSVSearchView.setQuery(searchText, false);
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
                if (isLoading)
                    progressBar.setVisibility(View.VISIBLE);
                else
                    progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setListeners() {
        setRadioButtonsListener();
        setRadioButtonsSortParameterListener();
        activityContactListBinding.activityContactListSVSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactListViewModel.onEventMenuItemList(ContactListEvent.SEARCH, newText);
                return true;
            }
        });

    }

    private void setRadioButtonsSortParameterListener() {
        activityContactListBinding.radiogroupSortParameter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
// Handle the change in the radio button state
                switch (checkedId) {
                    case R.id.radio_all:
                        // Sort the list of movies in ascending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, FilterType.ALL);

                        break;
                    case R.id.radio_id:
                        // Sort the list of movies in descending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, FilterType.ID);
                        break;
                    case R.id.radio_name:
                        // Sort the list of movies in descending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, FilterType.NAME);

                        break;
                    case R.id.radio_Age:
                        // Sort the list of movies in descending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, FilterType.AGE);
                        break;
                }
            }
        });
    }

    private void setRadioButtonsListener() {
        activityContactListBinding.radiogroupSort.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
// Handle the change in the radio button state
                switch (checkedId) {
                    case R.id.radio_ascending:
                        // Sort the list of movies in ascending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, SortType.ASC);


                        break;
                    case R.id.radio_descending:
                        // Sort the list of movies in descending order
                        contactListViewModel.onEventMenuItemList(ContactListEvent.FILTER_LIST, SortType.DESC);

                        break;
                }
            }
        });

    }


    private void startContactDetailsActivity() {
        String selectedItemJson = new Gson().toJson(contactDetailsViewModel.getSelectedItem().getValue());
        String itemDetailsJson = new Gson().toJson(contactDetailsViewModel.getContactDetailsLiveData().getValue());
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SELECTED_ITEM, selectedItemJson);
        bundle.putString(Constants.ITEM_DETAILS, itemDetailsJson);
        intent.putExtras(bundle);
        startActivity(intent);
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