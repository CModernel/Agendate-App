package com.example.agendate_app.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.agendate_app.Database._DBHelper;
import com.example.agendate_app.Fragments.MainFragment;
import com.example.agendate_app.R;
import com.example.agendate_app.Utils._Utils;

public class LoginFragment  extends Fragment {
    View myView;
    Button btn_Login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_login, container, false);

        btn_Login = (Button) myView.findViewById(R.id.frg_login_btn_enter);

        _DBHelper dbHelper = new _DBHelper(_Utils.getContext());
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        dbHelper.truncate(database);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _Utils.fragment(new MainFragment());
            }
        });

        return myView;
    }
}
