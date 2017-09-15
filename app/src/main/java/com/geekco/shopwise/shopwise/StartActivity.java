package com.geekco.shopwise.shopwise;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;


public class StartActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button start = (Button) findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase_ui();
            }
        });
    }



    public  void firebase_ui()
    {
       startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Arrays.asList( new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
                                ))
                        .build(),RC_SIGN_IN);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                Intent startpage= new Intent(StartActivity.this,MainActivity.class );
                startActivity(startpage);
                finish();
                return;
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Toast.makeText(this,"Sign in cancelled",Toast.LENGTH_SHORT).show();
                    return;
                }



                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this," No Internet Connection",Toast.LENGTH_SHORT).show();
                    return;
                }





                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(this," Unknown Error",Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            Toast.makeText(this,"Unknown sign in response",Toast.LENGTH_SHORT).show();
        }

    }

}
