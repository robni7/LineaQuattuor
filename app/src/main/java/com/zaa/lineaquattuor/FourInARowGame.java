package com.zaa.lineaquattuor;

public class FourInARowGame {

    private int [] numberOfEmptyColumnCells = {6,6,6,6,6,6,6};

    public int dropDisc(int theColumn) {
        if (theColumn>=1 && theColumn<=7) { // We need to only accept a valid input
            int emptyColumnCells = numberOfEmptyColumnCells[theColumn-1];
        }
        return 0; // we need to return something, but for now we'll return just "some error"
    }


}
