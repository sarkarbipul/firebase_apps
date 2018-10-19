package com.example.binarygadget.day32;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckActivity extends AppCompatActivity {
    private EditText editTextPhone;
    private Button btnCheck;
    DatabaseReference reference;
    private TextView retrieveName,retrieveEmail,retrievePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        editTextPhone=findViewById(R.id.edit1);
        btnCheck=findViewById(R.id.btn1);
        retrieveName=findViewById(R.id.tvNmae);
        retrieveEmail=findViewById(R.id.tvEmail);
        retrievePhone=findViewById(R.id.tvPhone);

        reference= FirebaseDatabase.getInstance().getReference().child("User");

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkData();
            }
        });
    }
    private void checkData(){
        String checkPhone = editTextPhone.getText().toString();

        reference.orderByChild("Phone").equalTo(checkPhone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    Toast.makeText(CheckActivity.this,"Entered Phone is exist already",Toast.LENGTH_SHORT).show();

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        User user = postSnapshot.getValue(User.class);
                        retrievePhone.setText(user.getPhone());
                        retrieveName.setText(user.getName());
                        retrieveEmail.setText(user.getEmail());
                    }
                }else {
                    Toast.makeText(CheckActivity.this,"Entered Phone is not exist",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
