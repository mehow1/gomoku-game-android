package com.example.micha.musiol_michal_pgm;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GomokuAI {

    public String[][] gameBoard;
    public int sizeo;
    protected String aiSign;
    protected String playerSign;
    List<AiOpen4> aiOpen4s;
    List<AiOpen3> aiOpen3s;
    List<AiOpenDouble2> aiOpenDoule2s;
    List<AiMax> aiMaxes;
    List<AiOpen4> pOpen4s;
    List<AiOpen3> pOpen3s;
    List<AiOpenDouble2> pOpenDoule2s;
    List<AiMax> pMaxes;
    int finalX;
    int finalY;
    int lvl;

    public GomokuAI(String[][] gameBoard, int sizeo, String aiSign, String playerSign, int lvl)
    {
        this.gameBoard = gameBoard;
        this.sizeo = sizeo;
        this.aiSign = aiSign;
        this.playerSign = playerSign;
        aiOpen4s = new ArrayList<AiOpen4>();
        aiOpen3s = new ArrayList<AiOpen3>();
        aiOpenDoule2s = new ArrayList<AiOpenDouble2>();
        aiMaxes = new ArrayList<AiMax>();
        pOpen4s = new ArrayList<AiOpen4>();
        pOpen3s = new ArrayList<AiOpen3>();
        pOpenDoule2s = new ArrayList<AiOpenDouble2>();
        pMaxes = new ArrayList<AiMax>();
        this.lvl = lvl;
        finalX = 0;
        finalY = 0;
    }

    public void UpdateBoard(String[][] gameBoard)
    {
        this.gameBoard = gameBoard;
    }

    private void setXY(int x, int y)
    {
        finalX = x;
        finalY = y;
    }

    public int getX() {
        return finalX;
    }


    public int getY()
    {
        return finalY;
    }

    public void makeMove() {
        SecureRandom r = new SecureRandom();
        int a = 0,b = 0;
        if(lvl == 1) {
            do {

                a = r.nextInt(15);
                b = r.nextInt(15);

            } while (gameBoard[a][b] == aiSign || gameBoard[a][b] == playerSign);

            setXY(a, b);
        }

        if(lvl == 2)
        {
            searchBoardAi();
            findMax();
        }

    }

    public void findSpec(String direction, int row, int columne, String s) {

        int counter = 0;
        for(int a = 0; a < 4; a++) {
            if (direction == "vertical") {
                if (gameBoard[row + a][columne] == s)
                counter++;
            }

            if (direction == "horizontal") {
                if (gameBoard[row][columne + a] == s)
                counter++;
            }

            if (direction == "crossedr") {
                if (gameBoard[row + a][columne + a] == s)
                counter++;
            }

            if (direction == "crossedup") {
                if (gameBoard[row - a][columne - a] == s)
                counter++;
            }
        }

        if(direction == "vertical")
        {
            if(gameBoard[row+counter+1][columne] == " ")
            {
                AiMax am = new AiMax(row-1,columne,s, counter);
                pMaxes.add(am);
            }
        }

        if(direction == "horizontal")
        {
            if(gameBoard[row][columne+counter+1] == " ")
            {
                AiMax am = new AiMax(row-1,columne,s, counter);
                pMaxes.add(am);
            }
        }

        if(direction == "crossedr")
        {
            if(gameBoard[row+counter+1][columne+counter+1] == " ")
            {
                AiMax am = new AiMax(row-1,columne,s, counter);
                pMaxes.add(am);
            }
        }

        if(direction == "crossedup")
        {
            if(gameBoard[row-counter-1][columne-counter-1] == " ")
            {
                AiMax am = new AiMax(row-1,columne,s, counter);
                pMaxes.add(am);
            }
        }

    }

    public void findMax()
    {
        int index = 0;
        int max = 0;
        int counter = 0;
        for (AiMax am : aiMaxes) {

            if(am.count >= max)
            {
                max = am.count;
                setXY(am.x,am.y);
            }


        }

    }

    public void searchBoardAi()
    {
        int counter = 0;
        for(int a = 0; a < sizeo; a++)
        {
            for(int b = 0; b < sizeo; b++)
            {
                if(b < sizeo-4)
                {
                    findSpec("horizontal",a,b,aiSign);
                }

                if(a < sizeo - 4)
                {
                    findSpec("vertical",a,b,aiSign);
                }

                if(b < sizeo -4 && a < sizeo -4)
                {
                    findSpec("crossedr",a,b,aiSign);
                }

                if(a < sizeo - 4 && b > 3)
                {
                    findSpec("crossedup",a,b,aiSign);
                }
            }
        }
    }

}

class AiOpen4 {

    String sign;
    int x;
    int y;

    public AiOpen4(int x, int y, String s)
    {
        this.sign = s;
        this.x = x;
        this.y = y;
    }

}

class AiOpen3 {

    String sign;
    int x;
    int y;

    public AiOpen3(int x, int y, String s)
    {
        this.sign = s;
        this.x = x;
        this.y = y;
    }

}

class AiOpenDouble2{

    String sign;
    int x;
    int y;

    public AiOpenDouble2(int x, int y, String s)
    {
        this.sign = s;
        this.x = x;
        this.y = y;
    }
}

class AiMax{

    int count;
    String sign;
    int x;
    int y;

    public AiMax(int x, int y, String s, int count)
    {
        this.count = count;
        this.sign = s;
        this.x = x;
        this.y = y;
    }
}