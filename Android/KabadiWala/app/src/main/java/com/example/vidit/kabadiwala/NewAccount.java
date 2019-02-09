package com.example.vidit.kabadiwala;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewAccount extends AppCompatActivity {

    EditText emailIdEditText,passwordEditText;
    RadioGroup radioGroup;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    Button signUpButton;
    String type;
    ArrayList<String> items=new ArrayList<>();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    int userCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        getSupportActionBar().setTitle("Create New Account");
        TextView loginTextView=findViewById(R.id.loginTextView);
        emailIdEditText=findViewById(R.id.emailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        radioGroup=findViewById(R.id.userTypeButtons);
        signUpButton=findViewById(R.id.signUpButton);
        loginTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(NewAccount.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                signUp(v);
            }
        });
    }

    public void signUp(View v)
    {
        final String emailId=emailIdEditText.getText().toString();
        final String password=passwordEditText.getText().toString();
        int selectedButton=radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton=(RadioButton) findViewById(selectedButton);
        type= (String) radioButton.getText();
        auth.createUserWithEmailAndPassword(emailId,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    FirebaseUser user=auth.getCurrentUser();
                    DatabaseReference rootRef=database.getReference();
                    DatabaseReference countRef=rootRef.child("userCount");
                    countRef.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            userCount=dataSnapshot.getValue(Integer.class);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    DatabaseReference userReference=rootRef.child("user/"+(userCount+1));
                    User newUser=new User(user.getEmail(),password,type);
                    userReference.setValue(newUser);
                    countRef.setValue(userCount+1);
                }
                else
                {
                    Toast.makeText(NewAccount.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
