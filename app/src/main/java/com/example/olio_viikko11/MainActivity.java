package com.example.olio_viikko11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactListAdapter adapter;
    private ArrayList<Contact> contacts;
    private Button addContactActivityButton;
    private ImageButton sortAlphabeticallyButton;
    private ImageButton sortByGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sortAlphabeticallyButton = findViewById(R.id.SortAlphabeticallyButton);
        sortByGroupButton = findViewById(R.id.SortByGroupButton);
        addContactActivityButton = findViewById(R.id.AddContactActivityButton);
        recyclerView = findViewById(R.id.ListContactsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contacts = ContactStorage.getInstance().getContacts();
        adapter = new ContactListAdapter(contacts);
        recyclerView.setAdapter(adapter);

        addContactActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });

        sortAlphabeticallyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(contacts, new Comparator<Contact>() {
                    @Override
                    public int compare(Contact c1, Contact c2) {
                        return c1.getFirstName().compareToIgnoreCase(c2.getFirstName());
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        sortByGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(contacts, new Comparator<Contact>() {
                    @Override
                    public int compare(Contact c1, Contact c2) {
                        if(c1.getContactGroup().equals("Työ") && !c2.getContactGroup().equals("Työ"))
                            return -1;
                        if(!c1.getContactGroup().equals("Työ") && c2.getContactGroup().equals("Työ"))
                            return 1;
                        return c1.getFirstName().compareToIgnoreCase(c2.getFirstName());
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
