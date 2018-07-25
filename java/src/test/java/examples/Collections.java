package examples;


import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class Collections {

    @Test
    public void workWithArrays(){
        //create the array - mandatory length
        String[] gamesArray = new String[5];

        //fill the array
        gamesArray[1] = "Zelda";
        gamesArray[3] = "Tekken";

        // print the array - is there a smarter way?
        System.out.println(gamesArray[0]);
        System.out.println(gamesArray[1]);
        System.out.println(gamesArray[2]);
        System.out.println(gamesArray[3]);

    }

    @Test
    public void workWithLists(){
        //create the List - check the imports!
        List<String> gamesList = new ArrayList<String>();

        //fill the List
        gamesList.add("Super Mario World");
        gamesList.add("Luigi's Mansion");

        //print the list - is there a smarter way? -> Loops.class
        System.out.println(gamesList.get(0));
        System.out.println(gamesList.get(1));
        //System.out.println(gamesList.get(2));
    }
}
