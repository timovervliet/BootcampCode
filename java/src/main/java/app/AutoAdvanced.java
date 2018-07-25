package app;

public class AutoAdvanced {

    public String brand;

    public AutoAdvanced(String brand, int hP, int rPM){
        this.brand = brand;
        System.out.println("Brand of your car is " + brand);
        askQuestion();
        calculateTorque(hP, rPM);
    }

    public void askQuestion(){
        System.out.println("Which type is your " + brand + "?");
    }

    public void calculateTorque(int hP, int rPM){

        if (hP < 1 || rPM < 100){
            System.out.println("Invalid metrics");
        } else{
            int torque = (hP * 5252)/rPM;
            System.out.println("The torque of your car is: " + torque);
        }
    }
}
