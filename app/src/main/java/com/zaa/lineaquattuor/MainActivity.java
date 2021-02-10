package com.zaa.lineaquattuor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView infoTextView;
    private TableLayout boardTableLayout;
    private Button columnButton;
    private int column;
    private int mycolor;

if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        mycolor = (getColor(R.color.teal_200));
    } else {
        mycolor = (getResources().getColor(R.color.teal_200));
    }

    private String columnLetter;


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

    public void columnButtonClicked(View theView) {
        columnButton = (Button) theView;

        // Perhaps a clunky way of sorting this through
        columnLetter = columnButton.getText().toString();
        switch (columnLetter) {
            case "A":
                column = 1;
                break;
            case "B":
                column = 2;
                break;
            case "C":
                column = 3;
                break;
            case "D":
                column = 4;
                break;
            case "E":
                column = 5;
                break;
            case "F":
                column = 6;
                break;
            case "G":
                column = 7;
                break;
        }

        // further code to make the move and process the row returned
        if (mycolor == ContextCompat.getColor(getResources(), R.color.teal_200)) {
            showDisc(6, column, mycolor);
            mycolor = ContextCompat.getColor(getResources(), R.color.aubergine_200);
            info("All eyes on you, player two! ");
        } else {
            showDisc(6, column, mycolor);
            mycolor = ContextCompat.getColor(getResources(), R.color.teal_200);
            info("Show ’em how it’s done, player one!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoTextView = findViewById(R.id.info_textview); // Haalt de XML-textview op
        info("Player one, you’re up!"); // Betekent zoveel als infoTextView.setText(" Player etc.");
        boardTableLayout = findViewById(R.id.board_tablelayout); // Het speelbord, dus de tabel uit de XML

    }

}