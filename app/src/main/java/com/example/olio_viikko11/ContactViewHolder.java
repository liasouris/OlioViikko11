package com.example.olio_viikko11;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder{
    public TextView contactNameText;
    public TextView contactNumberText;
    public TextView contactGroupText;
    public ImageButton contactDetailsButton;
    public ImageButton contactDeleteButton;
    private boolean detailsVisible = false;


    public ContactViewHolder(View itemView) {
        super(itemView);
        contactNameText = itemView.findViewById(R.id.ContactNameText);
        contactNumberText = itemView.findViewById(R.id.ContactNumberText);
        contactGroupText = itemView.findViewById(R.id.ContactGroupText);
        contactDetailsButton = itemView.findViewById((R.id.ContactDetailsButton));
        contactDeleteButton = itemView.findViewById((R.id.ContactDeleteButton));


    }

    public void bind(final Contact contact, final ContactListAdapter adapter) {
        contactNameText.setText(contact.getFullName());
        contactNumberText.setVisibility(View.GONE);
        contactGroupText.setVisibility(View.GONE);
        contactNumberText.setText(contact.getNumber());
        contactGroupText.setText(contact.getContactGroup());

        contactDetailsButton.setOnClickListener(v -> {
            detailsVisible = !detailsVisible;
            if (detailsVisible) {
                contactNumberText.setVisibility(View.VISIBLE);
                contactGroupText.setVisibility(View.VISIBLE);
            } else {
                contactNumberText.setVisibility(View.GONE);
                contactGroupText.setVisibility(View.GONE);
            }
        });

        contactDeleteButton.setOnClickListener(v -> {
            int position = getAdapterPosition();
            ContactStorage.getInstance().removeContact(position);
            adapter.notifyItemRemoved(position);
        });
    }
}

