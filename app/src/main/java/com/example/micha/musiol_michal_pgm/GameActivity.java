package com.example.micha.musiol_michal_pgm;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    String gameHistory = "";
    int queue = 0;
    String[][] gameBoard;
    boolean isGameEnded = false;
    int size = 15;
    int cellSize;
    Button buttons[][];
    Button ngbutton;
    Button okbutton;
    int currentId = 0;
    Boolean isCurrent = false;
    String currentSign = "O";
    int lvl;
    GomokuAI ai;

    public void makeAiMove()
    {
        ai.makeMove();
        gameBoard[ai.getX()][ai.getY()] = currentSign;
        buttons[ai.getX()][ai.getY()].setBackgroundColor(Color.BLUE);
        ai.UpdateBoard(gameBoard);
        changeSign();
    }

    public void checkState()
    {
        Toast t1 = Toast.makeText(getApplicationContext(),"Wygral gracz 1", Toast.LENGTH_LONG);
        Toast t2 = Toast.makeText(getApplicationContext(),"Wygral gracz 2", Toast.LENGTH_LONG);
        int result = GomokuClass.checkState(gameBoard,size);

        if(result == 1)
            endGame(1);

        if(result == 2)
            endGame(2);
    }

    public void changeSign()
    {
        if(currentSign == "O")
            currentSign = "X";
        else
            currentSign = "O";
    }

    public void clearBoard()
    {
        for(int a = 0 ; a< size; a++)
        {
            for(int b = 0; b < size; b++)
            {
                gameBoard[a][b] = "";
                buttons[a][b].setBackgroundColor(Color.LTGRAY);
            }
        }
    }

    public void endGame(int winner)
    {
        Toast t1 = Toast.makeText(getApplicationContext(),"Wygral gracz czerwony", Toast.LENGTH_LONG);
        Toast t2 = Toast.makeText(getApplicationContext(),"Wygral gracz niebieski", Toast.LENGTH_LONG);

        if(winner == 1) {
            t1.show();
            okbutton.setText("Wygral gracz czerwony");
        }
        else {
            t2.show();
            okbutton.setText("Wygrał gracz niebieski");
        }

        okbutton.setEnabled(false);
        isGameEnded = true;
    }

    public void queueCounter()
    {
        if(currentSign == "X")
        {
            queue ++;
            gameHistory = GomokuClass.addMoveToHistory(gameHistory,queue);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast t1 = Toast.makeText(getApplicationContext(),"Wygral gracz 1", Toast.LENGTH_LONG);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameBoard = new String[size][size];

        okbutton = (Button) findViewById(R.id.okbutton);
         lvl = getIntent().getExtras().getInt("lvl");
        okbutton.setEnabled(false);

        buttons = new Button[size][size];
        gameBoard = new String[size][size];
        LinearLayout mll = (LinearLayout) findViewById(R.id.mll);
        cellSize = mll.getWidth()/size;
        ai = new GomokuAI(gameBoard,size,"O","X",lvl);

        ngbutton = (Button) findViewById(R.id.ngbutton);
        ngbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBoard();
                okbutton.setEnabled(false);
                isGameEnded = false;
                currentSign = "X";
                okbutton.setText("Ok");
                ai.UpdateBoard(gameBoard);
            }
        });

        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) findViewById(currentId);
                GomokuClass.makeMove(gameBoard,currentSign,GomokuClass.getRow(String.valueOf(currentId)),GomokuClass.getColumne(String.valueOf(currentId)));

                if(currentSign == "X")
                    button.setBackgroundColor(Color.BLUE);
                else
                    button.setBackgroundColor(Color.RED);

                queueCounter(); // dodaje numer ruchu do histori gier
                gameHistory = GomokuClass.addMoveToHistory(gameHistory,currentId);
                changeSign();
                okbutton.setEnabled(false);
                isCurrent = false;
                checkState();
                if(lvl != 0)
                    makeAiMove();
            }
        });

        for(int a = 0; a < size; a++)
        {
            LinearLayout ll = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,26,0);
            ll.setLayoutParams(params);
            ll.setPadding(0,0,0,0);
            mll.addView(ll);

            for(int b = 0; b < size; b++)
            {
                gameBoard[a][b] = "";
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(26,26,0);
                params2.setMargins(1,1,1,1);
                buttons[a][b] = new Button(getApplicationContext());
                buttons[a][b].setText("");
                buttons[a][b].setBackgroundColor(Color.LTGRAY);
                String anumber = "", bnumber = "";

                if(a < 10)
                    anumber = "0" + String.valueOf(a);
                else
                    anumber = String.valueOf(a);

                if(b < 10)
                    bnumber = "0" + String.valueOf(b);
                else
                    bnumber = String.valueOf(b);

                String fId = "1" + anumber + bnumber;
                int finId = Integer.parseInt(fId);

                buttons[a][b].setId(finId);

                buttons[a][b].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Button button = (Button) view;
                        int x = GomokuClass.getRow(String.valueOf(button.getId()));
                        int y = GomokuClass.getColumne(String.valueOf(button.getId()));
                        if(GomokuClass.isButtonFree(gameBoard,x,y) && isGameEnded == false) {
                            if (isCurrent == true) {

                                Button currentButton = (Button) findViewById(currentId);
                                currentButton.setBackgroundColor(Color.LTGRAY);
                                button.setBackgroundColor(Color.argb(75, 64, 255, 76));
                                currentId = button.getId();
                                okbutton.setEnabled(true);
                            } else {
                                button.setBackgroundColor(Color.argb(75, 64, 255, 76));
                                currentId = button.getId();
                                isCurrent = true;
                                okbutton.setEnabled(true);
                            }
                        }
                    }
                });

                ll.addView(buttons[a][b], params2);
            }
        } // koniec rysowania planszy

    }
}
