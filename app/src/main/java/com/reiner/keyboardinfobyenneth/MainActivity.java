package com.reiner.keyboardinfobyenneth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvKeyboards = (ListView) findViewById(R.id.lvKeyboards);
        TextView txtDefault = (TextView) findViewById(R.id.txtDefault);

        InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
        List<InputMethodInfo> list =imeManager.getEnabledInputMethodList();

        List<String> keyboardInfos = new ArrayList<String>();

        for(InputMethodInfo i: list)
        {
            keyboardInfos.add("PackageName: " + i.getPackageName() + " ID: " + i.getId());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, keyboardInfos);
        lvKeyboards.setAdapter(adapter);

        String defaultKeyboardID = Settings.Secure.getString(getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
        txtDefault.setText("Default Keyboard ID: " + defaultKeyboardID);
    }
}