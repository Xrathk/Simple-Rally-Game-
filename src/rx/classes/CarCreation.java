package rx.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarCreation {

    /**
     * This function is the car creation menu.
     */
    public static void CreateCar() throws IOException{
        boolean IdCondition; // checks if CarID user enters starts with a character
        boolean IdCondition2; // checks if ID already exists
        boolean HpCondition; // checks if horsepower is between 50 and 600 (acceptable numbers)
        boolean Condition0_100; // checks if condition and driver ability are between 0 and 100 (min is 0, max is 100)
        float initialBalance; // initialbalance, depending on what the user entered
        List<String> idList =  getIDs(); // List with IDs that already exist
        // idList.forEach(System.out::println); // prints all IDs that are in the data


        System.out.println("\nEnter the details of your car:\n");

        // Get Car ID
        System.out.println("Car ID:");
        StringInputObject CarID;
        IdCondition = false;
        CarID = InputMethods.isStringInputValid();
        // Checking conditions
        if (CarID.getStringInput().charAt(0) >= 'A' && CarID.getStringInput().charAt(0) <= 'Z') {
            IdCondition = true;
        } else {
            System.out.println("Car ID invalid. Your car's ID should start with a capital letter!");
        }
        if (idList.contains(CarID.getStringInput())) {
            IdCondition2 = false;
            System.out.println("This Car ID already exists! Pick another ID for your car!");
        } else {
            IdCondition2 = true;
        }

        // Checking Conditions
        while (!CarID.isInputValid()  || !IdCondition || !IdCondition2) {
            System.out.println("Enter a valid Car ID:");
            CarID = InputMethods.isStringInputValid();
            if (CarID.getStringInput().charAt(0) >= 'A' && CarID.getStringInput().charAt(0) <= 'Z') {
                IdCondition = true;
            } else {
                IdCondition = false;
                System.out.println("Car ID invalid. Your car's ID should start with a capital letter!");
            }
            if (idList.contains(CarID.getStringInput())) {
                IdCondition2 = false;
                System.out.println("This Car ID already exists! Pick another ID for your car!");
            } else {
                IdCondition2 = true;
            }
        }
        System.out.println("Car ID set!\n");

        // Get Car Model
        System.out.println("Car Model:");
        StringInputObject CarModel;
        CarModel = InputMethods.isStringInputValid();
        while (!CarModel.isInputValid()) {
            System.out.println("Enter a valid car model:");
            CarModel = InputMethods.isStringInputValid();
        }
        System.out.println("Car model set!\n");

        // Get Horsepower
        System.out.println("Car Horsepower:");
        IntegerInputObject CarHP;
        HpCondition = false;
        CarHP = InputMethods.isIntegerInputValid();
        if (CarHP.isInputValid()) {
            if (CarHP.getIntegerInput() >= 50 && CarHP.getIntegerInput() <= 600) {
                HpCondition = true;
            } else {
                System.out.println("The number you entered is off limits. Horsepower should be between 50 and 600 HP.");
            }
        }
        while (!CarHP.isInputValid() || !HpCondition) {
            System.out.println("Enter a valid number for horsepower:");
            CarHP = InputMethods.isIntegerInputValid();
            if (CarHP.isInputValid()) {
                if (CarHP.getIntegerInput() >= 50 && CarHP.getIntegerInput() <= 600) {
                    HpCondition = true;
                } else {
                    HpCondition = false;
                    System.out.println("The number you entered is off limits. Horsepower should be between 50 and 600 HP.");
                }
            }
        }
        System.out.println("Car horsepower set!\n");

        // Get Condition
        System.out.println("Car Condition:");
        IntegerInputObject CarCond;
        Condition0_100 = false;
        CarCond = InputMethods.isIntegerInputValid();
        if (CarCond.isInputValid()) {
            if (CarCond.getIntegerInput() >= 0 && CarCond.getIntegerInput() <= 100) {
                Condition0_100 = true;
            } else {
                System.out.println("The number you entered is off limits. Car condition should be between 0 and 100.");
            }
        }
        while (!CarCond.isInputValid() || !Condition0_100) {
            System.out.println("Enter a valid number for the car's condition:");
            CarCond = InputMethods.isIntegerInputValid();
            if (CarCond.isInputValid()) {
                if (CarCond.getIntegerInput() >= 0 && CarCond.getIntegerInput() <= 100) {
                    Condition0_100 = true;
                } else {
                    Condition0_100 = false;
                    System.out.println("The number you entered is off limits. Car condition should be between 0 and 100.");
                }
            }
        }
        System.out.println("Car condition set!\n");

        // Get DriverAbility
        System.out.println("Car Driver Ability:");
        IntegerInputObject CarDA;
        Condition0_100 = false;
        CarDA = InputMethods.isIntegerInputValid();
        if (CarDA.isInputValid()) {
            if (CarDA.getIntegerInput() >= 0 && CarDA.getIntegerInput() <= 100) {
                Condition0_100 = true;
            } else {
                Condition0_100 = false;
                System.out.println("The number you entered is off limits. Driver should be between 0 and 100.");
            }
        }
        while (!CarDA.isInputValid() || !Condition0_100) {
            System.out.println("Enter a valid number for the car driver's ability:");
            CarDA = InputMethods.isIntegerInputValid();
            if (CarDA.isInputValid()) {
                if (CarDA.getIntegerInput() >= 0 && CarDA.getIntegerInput() <= 100) {
                    Condition0_100 = true;
                } else {
                    System.out.println("The number you entered is off limits. Driver should be between 0 and 100.");
                }
            }
        }
        System.out.println("Car driver ability set!\n");

        // Saving new Car
        System.out.println("Car created! Saving entry...");
        initialBalance = getInitialBalance(CarHP.getIntegerInput(),CarCond.getIntegerInput(),CarDA.getIntegerInput());
        Car newCar = new Car(CarID.getStringInput(),CarModel.getStringInput(),CarHP.getIntegerInput(),CarCond.getIntegerInput(),CarDA.getIntegerInput(),initialBalance);
        newCar.SaveEntry();
    }

    /**
     * Calculates the initial balance of a car based on what the user entered:
     * @param HP car horsepower
     * @param Cond car condition
     * @param DrivAb car driver ability
     */
    public static float getInitialBalance(int HP, int Cond, int DrivAb) {
        float init; // initial balance
        // higher HP cars cost more to repair, so start with higher balance
        if (HP < 100) {
            init = 10000;
        } else if (HP < 200) {
            init = 15000;
        } else if (HP < 300) {
            init = 20000;
        } else if (HP < 400) {
            init = 30000;
        } else {
            init = 35000;
        }
        init += (100 - Cond) * 10; // user gets an additional 10 credits for every lost point in condition
        init += (100 - DrivAb) * 100; // user gets and additional 100 credits for every lost point in driver ability
        return init;
    }

    public static List<String> getIDs() throws IOException {
        List<String> idList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(Constants.carDataFile));
        String line;
        while ((line = br.readLine()) != null) {
            String[] CarData = line.split(",");
            idList.add(CarData[0]);
        }
        return idList;
    }
}
