package com.example.recyclerviewcontactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> contacts;
    private RecyclerView recyclerView;
    private static int counter = 0;

    private EditText editTextPhone2;
    private EditText editTextPersonName;
    private CustomRecylerAdapter myAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = new ArrayList<>();
        // Add Contacts Here:
        contacts.add(new Contact(++counter, "Ankit Yadav", "9267943068", "ankit19409@iiitd.ac.in"));
        contacts.add(new Contact(++counter, "Harshal Dev", "9818800898", "harshal19306@iiitd.ac.in"));
        contacts.add(new Contact(++counter, "Hitesh Garg", "9540453060", "hitesh19426@iiitd.ac.in"));


        editTextPhone2 = findViewById(R.id.editTextPhone2);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        recyclerView = findViewById(R.id.recyclerView);

        myAd = new CustomRecylerAdapter(contacts);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAd);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    public void onClickNew(View view) {
        String name = editTextPersonName.getText().toString();
        String phone = editTextPhone2.getText().toString();
        if(name.equals("")) {
            Toast.makeText(this, "Please Enter Name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(phone.length()!=10) {
            Toast.makeText(this, "Please Enter Valid Phone!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            long PhoneNo = Long.parseLong(phone);
        }
        catch(NumberFormatException e) {
            Toast.makeText(this, "Please Enter Number Only!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }

        addContact(name, phone);
        editTextPersonName.setText("");
        editTextPhone2.setText("");

    }

    public void addContact(String name, String phone) {
        contacts.add(new Contact(++counter, name, phone, "xyz@abc.com"));
        recyclerView.setAdapter(myAd);
    }




}