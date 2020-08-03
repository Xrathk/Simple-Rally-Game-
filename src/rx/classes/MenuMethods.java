package rx.classes;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.nio.file.*;
import java.io.File;

/**
 * Class with various menu methods.
 *
 * @author AK
 * @version 1.0
 * @see CarInitializer
 */

public class MenuMethods {

    /**
     * This class shows the main menu.
     */
    public static void ShowMainMenu() throws IOException{
        int choice;
        String[] options = {"Create New Car", "Load Car", "Create New Race", "Load Race", "Exit"};

        // Show titles
        DisplayTitle("AKG");
        WaitTime(2);
        DisplayTitle("Rally RX");
        WaitTime(2);

        // Show main menu options
        System.out.println("Welcome to Rally RX!!!\n");
        CheckDataAvailability(); // checking for data files
        System.out.println("What would you like to do?");
        do {
            // Get user input, if 5 is the input exit program
            Scanner in = new Scanner(System.in);
            ShowMenuOptions(options);
            try {
                choice = in.nextInt();
            } catch (Exception e) {
                choice = 0;
            }
            switch (choice) {
                case 1:  // Create car
                    System.out.println("Creating car...");
                    CarCreation.CreateCar();
                    break;
                case 2:  // Load car
                    boolean carExists;
                    Car userCar;
                    System.out.println("Loading car data...\n");
                    // Get Car ID from user
                    System.out.println("Enter a Car ID:");
                    StringInputObject UserCarID;
                    UserCarID = InputMethods.isStringInputValid();
                    while (!UserCarID.isInputValid()) {
                        System.out.println("Enter a valid Car ID:");
                        UserCarID = InputMethods.isStringInputValid();
                    }
                    // Check if Car ID exists in data
                    carExists = CarLoading.VerifyCar(UserCarID.getStringInput());
                    // Load car if it exists, print error message if it doesn't
                    if (carExists) {
                        userCar = CarLoading.LoadCar(UserCarID.getStringInput());
                        System.out.printf("Car with ID:%s loaded!%n",UserCarID.getStringInput());
                        // Enter customization screen
                        CarCustomization.Customization(userCar);
                    } else {
                        System.out.println("The ID you entered doesn't correspond to a car. Please enter a valid car ID.");
                    }

                    break;
                case 3: // Create race
                    System.out.println("Creating race...");
                    break;
                case 4: // Load race
                    System.out.println("Loading race data...");
                    break;
                case 5:  // Exit
                    System.out.println("Have a nice day!");
                    in.close();
                    break;
                default:
                    System.out.println("Please enter a proper number from 1 to 5.");
                    break;
            }
        } while (choice != 5);

        // Exit program
        System.exit(1);
    }

    /**
     * Adds a time delay.
     * @param timeout how many seconds of delay
     */
    public static void WaitTime(int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong! Exitting...");
        } catch (Exception e1) {
            System.out.println("Program stopped working. Exitting...");
        }
    }

    /**
     * Displays a title (f.e. company name) in stylized format.
     * Displayed title will be 40 characters, with the title at the middle.
     * Format: |---Title---|
     * @param title to be displayed
     */
    public static void DisplayTitle(String title) {
        int spareLength = 40 - title.length();
        int titleStart = (int) Math.ceil((float) spareLength/2-1);
        StringBuilder newTitle = new StringBuilder();
        if (title.length()>=36) {
            newTitle.append("|");
            newTitle.append(title);
            newTitle.append("|");
        } else {
            for (int i = 0; i < 40; i++) {
                if (i == 0 || i == 39) {
                    newTitle.append("|");
                } else if ((i<= titleStart) || (i>=40-spareLength/2)) {
                    newTitle.append("-");
                } else if (i==(int) Math.ceil((float) spareLength/2)) {
                    newTitle.append(title);
                }
            }
        }
        System.out.println();
        System.out.println(Constants.ANSI_BLACK_BACKGROUND + Constants.ANSI_YELLOW + newTitle.toString() + Constants.ANSI_RESET);
        System.out.println();
    }

    /**
     * Displays available menu options for user.
     * @param options array of strings to be displayed (one line for each option).
     */
    public static void ShowMenuOptions(String[] options) {
        System.out.println();
        for (int i=0; i<options.length; i++) {
            System.out.printf("%d. %s%n",i+1,options[i]);
        }
        System.out.println();
    }

    /**
     * Checks if data files exist. Creates them if they don't exist.
     */
    public static void CheckDataAvailability() {
        Path path = Paths.get(Constants.carDataFile);

        System.out.println("Checking for data files...");
        if (Files.exists(path)) {
            // File exists
            System.out.println("Data files found.\n");
        }

        if (Files.notExists(path)) {
            // File doesn't exist
            System.out.println("Data files not found. Creating data files...");
            try {
                File myObj = new File(Constants.carDataFile);
                if (myObj.createNewFile()) {
                    System.out.println("Data files created.\n");
                }
            } catch (IOException e) {
                System.out.println("Error: Could not create data files. Exitting...");
                System.exit(0);
            }

        }

    }

}


