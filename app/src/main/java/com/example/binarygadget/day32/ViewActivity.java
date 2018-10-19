package com.example.binarygadget.day32;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private ListView myListView;
    DatabaseReference mDatabase;
    ArrayList<String> myArrayList;
    ArrayAdapter<String> adapter;
    String name,email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("User");

        myListView=findViewById(R.id.list_view);
        myArrayList = new ArrayList<>();

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                name= dataSnapshot.child("Name").getValue(String.class);
                email= dataSnapshot.child("Email").getValue(String.class);
                phone= dataSnapshot.child("Phone").getValue(String.class);

                myArrayList.add("Name: "+name);
                myArrayList.add("Email: "+email);
                myArrayList.add("Phone: "+phone);

                adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,myArrayList);
                myListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
