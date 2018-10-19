package com.example.binarygadget.day32;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class RemovePersonActivity extends AppCompatActivity {

    private EditText phone;
    private Button remove;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_person);

        phone=findViewById(R.id.edit_phnonePerson);
        remove=findViewById(R.id.btnRemovePerson);
        mDatabase= FirebaseDatabase.getInstance().getReference();

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String deleteValue= phone.getText().toString();
                Query query = mDatabase.child("User").orderByChild("Phone").equalTo(deleteValue);

                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        dataSnapshot.getRef().setValue(null);
                        Toast.makeText(RemovePersonActivity.this,deleteValue+"Remove Successfully",Toast.LENGTH_SHORT).show();
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
        });
    }
}
