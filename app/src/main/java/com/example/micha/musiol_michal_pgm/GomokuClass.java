package com.example.micha.musiol_michal_pgm;

import java.util.List;

/**
 * Created by Michał on 2017-01-11.
 */

public  class GomokuClass {

    public static int checkState(String[][] gameBoard, int size)
    {
        for(int a = 0; a < size; a++)
        {
            for(int b = 0; b < size; b++)
            {
                if(b < size-4)
                {
                    if(gameBoard[a][b] == gameBoard[a][b+1] && gameBoard[a][b+2] == gameBoard[a][b+1]
                            && gameBoard[a][b+3] == gameBoard[a][b+2] && gameBoard[a][b+4] == gameBoard[a][b+3])
                    {
                        if(gameBoard[a][b] == "O")
                            return 1;

                        if(gameBoard[a][b] == "X")
                            return 2;
                    }
                }

                if(a < size - 4)
                {
                    if(gameBoard[a][b] == gameBoard[a+1][b] && gameBoard[a+2][b] == gameBoard[a+1][b]
                            && gameBoard[a+3][b] == gameBoard[a+2][b] && gameBoard[a+4][b] == gameBoard[a+3][b])
                    {
                        if(gameBoard[a][b] == "O")
                            return 1;

                        if(gameBoard[a][b] == "X")
                            return 2;
                    }
                }

                if(b < size -4 && a < size -4)
                {
                    if(gameBoard[a][b] == gameBoard[a+1][b+1] && gameBoard[a+2][b+2] == gameBoard[a+1][b+1]
                            && gameBoard[a+3][b+3] == gameBoard[a+2][b+2] && gameBoard[a+4][b+4] == gameBoard[a+3][b+3])
                    {
                        if(gameBoard[a][b] == "O")
                            return 1;

                        if(gameBoard[a][b] == "X")
                            return 2;
                    }
                }

                if(a < size - 4 && b > 3)
                {
                    if(gameBoard[a][b] == gameBoard[a+1][b-1] && gameBoard[a+2][b-2] == gameBoard[a+1][b-1]
                            && gameBoard[a+3][b-3] == gameBoard[a+2][b-2] && gameBoard[a+4][b-4] == gameBoard[a+3][b-3])
                    {
                        if(gameBoard[a][b] == "O")
                            return 1;

                        if(gameBoard[a][b] == "X")
                            return 2;
                    }
                }
            }
        }

        return 0;
    }

    public static void makeMove(String[][] gameBoard,String currentSign, int x, int y){ gameBoard[x][y] = currentSign;}

    public static int getRow(String id)
    {
        int row;
        if(id.substring(1,2) == "0")
            row = Integer.parseInt(id.substring(2,3));
        else
            row = Integer.parseInt(id.substring(1,3));

        return row;
    }

    public static int getColumne(String id)
    {
        int columne;
        if(id.substring(3,4) == "0")
            columne = Integer.parseInt(id.substring(4));
        else
            columne = Integer.parseInt(id.substring(3));

        return columne;
    }

    public static boolean isButtonFree(String[][] gameBoard, int x, int y)
    {
        if(gameBoard[x][y] == "")
            return true;
        else
            return false;
    }

    public static String addMoveToHistory(String gameHistory, String id)
    {
        int x = getRow(id);
        int y = getColumne(id);
        char row =(char)(x+97);
        gameHistory += " ";
        gameHistory += row;
        gameHistory += "" + y;
        return gameHistory;
    }

    public static String addMoveToHistory(String gameHistory,int q)
    {
        gameHistory += "" + q;
        gameHistory += "" + ".";
        return gameHistory;
    }

}
