package com.example.binarygadget.day32;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddActivity extends AppCompatActivity {

    DatabaseReference mdatabase;

    EditText name,email,phone;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mdatabase= FirebaseDatabase.getInstance().getReference().child("User");

        name=findViewById(R.id.editName);
        email=findViewById(R.id.editEmail);
        phone=findViewById(R.id.editPhone);
        save=findViewById(R.id.btnSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInfo();

            }
        });
    }
    private void addInfo(){
        final String userName= name.getText().toString();
        final String userEmail= email.getText().toString();
        final String userPhone= phone.getText().toString();

        HashMap<String, String> dataMap= new HashMap<>();
                    dataMap.put("Name",userName);
                    dataMap.put("Email",userEmail);
                    dataMap.put("Phone",userPhone);

                    String id= mdatabase.push().getKey();
                    mdatabase.child(id).setValue(dataMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            Toast.makeText(AddActivity.this,"Data Inserted", Toast.LENGTH_SHORT).show();
                        }
                    });

    }
}
