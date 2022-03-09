package com.barsha.first_android_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {
    Button logout;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_layout);

        Log.i("Lifecycle","onCreate");

        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);

        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sharedPreferences.getBoolean("remember me", false);
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                Toast.makeText(HomeActivity.this, "you are successfully logged out", Toast.LENGTH_SHORT).show();
            }

        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle","onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Lifecycle","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle","onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu1:
                startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
                break;

            case R.id.menu2:
                showAlertDialog();
                break;

            case R.id.menu3:
                startActivity(new Intent(HomeActivity.this, FragmentExampleActivity.class));
                Toast.makeText(this, "Menu3", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu4:
                startActivity(new Intent(HomeActivity.this, UserListActivity.class));
                showCustomToast("This is custom toast");
                break;

            case R.id.menu5:

                break;

            case R.id.submenu:
                showCustomToast("submenu");
                break;

            case R.id.submenu1:
                showCustomToast("submenu1");
                break;

            case R.id.submenu2:
                showCustomToast("submenu2");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Exit application");
        dialog.setMessage("Are you sure?");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();
            }
        });
        dialog.setNegativeButton("cancel", null);
        dialog.setCancelable(false);
        dialog.show();

    }

    public void showCustomToast(String srt) {
        Toast toast = new Toast(this);
        View view = LayoutInflater.from(this).inflate(R.layout.custom_toast_design, null);
        TextView message = view.findViewById(R.id.message);
        message.setText(srt);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 100);
        toast.show();
    }

}