package com.example.olio_viikko11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {
    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private EditText phoneNumberEdit;
    private RadioGroup contactTypeRadioGroup;
    private Button addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        firstNameEdit = findViewById(R.id.FirstNameEdit);
        lastNameEdit = findViewById(R.id.LastNameEdit);
        phoneNumberEdit = findViewById(R.id.PhoneNumberEdit);
        contactTypeRadioGroup = findViewById(R.id.ContactTypeRadioGroup);
        addContactButton = findViewById(R.id.AddContactActivityButton);

        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = firstNameEdit.getText().toString().trim();
                String lastName = lastNameEdit.getText().toString().trim();
                String number = phoneNumberEdit.getText().toString().trim();

                String contactGroup = "Personal";
                int selectedId = contactTypeRadioGroup.getCheckedRadioButtonId();
                if(selectedId == R.id.WorkRadioButton) {
                    contactGroup = "Work";
                }

                Contact newContact = new Contact(firstName, lastName, number, contactGroup);
                ContactStorage.getInstance().addContact(newContact);

                finish();
            }
        });
    }
}
