package com.barsha.first_android_application;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TopFragment extends Fragment {
    EditText username,password;
    Button login, register;
    CheckBox rememberme;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout, null);
        username = view. findViewById(R.id.username);
        password = view. findViewById(R.id.password);
        login = view. findViewById(R.id.login);
        register = view. findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = username.getText().toString();

                ((FragmentExampleActivity)getActivity()).setBottomFragmentTitle(value);
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String value = username.getText().toString();

                ((FragmentExampleActivity)getActivity()).setBottomFragmentTitle(value);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return view;
    }
}
