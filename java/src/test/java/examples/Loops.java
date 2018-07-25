package examples;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class Loops {

    @Test
    public void workWithWhileLoop(){
        //start value
        int i = 1;

        //create the while loop
        while (i < 5){
            System.out.println("currect value: " + i);
            //increment to prevent infinite loop
            i++;
        }
    }

    @Test
    public void workWithDoWhileLoop(){
        //start value
        int i = 1;

        //create the Do While loop
        do {
            System.out.println("currect value: " + i);
            //increment to prevent infinite loop
            i++;
        }
        while (i < 5);
    }

    @Test
    public void workWithForLoop(){
        //create the for loop
        for(int i = 1; i < 5; i++){
            System.out.println("currect value: " + i);
        }
    }

    @Test
    public void listItter(){
        //create the List - check the imports!
        List<String> gamesList = new ArrayList<String>();

        //fill the List
        gamesList.add("Super Mario World");
        gamesList.add("Luigi's Mansion");
        gamesList.add("Zelda");
        gamesList.add("Street Fighter");
        gamesList.add("Zelda");

        for(int i = 0; i < gamesList.size(); i++){
            System.out.println(gamesList.get(i));
        }

        for(String game : gamesList){
            System.out.println(game);
        }
    }
}
