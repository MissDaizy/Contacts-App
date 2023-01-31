package com.diana_ukrainsky.contacts.ui.contact_list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.diana_ukrainsky.contacts.data.model.Contact;
import com.diana_ukrainsky.contacts.databinding.ContactListItemBinding;
import com.diana_ukrainsky.contacts.ui.callback.ContactDiffCallback;
import com.diana_ukrainsky.contacts.ui.callback.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ContactListItemBinding contactListItemBinding;
    private List<Contact> contactList;
    private CustomItemClickListener customItemClickListener;

    private Context context;

    public ContactListAdapter(Context context) {
        this.customItemClickListener=(CustomItemClickListener)context;
        this.contactList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        contactListItemBinding = ContactListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        setListeners(myViewHolder,contact);
        myViewHolder.contactListItemBinding.contactListItemTXTName.setText(contact.getFullName());
        myViewHolder.contactListItemBinding.contactListItemTXTId.setText(String.valueOf(contact.getId()));
        myViewHolder.contactListItemBinding.contactListItemTXTAge.setText(String.valueOf(contact.getAge()));
    }

    private void setListeners(MyViewHolder myViewHolder, Contact contact) {
        myViewHolder.contactListItemBinding.contactListItemCVContactItemCard.setOnClickListener(v -> {
            customItemClickListener.onClick(contact);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void updateRecipeListItems(List<Contact> contactList) {
        final ContactDiffCallback diffCallback = new ContactDiffCallback(this.contactList, contactList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.contactList.clear();
        this.contactList.addAll(contactList);
        this.notifyDataSetChanged();
        diffResult.dispatchUpdatesTo(this);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ContactListItemBinding contactListItemBinding;

        public MyViewHolder(ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;

        }
    }
}
