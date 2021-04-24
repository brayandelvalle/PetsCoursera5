package com.example.petscoursera.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.petscoursera.JavaMailAPI;
import com.example.petscoursera.R;

public class Contacto extends AppCompatActivity {

    EditText etContactName, etContactMail, etContactComment;
    Button btnSendComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        etContactName    = (EditText) findViewById(R.id.etContactName);
        etContactMail    = (EditText) findViewById(R.id.etContactMail);
        etContactComment = (EditText) findViewById(R.id.etContactComment);
        btnSendComment   = (Button)   findViewById(R.id.btnSendComment);

        btnSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { sendComment(); }
        });
    }

    //This method will send the comment with the help of the class JavaMailAPI
    public void sendComment(){
        String name     = etContactName.getText().toString().trim();
        String mail     = etContactMail.getText().toString().trim();
        String comment  = etContactComment.getText().toString();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, name, comment);
        javaMailAPI.execute();
    }
}