package com.example.agendate_app.Interfaces;


import android.view.View;

import androidx.fragment.app.Fragment;

public interface _RVListener {
    void onRVItemClick(Fragment fragment, View view, Object object, int position);

    void onRVItemLongClick(Fragment fragment, View view, Object object, int position);
}
