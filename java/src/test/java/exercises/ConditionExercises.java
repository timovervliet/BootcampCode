package exercises;

import org.testng.annotations.Test;

/**
 * Created by MiesMSI on 10-7-2017.
 */
public class ConditionExercises {


    public void bootcampAgeChecker(int age){
        if (age > 21){
            System.out.println("Deelnemer toegelaten");
        }
        else{
            System.out.println("te jong");
        }
    }

    @Test
    public void testMember(){
        bootcampAgeChecker(24);
        bootcampAgeChecker(19);
    }

    public void bootcampAgeCheckerAdvanced(int age){
        if (age >= 65){
            System.out.println("Deelnemer niet toegelaten, check bij de manager.");
        }
        else if(age > 21){
            System.out.println("Deelnemer toegelaten");
        }
        else{
            System.out.println("te jong.");
        }
    }

    @Test
    public void testMemberAdvanced(){
        bootcampAgeCheckerAdvanced(19);
        bootcampAgeCheckerAdvanced(21);
        bootcampAgeCheckerAdvanced(65);
        bootcampAgeCheckerAdvanced(66);
    }
}
