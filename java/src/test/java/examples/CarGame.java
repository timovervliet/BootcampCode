package examples;

import org.testng.annotations.Test;

public class CarGame {

    @Test
    public void startAuto(){
        Auto auto = new Auto();
        auto.setColor("Black");
        auto.setBrand("BMW");
        auto.calculateTorque(172,6000);
    }

    @Test
    public void startCarNow(){
        Auto.runWithOutInit();
    }

    @Test
    public void startBetterAuto(){
        AutoAdvanced autoAdvanced = new AutoAdvanced("Renault", 200, 60000);
    }
}
