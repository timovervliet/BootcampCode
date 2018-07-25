package examples;


public class Auto {

    public void setColor(String carColor){
        System.out.println("The color of your car is: " + carColor);
    }

    public void setBrand(String carBrand){
        System.out.println("The brand of your car is: " + carBrand);
    }

    public void calculateTorque(int hP, int rPM){

        if (hP < 1 || rPM < 100){
            System.out.println("Invalid metrics");
        } else{
            int torque = (hP * 5252)/rPM;
            System.out.println("The torque of your car is: " + torque);
        }
    }

    //static can be calles witout having to declare the class
    public static void runWithOutInit(){
        System.out.println("Vroem vroem");
    }
}
