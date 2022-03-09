package com.barsha.first_android_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.barsha.first_android_application.helper.DatabaseHelper;
import com.barsha.first_android_application.helper.Userinfo;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    LinearLayout userListContainer;
    DatabaseHelper databaseHelper;
    ImageView imageView;


    ArrayList<Userinfo> userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        databaseHelper = new DatabaseHelper(this);
        userListContainer = findViewById(R.id.userListcontainer);
        userlist = databaseHelper.getUserList();

        displayUsers();

    }

    public void displayUsers() {
        for (int i = 0; i < userlist.size(); i++) {
            Userinfo info = userlist.get(i);
        }
        for (Userinfo info : userlist) {
            View view = LayoutInflater.from(this).inflate(R.layout.list_item_layout, null);

            TextView username = view.findViewById(R.id.username);
            TextView password = view.findViewById(R.id.password);
            TextView firstname = view.findViewById(R.id.firstname);
            TextView lastname = view.findViewById(R.id.lastname);
            TextView email = view.findViewById(R.id.email);
            imageView = findViewById(R.id.imageView);

            username.setText(info.username);
            password.setText(info.password);
            firstname.setText(info.firstname);
            lastname.setText(info.lastname);
            email.setText(info.email);
            if(info.image != null) {
                //imageView.setImageBitmap(DatabaseHelper.getBitmap(info.image));
                Glide.with(this).load(DatabaseHelper.getBitmap(info.image))
                        .circleCrop()
                        .into(imageView);

            }

            userListContainer.addView(view);
        }

    }

}