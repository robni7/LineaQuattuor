package com.zaa.lineaquattuor;

// LineaQuattuor - Software by Zaa (2021)

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView infoTextView;
    private TableLayout boardTableLayout;
    private Button columnButton;
    private int column;
    private int mycolor;
    private int colorPlayer1;
    private int colorPlayer2;
    private String columnLetter;

    private FourInARowGame fourInARowGame = new FourInARowGame();

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

        int rowToDrop = fourInARowGame.dropDisc(column);

        while (((ColorDrawable)getBoardImageView(rowToDrop-1, column-1).getBackground()).getColor()!=Color.parseColor("#aaaaaa")) {
            rowToDrop--; // decrement the rowToDrop value by one for as many times as there are disks already dropped
        }

        if (rowToDrop==1) {
            columnButton.setEnabled(false); // disables the column button when the entire column is full of disks
        }

        if (mycolor == colorPlayer1) {
            showDisc(rowToDrop, column, mycolor);
            mycolor = colorPlayer2;
            info("All eyes on you, player two! ");
        } else {
            showDisc(rowToDrop, column, mycolor);
            mycolor = colorPlayer1;
            info("Show ’em how it’s done, player one!");
        }

        int gameResult = fourInARowGame.getResult();
        if (gameResult != 0) {
            endOfGame(gameResult);
        }
    }

    private void endOfGame(int theResult) {
        TableRow tableButtonRow = (TableRow) boardTableLayout.getChildAt(6);
        for (int columnIndex = 6; columnIndex >= 0; columnIndex--) {
            tableButtonRow.getChildAt(columnIndex).setEnabled(false);
        }
        if (theResult == -1) info("What a absolutely spectacular match! It ended in a tie.");
        else {
            info("What a match! Player " + theResult + " has taken the win!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoTextView = findViewById(R.id.info_textview); // Haalt de XML-textview op
        info("Player one, you’re up!"); // Betekent zoveel als infoTextView.setText(" Player etc.");
        boardTableLayout = findViewById(R.id.board_tablelayout); // Het speelbord, dus de tabel uit de XML

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            colorPlayer1 = (getColor(R.color.forest_200));
            colorPlayer2 = (getColor(R.color.aubergine_200));
        } else {
            colorPlayer1 = (getResources().getColor(R.color.forest_200));
            colorPlayer2 = (getResources().getColor(R.color.aubergine_200));
        }
        mycolor = colorPlayer1;
    }

}