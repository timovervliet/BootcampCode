package exercises;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class Controles {

    @Test
    public void checkNumber(){
        int testInt = 42;
        Assertions.assertThat(testInt).as("Number of participants").isLessThan(40);

    }

    @Test
    public void checkBool(){
        boolean testBln = false;
        Assertions.assertThat(testBln).as("Selection box is disabled").isTrue();
    }

    @Test
    public void checkText(){
        String testString = "Welcome the Selenium Course";
        Assertions.assertThat(testString).as("Checking String compare").doesNotContain("Selenium");
    }
}
