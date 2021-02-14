package com.zaa.lineaquattuor;

public class FourInARowGame {

    private int [] savedNumberOfEmptyColumnCells = {6,6,6,6,6,6,6};

    public int dropDisc(int theColumn) {
        if (theColumn>=1 && theColumn<=7) { // We need to only accept a valid input
            int emptyColumnCells = savedNumberOfEmptyColumnCells[theColumn-1]; // Haal het aantal lege cellen op (min 1 want arrays beginnen met 0) en plaats in tijdelijke int
            if (emptyColumnCells > 0) { // If there are any empty cells left in this column, then:
                savedNumberOfEmptyColumnCells[theColumn-1] = emptyColumnCells - 1; // Decrement the saved array by one
            }
            return emptyColumnCells;
        }
        return 0; // we need to return something, if anything is not OK we'll return nought
    }


}
