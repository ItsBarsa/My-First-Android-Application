package com.barsha.first_android_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.barsha.first_android_application.helper.DatabaseHelper;


public class RegisterActivity extends AppCompatActivity {

    EditText username, password, repassword, firstname, lastname, email;
    RadioGroup gender;
    Button register;
    TextView backtologin;

    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        databaseHelper = new DatabaseHelper(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        gender = findViewById(R.id.gender);
        register = findViewById(R.id.register);
        backtologin = findViewById(R.id.backtologin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (emptyFieldValidation(username) && passwordValidation(password) && passwordValidation(repassword) && emptyFieldValidation(firstname) && emptyFieldValidation(lastname) && isvalidEmail(email)) {
                    if (password.getText().toString().equals(repassword.getText().toString())) {
                        String usernameValue = username.getText().toString();
                        String passwordValue = password.getText().toString();
                        String firstnameValue = firstname.getText().toString();
                        String lastnameValue = lastname.getText().toString();
                        String emailValue = email.getText().toString();

                        RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
                        String genderValue = checkedBtn.getText().toString();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", usernameValue);
                        editor.putString("password", passwordValue);
                        editor.putString("firstname", firstnameValue);
                        editor.putString("lastname", lastnameValue);
                        editor.putString("email", emailValue);
                        editor.putString("gender", genderValue);
                        editor.commit();

                        ContentValues contentValues = new ContentValues();
                        contentValues.put("username", usernameValue);
                        contentValues.put("password", passwordValue);
                        contentValues.put("firstname", firstnameValue);
                        contentValues.put("lastname", lastnameValue);
                        contentValues.put("email", emailValue);
                        contentValues.put("gender", genderValue);
                        databaseHelper.insertUser(contentValues);

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);


                        Toast.makeText(RegisterActivity.this, "user Registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();

                    }

                }
            }

        });

        backtologin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }


    public boolean emptyFieldValidation(EditText view) {
        if (view.getText().toString().length() > 0) {
            return true;
        } else {
            view.setError("This field can't be empty");
            return false;
        }
    }

    public boolean isvalidEmail(EditText view) {

        String emailValue = view.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
            return true;
        } else {
            view.setError("Invalid email address");
            return false;
        }
    }

    public boolean passwordValidation(EditText view) {
        if (view.getText().toString().length() > 6) {
            return true;
        } else {
            view.setError("The character must not be less than 6");
            return false;
        }


    }

}

