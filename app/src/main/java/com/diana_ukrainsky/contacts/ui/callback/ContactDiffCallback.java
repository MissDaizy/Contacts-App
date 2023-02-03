package com.diana_ukrainsky.contacts.ui.callback;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.diana_ukrainsky.contacts.common.Constants;
import com.diana_ukrainsky.contacts.data.model.Contact;

import java.util.List;

public class ContactDiffCallback extends DiffUtil.Callback {
    private final List<Contact> mOldContactList;
    private final List<Contact> mNewContactList;

    public ContactDiffCallback(List<Contact> mOldContactList, List<Contact> mNewContactList) {
        this.mOldContactList = mOldContactList;
        this.mNewContactList = mNewContactList;
    }

    @Override
    public int getOldListSize() {
        return mOldContactList==null?0:mOldContactList.size();
    }

    @Override
    public int getNewListSize() {
        return mOldContactList==null?0:mNewContactList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldContactList.get(oldItemPosition).getId()==(mNewContactList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Contact oldContact = mOldContactList.get(oldItemPosition);
        final Contact newContact = mNewContactList.get(newItemPosition);

        return oldContact.equals(newContact);
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
