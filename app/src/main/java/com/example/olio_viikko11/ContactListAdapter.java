package com.example.olio_viikko11;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private ArrayList<Contact> contacts;
    public ContactListAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_view, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Contact contact = contacts.get(position);

        holder.contactNameText.setText(contact.getFullName());
        holder.contactNumberText.setVisibility(View.GONE);
        holder.contactGroupText.setVisibility(View.GONE);

        holder.contactDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.contactNumberText.getVisibility() == View.GONE) {
                    holder.contactNumberText.setText(contact.getNumber());
                    holder.contactGroupText.setText(contact.getContactGroup());
                    holder.contactNumberText.setVisibility(View.VISIBLE);
                    holder.contactGroupText.setVisibility(View.VISIBLE);
                } else {
                    holder.contactNumberText.setVisibility(View.GONE);
                    holder.contactGroupText.setVisibility(View.GONE);
                }
            }
        });

        holder.contactDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    ContactStorage.getInstance().removeContact(currentPosition);
                    contacts.remove(currentPosition);
                    notifyItemRemoved(currentPosition);
                    notifyItemRangeChanged(currentPosition, contacts.size());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
