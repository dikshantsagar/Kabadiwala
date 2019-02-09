package com.example.vidit.kabadiwala;

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class BuyerLoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    static FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    int count;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_login);

        emailEditText=findViewById(R.id.emailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        user=firebaseAuth.getCurrentUser();
        if(user!=null)
        {
//            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//            startActivity(intent);
        }
        Button loginButton=findViewById(R.id.loginButton);
        TextView newAccountTextView=findViewById(R.id.newAccountTextView);
        newAccountTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(BuyerLoginActivity.this,NewAccount.class);
                startActivity(intent);
                finish();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                signIn(v);
            }
        });
    }

    public void signIn(View view)
    {
        final String emailId=emailEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(emailId,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    final FirebaseUser user=firebaseAuth.getCurrentUser();
                    Intent intent=new Intent(BuyerLoginActivity.this,BuyerMainActivity.class);
                    intent.putExtra("UID",user.getUid());
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(BuyerLoginActivity.this,"not logged in",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(BuyerLoginActivity.this,UserTypeActivity.class);
        startActivity(intent);
        finish();
    }
}
