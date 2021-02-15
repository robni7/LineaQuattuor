package com.zaa.lineaquattuor;

public class FourInARowGame {

    private int[] savedNumberOfEmptyColumnCells = {6, 6, 6, 6, 6, 6, 6};

    private int[][] board = new int[6][7]; // Array of empty cells by rows resp. columns. Val: 0 = no disc, 1 = disc P1, 2 = disc P2

    private int playerNumber = 1; // We start the game with Player 1, naturally
    private int playerWhoWon = 0; // Currently, nobody has won
    private int emptyColumnCells; // The number of empty column cells at the last disk drop action
    private int lastColumn; // The last column that the disk was dropped in

    private int[][] cellIndicesWhoWon = new int[][]{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}}; // An array of four two-element arrays


    public int dropDisc(int theColumn) {
        if (theColumn >= 1 && theColumn <= 7) { // We need to only accept a valid input
            lastColumn = theColumn; // Remember the last column that the disk was dropped in
            emptyColumnCells = savedNumberOfEmptyColumnCells[theColumn - 1]; // Haal het aantal lege cellen op (min 1 want arrays beginnen met 0) en plaats in tijdelijke int
            if (emptyColumnCells > 0) { // If there are any empty cells left in this column, then the drop is valid. We will do the following:
                savedNumberOfEmptyColumnCells[theColumn - 1] = emptyColumnCells - 1; // Decrement the saved array by one
                board[emptyColumnCells - 1][theColumn - 1] = playerNumber; // Record the player number for the selected cell (row count; column)
                checkForEndOfGame();
                playerNumber = 3 - playerNumber; // Will always return the opposite player through the magic of math
            }
            return emptyColumnCells;
        }
        return 0; // we need to return something, if anything is not OK we'll return nought
    }

    private boolean currentPlayerWon() {
        // Step 1 of remembering the 4-in-a-row cells: remember the current cell
        cellIndicesWhoWon[3][0] = emptyColumnCells - 1;
        cellIndicesWhoWon[3][1] = lastColumn - 1;
        return fourInARowHorizontally() || fourInARowVertically() || fourInARowDiagonally() || fourInARowReverseDiagonally();
    }

    private boolean isBoardFull() {
        for (int columnIndex = 6; columnIndex >= 0; columnIndex--) { // Repeat 6 times, decreasing columnIndex value by one
            if (savedNumberOfEmptyColumnCells[columnIndex] > 0) {
                return false;
            }
        }
        return true; // Wanneer er voor elke kolom geen lege cel meer kan worden gevonden, dan isBoardFull = TRUE.
    }

    public void checkForEndOfGame() {
        if (currentPlayerWon() == true) {
            playerWhoWon = playerNumber; // We have a winner!
        } else if (isBoardFull() == true) {
            playerWhoWon = -1; // We have a draw...
        }
    }

    public int getResult() { // This subroutine will, when called, return 0 for when the game has not ended yet, 1 for P1, 2 for P2, -1 for draw.
        return playerWhoWon;
    }

    private boolean fourInARowHorizontally() {
        int leftToFind = 3, rowIndex = emptyColumnCells - 1;

        // check to the left
        int columnIndex = lastColumn - 2; // first column index to the left
        while (leftToFind > 0 && columnIndex >= 0 && board[rowIndex][columnIndex] == playerNumber) {
            leftToFind--;
            // remember the cell occupied by a disc of the current player
            cellIndicesWhoWon[leftToFind][0] = rowIndex;
            cellIndicesWhoWon[leftToFind][1] = columnIndex;
            columnIndex--;
        }
        if (leftToFind == 0) return true;

        // check to the right
        columnIndex = lastColumn; // first column index to the right
        while (leftToFind > 0 && columnIndex <= 6 && board[rowIndex][columnIndex] == playerNumber) {
            leftToFind--;
            // remember the cell occupied by a disc of the current player
            cellIndicesWhoWon[leftToFind][0] = rowIndex;
            cellIndicesWhoWon[leftToFind][1] = columnIndex;
            columnIndex++;
        }
        return (leftToFind == 0);
    }

    private boolean fourInARowVertically() {
        int leftToFind = 3, columnIndex = lastColumn - 1;
        // check up
        int rowIndex = emptyColumnCells - 2;
        while (leftToFind > 0 && rowIndex >= 0 && board[rowIndex][columnIndex] == playerNumber) {
            leftToFind--;
            // remember the cell occupied by a disc of the current player
            cellIndicesWhoWon[leftToFind][0] = rowIndex;
            cellIndicesWhoWon[leftToFind][1] = columnIndex;
            rowIndex--;
        }
        if (leftToFind == 0) {
            return true;
        }
        // check down
        rowIndex = emptyColumnCells;
        while (leftToFind > 0 && rowIndex <= 5 && board[rowIndex][columnIndex] == playerNumber) {
            leftToFind--;
            // remember the cell occupied by a disc of the current player
            cellIndicesWhoWon[leftToFind][0] = rowIndex;
            cellIndicesWhoWon[leftToFind][1] = columnIndex;
            rowIndex++;
        }
        return (leftToFind == 0);
    }

    private boolean fourInARowDiagonally() {
        int leftToFind = 3, rowIndex, columnIndex;
        // check left and up
        rowIndex = emptyColumnCells - 2;
        columnIndex = lastColumn - 2;
        while (leftToFind > 0 && rowIndex >= 0 && columnIndex >= 0 &&
                board[rowIndex][columnIndex] == playerNumber) {
            leftToFind--;
            // remember the cell occupied by a disc of the current player
            cellIndicesWhoWon[leftToFind][0] = rowIndex;
            cellIndicesWhoWon[leftToFind][1] = columnIndex;
            rowIndex--;
            columnIndex--;
        }
        if (leftToFind == 0) return true;
        // check right and down
        rowIndex = emptyColumnCells;
        columnIndex = lastColumn;
        while (leftToFind > 0 && rowIndex <= 5 && columnIndex <= 6 &&
                board[rowIndex][columnIndex] == playerNumber) {
            leftToFind--;
            // remember the cell occupied by a disc of the current player
            cellIndicesWhoWon[leftToFind][0] = rowIndex;
            cellIndicesWhoWon[leftToFind][1] = columnIndex;
            rowIndex++;
            columnIndex++;
        }
        return (leftToFind == 0);
    }

    private boolean fourInARowReverseDiagonally() {
        int leftToFind = 3, rowIndex, columnIndex;
        // check left and down
        rowIndex = emptyColumnCells;
        columnIndex = lastColumn - 2;
        while (leftToFind > 0 && rowIndex <= 5 && columnIndex >= 0 &&
                board[rowIndex][columnIndex] == playerNumber) {
            leftToFind--;
            // remember the cell occupied by a disc of the current player
            cellIndicesWhoWon[leftToFind][0] = rowIndex;
            cellIndicesWhoWon[leftToFind][1] = columnIndex;
            rowIndex++;
            columnIndex--;
        }
        if (leftToFind == 0) return true;
        // check right and up
        rowIndex = emptyColumnCells - 2;
        columnIndex = lastColumn;
        while (leftToFind > 0 && rowIndex >= 0 && columnIndex <= 6 &&
                board[rowIndex][columnIndex] == playerNumber) {
            leftToFind--;
            // remember the cell occupied by a disc of the current player
            cellIndicesWhoWon[leftToFind][0] = rowIndex;
            cellIndicesWhoWon[leftToFind][1] = columnIndex;
            rowIndex--;
            columnIndex++;
        }
        return (leftToFind == 0);
    }
}
