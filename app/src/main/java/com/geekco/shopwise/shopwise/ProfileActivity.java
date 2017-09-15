package com.geekco.shopwise.shopwise;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

import static android.widget.RelativeLayout.TRUE;

public class ProfileActivity extends AppCompatActivity {
   Button update ;
    String uid,display_name,display_satus;
   EditText name , status;
    private Toolbar mToolbar;
   DatabaseReference sDatabase;

    ProgressBar update_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
       uid = current_user.getUid();

        update = (Button) findViewById(R.id.update_btn);
        mToolbar = (Toolbar)findViewById(R.id.profile_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Set Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                update_progress = (ProgressBar) findViewById(R.id.update_progress);
                 update_progress.setVisibility(View.VISIBLE);
                name = (EditText) findViewById(R.id.input_name);
                status=(EditText) findViewById(R.id.input_status);
                display_name = name.getText().toString();
                display_satus = status.getText().toString();

                 sDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                HashMap<String,String> UserMap =new HashMap<String, String>();
                UserMap.put("name",display_name);
                UserMap.put("status",display_satus);
                sDatabase.setValue(UserMap,new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {


                            Toast.makeText(ProfileActivity.this,"Data could not be saved " + databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                        } else {
                            update_progress.setVisibility(View.INVISIBLE);
                        }
                    }
                });


            }
        });
    }
}
