package com.example.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by YiranPan on 2017/10/6.
 */
public class Model {
    private static final String[] cards = new String[]{"A","A","A","A","2","2","2","2","3","3","3","3","4","4","4","4","5","5","5","5","6","6","6","6","7","7","7","7","8","8","8","8","9","9","9","9","10","10","10","10","J","J","J","J","Q","Q","Q","Q","K","K","K","K"};
    private List<Integer> showCard = new ArrayList<>();
    private int score;
    private boolean isPlaying;
    public String createCard(){
        Random random = new Random();
        int pos = random.nextInt(cards.length);
        while(showCard.contains(pos)){
            pos = random.nextInt(cards.length);
        }
        showCard.add(pos);
        score+=getCardScore(pos);
        return cards[pos];
    }
    private int getCardScore(int pos){
        //A
        if(pos<4){
            return 1;
        //2 3 4 5 6 7 8 9 10
        }else if(pos<40){
            return pos/4+1;
        //J Q K
        }else{
            return 10;
        }
    }
    public int getShowCardNum(){
       return showCard.size();
    }
    public int getScore(){
        return score;
    }
    /**
     * 0: continue game
     * 1: player win
     * -1:player lose
     * @return
     */
    public int calcResult(){
        if(score<17){
            return 0;
        }else if(score<=21){
            isPlaying = false;
            return 1;
        }else{
            isPlaying = false;
            return -1;
        }
    }
    public void prepare(){
        showCard.clear();
        score = 0;
        isPlaying = true;
    }
    public boolean isPlaying(){
        return isPlaying;
    }
}
