package com.zaa.lineaquattuor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView infoTextView;

    private void info(String info) {
        infoTextView.setText(info);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoTextView = findViewById(R.id.info_textview); // Haalt de XML-textview op
        info(" Player one is up."); // Betekent zoveel als infoTextView.setText(" Player etc."); Quick hack: spatie voor het bericht tbv gebrek aan padding.
    }

}