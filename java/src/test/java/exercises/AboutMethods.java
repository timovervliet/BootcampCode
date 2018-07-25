package exercises;

import org.testng.annotations.Test;

public class AboutMethods {

    @Test
    public void printProduct(){
        System.out.println(multiply(4,5));
        System.out.println(sum(1,2,3));
    }

    private int multiply(int numberA, int numberB){
        return numberA * numberB;
    }
    
    private int sum(int numberOne, int numberTwo, int i) {
    	return numberOne + numberTwo + i;
    }
}
