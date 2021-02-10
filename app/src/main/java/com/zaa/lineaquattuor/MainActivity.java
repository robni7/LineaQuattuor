package com.zaa.lineaquattuor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView infoTextView;
    private TableLayout boardTableLayout;

    private void info(String theInfo) {
        infoTextView.setText(theInfo);
    }

    private ImageView getBoardImageView(int theRowIndex, int theColumnIndex) {
        TableRow tableRow = (TableRow) boardTableLayout.getChildAt(theRowIndex);
        return (ImageView) tableRow.getChildAt(theColumnIndex);
    }

    private void showDisc(int theRow, int theColumn, int theColor) { // set the background color of specified cell
        getBoardImageView(theRow - 1, theColumn - 1).setBackgroundColor(theColor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoTextView = findViewById(R.id.info_textview); // Haalt de XML-textview op
        info("Player one is up."); // Betekent zoveel als infoTextView.setText(" Player etc.");
        boardTableLayout = findViewById(R.id.board_tablelayout); // Het speelbord, dus de tabel uit de XML

    }

}