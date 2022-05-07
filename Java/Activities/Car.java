package Activities;

public class Car {

String color;
String transmission;
int make;
int tyres;
int doors;
     Car()
     {
         tyres=4;
         doors=4;
     }
    //Class Methods
    public void displayCharacterstics(){
        System.out.println("Color of the Car: " + color);
        System.out.println("Make of the Car: " + make);
        System.out.println("Transmission of the Car: " + transmission);
        System.out.println("Number of doors on the car: " + doors);
        System.out.println("Number of tyres on the car: " + tyres);
    }
    public void accelarate(){
        System.out.println("The car is moving  Forward");
    }
    public void brake(){
    System.out.println("The car has Stopped");
    }


}
