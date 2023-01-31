package com.diana_ukrainsky.contacts.ui.contact_details.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.diana_ukrainsky.contacts.R;
import com.diana_ukrainsky.contacts.common.Constants;
import com.diana_ukrainsky.contacts.data.model.Contact;
import com.diana_ukrainsky.contacts.databinding.ActivityContactDetailsBinding;
import com.diana_ukrainsky.contacts.ui.contact_details.ContactDetailsViewModel;
import com.diana_ukrainsky.contacts.util.ImageUtil;
import com.google.gson.Gson;

public class ContactDetailsActivity extends AppCompatActivity {
    
    private ActivityContactDetailsBinding activityContactDetailsBinding;
    
    private static final int HEIGHT =1000 ;
    private static final int WIDTH =1000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        
        activityContactDetailsBinding = ActivityContactDetailsBinding.inflate(getLayoutInflater());
        View view=activityContactDetailsBinding.getRoot();
        setContentView(view);
        
        setUI();
    }

    private void setUI() {
        Contact contact=getItemData();
        Contact menuItemDetails = getItemDetails();
        setMenuItemUI(contact);
        setMenuItemDetailsUI(menuItemDetails);
    }
    private Contact getItemData() {
        Bundle bundle = getIntent ().getExtras ();
        String selectedItemJson = bundle.getString (Constants.SELECTED_ITEM);
        Contact selectedItem = new Gson().fromJson (selectedItemJson, Contact.class);
        return  selectedItem;
    }

    private Contact getItemDetails() {
        Bundle bundle = getIntent ().getExtras ();
        String itemDetailsJson = bundle.getString (Constants.ITEM_DETAILS);
        Contact menuItemDetails = new Gson().fromJson (itemDetailsJson, Contact.class);
        return menuItemDetails;
    }

    private void setMenuItemUI(Contact selectedItem) {
        activityContactDetailsBinding.activityContactDetailsTXTId.setText(String.valueOf(selectedItem.getId()));
        activityContactDetailsBinding.activityContactDetailsTXTAge.setText(String.valueOf(selectedItem.getAge()));
        activityContactDetailsBinding.activityContactDetailsTXTName.setText(selectedItem.getFullName());
    }

    private void setMenuItemDetailsUI(Contact menuItemDetails) {
        activityContactDetailsBinding.activityContactDetailsTXTEmail.setText(menuItemDetails.getEmail());
        activityContactDetailsBinding.activityContactDetailsTXTPhone.setText(menuItemDetails.getPhone());
        activityContactDetailsBinding.activityContactDetailsTXTIp.setText(menuItemDetails.getIp());
        setImageUI(menuItemDetails.getImage());
    }

    private void setImageUI(String imageUrl) {
        ImageUtil.setImage(this,imageUrl,activityContactDetailsBinding.activityContactDetailsIMGContactImage,WIDTH,HEIGHT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}