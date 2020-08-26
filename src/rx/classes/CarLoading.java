package rx.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.HashMap;


public class CarLoading {
    /**
     * This function checks if a car ID the user entered exists
     * @param ID the id the user entered
     * @return if this ID is in the data
     */
    public static boolean VerifyCar(String ID) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(Constants.carDataFile));
        String line;
        while ((line = br.readLine()) != null) {
            String[] CarData = line.split(",");
            if (CarData[0].equals(ID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function loads a car from the data.
     * @param ID the ID the user entered
     * @return the car with this ID
     */
    public static Car LoadCar(String ID) throws IOException {
        Car userCar = new Car();
        BufferedReader br = new BufferedReader(new FileReader(Constants.carDataFile));
        String line;
        while ((line = br.readLine()) != null) {
            String[] CarData = line.split(",");
            if (CarData[0].equals(ID)) {
                userCar.setId(CarData[0]);
                userCar.setModel(CarData[1]);
                userCar.setHorsePower(Integer.parseInt(CarData[6]));
                userCar.setCondition(Integer.parseInt(CarData[3]));
                userCar.setDriverAbility(Integer.parseInt(CarData[7]));
                userCar.setBalance(Float.parseFloat(CarData[5]));
                userCar.setTextColor(CarData[8]);
                userCar.setBgColor(CarData[9]);
            }
        }
        return userCar;
    }

    /**
     * This function gets the initial horsepower of a car.
     * @param ID the ID the user entered
     * @return this car's initial horsepower
     */
    public static int GetInitialHorsepower(String ID) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(Constants.carDataFile));
        String line;
        while ((line = br.readLine()) != null) {
            String[] CarData = line.split(",");
            if (CarData[0].equals(ID)) {
                return Integer.parseInt(CarData[2]);
            }
        }
        return 0;
    }

    /**
     * Updates the properties of a car (HP,DA,condition, colors and balance). (IN PROGRESS)
     * @param ID of car to modift
     * @param property which property changes
     * @param value new value to condition, HP, etc
     * @param cost how much it costed (substracted from balance)
     */
    public static void UpdateCar(String ID, String property, int value, float cost) throws IOException {
        String[] oldLine = {"","","","","","","","","",""};
        String[] newLine = {"","","","","","","","","",""};
        StringBuilder builder = new StringBuilder();
        // Getting initial entry
        try (BufferedReader br = new BufferedReader(new FileReader(Constants.carDataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
                String[] CarData = line.split(",");
                if (CarData[0].equals(ID)) {
                    oldLine[0] = CarData[0];
                    oldLine[1] = CarData[1];
                    oldLine[2] = CarData[2];
                    oldLine[3] = CarData[3];
                    oldLine[4] = CarData[4];
                    oldLine[5] = CarData[5];
                    oldLine[6] = CarData[6];
                    oldLine[7] = CarData[7];
                    oldLine[8] = CarData[8];
                    oldLine[9] = CarData[9];
                }
            }
        }
        String fileContents = builder.toString();
        // Creating new entry
        newLine[0] = oldLine[0];
        newLine[1] = oldLine[1];
        newLine[2] = oldLine[2];
        newLine[3] = oldLine[3];
        newLine[4] = oldLine[4];
        newLine[5] = oldLine[5];
        newLine[6] = oldLine[6];
        newLine[7] = oldLine[7];
        newLine[8] = oldLine[8];
        newLine[9] = oldLine[9];

        // Finding which property to change
        HashMap<Integer, String> colorCodes = ColoringMethods.InitColorCodesReverse(); // if colors are found, load matching hashmap
        switch(property){
            case "Condition":
                newLine[3] = Integer.toString(value);
                break;
            case "Driver Ability":
                newLine[7] = Integer.toString(value);
                break;
            case "HorsePower":
                newLine[6] = Integer.toString(value);
                break;
            case "TextColor":
                newLine[8] = colorCodes.get(value);
                break;
            case "BgColor":
                newLine[9] = colorCodes.get(value);
                break;
            default:
                break;
        }

        // Calculating new balance
        newLine[5] = Float.toString(Float.parseFloat(oldLine[5]) - cost);
        //Replacing the old line with new line
        String oldLineEntry = String.join(",",oldLine);
        String newLineEntry = String.join(",",newLine);
        fileContents = fileContents.replaceAll(oldLineEntry, newLineEntry);
        // Writing to file
        try (FileWriter writer = new FileWriter(Constants.carDataFile); BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(fileContents);

        } catch (IOException e) {
            System.out.println("Something went wrong...");
        }
    }
}
