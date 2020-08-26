package rx.classes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * CAR CLASS
 * Defines all properties and methods of a car.
 *
 * @author AK
 * @version 1.0
 */

public class Car {
    // Fields
    private String ID;
    private String model;
    private int horsePower;
    private int condition;
    private int driverAbility;
    private double balance;
    private String textColor;
    private String bgColor;

    // Constructors
    public Car () {}

    public Car(String id, String model, int hp, int cond, int driverAbility, double balance) {
        this.ID = id;
        this.model = model;
        this.horsePower = hp;
        this.condition = cond;
        this.driverAbility = driverAbility;
        this.balance = balance;
    }

    // Getters and Setters
    public String getId() {
        return  this.ID;
    }

    public void setId(String id) {
        this.ID = id;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return this.horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getCondition() {
        return this.condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getDriverAbility() {
        return this.driverAbility;
    }

    public void setDriverAbility(int driverAbility) {
        this.driverAbility = driverAbility;
    }

    public String getBalance() {
        return String.format(Locale.ITALY,"Balance: %.2f",balance);
    }

    public double getBalanceFloat() {
        return this.balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTextColor() {
        return this.textColor;
    }

    public void setTextColor(String color) {
        this.textColor = color;
    }

    public String getBgColor() {
        return this.bgColor;
    }

    public void setBgColor(String color) {
        this.bgColor = color;
    }

    // Methods/Usability

    /**
     * Saves car properties to text file (used during car creation).
     */
    public void SaveEntry(){
        try{
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Constants.carDataFile, true), StandardCharsets.UTF_8),true);
            pw.printf(Locale.US,"%s,%s,%d,%d,%d,%.2f,%d,%d,%s,%s%n",ID,model,horsePower,condition,driverAbility,balance,horsePower,driverAbility,"no color","no color");
            System.out.println("Entry successfully saved!");
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (Exception e1){
            System.out.println("Something went wrong.");
        }
    }

    /**
     * Displays details of car, in a form of a table.
     */
    @Override
    public String toString() {
        // initializing variables
        StringBuilder details = new StringBuilder();
        String[] lines = {"","","","","",""};
        StringBuilder border = new StringBuilder();
        int lineLength = 0; // table length (number of asterisks)

        // designing table
        lines[0] = "** ID: " + ID + " **";
        lines[1] = "** Model: " + model + " **";
        lines[2] = "** Horsepower: " + horsePower + " HP**";
        lines[3] = "** Condition: " + condition;
        if (condition < 50) {
            lines[3] += " (heavy damage) **";
        } else if (condition < 85) {
            lines[3] += " (slight damage) **";
        } else {
            lines[3] += " (no damage) **";
        }
        lines[4] = "** Driver Ability: " + driverAbility +" **";
        lines[5] = String.format(Locale.ITALY,"** Balance: %.2f  **",balance);

        // finding longest line
        for (String line:lines) {
            if (line.length() > lineLength) {
                lineLength = line.length();
            }
        }

        // designing borders and adding padding to lines
        for (int i=0; i<lineLength; i++) {
            border.append("*");
        }
        border.append("\n");
        for (int i=0; i<lines.length; i++) {
            lines[i] = lines[i].substring(0,lines[i].length() - 2);
            while (lines[i].length() < lineLength-2) {
                lines[i] += " ";
            }
            lines[i] += "**";

        }

        // returning table
        details.append(border);
        for (String line:lines) {

            details.append(line);
            details.append("\n");

        }
        details.append(border);
        return details.toString();
    }

    /**
     * NOT COMPLETE
     * Registers car to a race.
     */
    public void RegisterToRace() {

    }

    /**
     * NOT COMPLETE
     * Race function. Calculates score of car based on its properties (mainly condition, driverAblility and horsepower).
     * Car with biggest score wins.
     * @return score of car. Highest score wins the race
     */
    public int RunRace(){
        return 0;
    }

    /**
     * Calculates cost of car repair. Each point costs X credits (depends on horsepower).
     * @return cost of repairs
     */
    public float CalculateRepairCost() {
        if (horsePower < 100) {
            return (float) (100 - condition) * 20;
        } else if (horsePower < 200) {
            return (float) (100 - condition) * 35;
        } else if (horsePower < 300) {
            return (float) (100 - condition) * 50;
        } else if (horsePower < 400) {
            return (float) (100 - condition) * 75;
        } else if (horsePower < 500) {
            return (float) (100 - condition) * 95;
        } else {
            return (float) (100 - condition) * 100;
        }
    }

    /**
     * Calculates cost of car upgrade. Each point costs X credits (depends on class).
     * @return cost of upgrade for 1 HP
     */
    public float CalculateUpgradeCost() {
        if (horsePower < 100) {
            return (float) 150;
        } else if (horsePower < 200) {
            return (float) 180;
        } else if (horsePower < 300) {
            return (float) 220;
        } else if (horsePower < 400) {
            return (float) 280;
        } else if (horsePower < 500) {
            return (float) 350;
        } else {
            return (float) 500;
        }
    }

    /**
     * Calculates the max allowed horsepower for this car. Maximum is 25% above the original value (at car creation).
     * @return max allowed horsepower for this car
     */
    public int CalculateMaxHorsepower() throws IOException {
        int initHP = CarLoading.GetInitialHorsepower(ID);
        return (int) ((float) 125/100 * initHP);
    }

    /**
     * Restores the user's car to perfect condition.
     */
    public void RepairCar() {
        this.condition = 100;
    }

    /**
     * Updates car's balance.
     * @param amount by how much the balance increases or decreases
     */
    public void UpdateBalance(double amount) {
        this.balance = balance + amount;
    }
}
