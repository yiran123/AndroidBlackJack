package com.example.blackjack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layout;
    TextView card1,card2;
    TextView score;
    GestureDetector gd;
    Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new Model();
        gd = new GestureDetector(this,new MyClick());
        layout = (RelativeLayout) findViewById(R.id.rl);
        card1 = (TextView) findViewById(R.id.card1);
        card2 = (TextView) findViewById(R.id.card2);
        score = (TextView) findViewById(R.id.score);
        Button game = (Button) findViewById(R.id.game);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });
    }
    private void newGame(){
        model.prepare();
        card1.setVisibility(View.INVISIBLE);
        card2.setVisibility(View.INVISIBLE);
        score.setText("Score:"+model.getScore());
        layout.removeAllViews();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!model.isPlaying()){
            Toast.makeText(this,"Your have not started a new game!",Toast.LENGTH_LONG).show();
            return true;
        }
        return gd.onTouchEvent(event);
    }

    private void createCardView(String num){
        TextView tv = new TextView(this);
        tv.setText(num);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(card1.getWidth(),card1.getHeight());
        params.setMargins(layout.getChildCount()*80,0,0,0);
        tv.setLayoutParams(params);
        tv.setPadding(10,5,0,0);
        tv.setTextSize(25);
        tv.setBackgroundResource(R.drawable.border);
        layout.addView(tv);
    }
    public class MyClick extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //TODO
//            Toast.makeText(MainActivity.this,"22",Toast.LENGTH_SHORT).show();
            //execute when the card < 2
            if(model.getShowCardNum()<2){
                String card11 = model.createCard();

                card1.setText(card11);
                card1.setVisibility(View.VISIBLE);
                String card22 = model.createCard();

                card2.setText(card22);
                card2.setVisibility(View.VISIBLE);


                calcResult();
            }
            return false;

        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if(model.getShowCardNum()<2){
                return super.onSingleTapConfirmed(e);
            }
            String card = model.createCard();
            createCardView(card);
            calcResult();
            return super.onSingleTapConfirmed(e);

        }

    }
    private void calcResult(){
        score.setText("Score:"+model.getScore());
        int result = model.calcResult();
        if(result==-1){
            Toast.makeText(MainActivity.this,"You Lose",Toast.LENGTH_LONG).show();
            return;
        }else if(result==1){
            Toast.makeText(MainActivity.this,"You Win",Toast.LENGTH_LONG).show();
        }
    }
}
