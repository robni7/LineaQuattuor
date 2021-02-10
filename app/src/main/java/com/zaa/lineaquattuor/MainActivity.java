package com.zaa.lineaquattuor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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
    private int mycolor = Color.RED;


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
        column = Integer.parseInt(columnButton.getText().toString());
        // further code to make the move and process the row returned
        if (mycolor == Color.RED) {
            showDisc(6, column, mycolor);
            mycolor = Color.BLUE;
            info("All eyes on you, player two! ");
        } else {
            showDisc(6, column, mycolor);
            mycolor = Color.RED;
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