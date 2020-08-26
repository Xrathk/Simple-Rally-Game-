package rx.classes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CarCustomization {

    /**
     * Car customization screen.
     * @param userCar car the user loaded
     */
    public static void Customization(Car userCar) throws IOException {
        String[] options = {"Overview", "Repair Car", "Upgrade Car", "Customize car", "Exit back to Main Menu"};
        // Initializing color hashmaps
        HashMap<String, String> textColors = ColoringMethods.InitTextColors();
        HashMap<String, String> bgColors = ColoringMethods.InitBgColors();
        int choice;

        System.out.println();
        do {
            // Get user input, if 5 is the input exit program
            Scanner in = new Scanner(System.in);
            System.out.println("Car Customization:");
            MenuMethods.ShowMenuOptions(options);
            try {
                choice = in.nextInt();
            } catch (Exception e) {
                choice = 0;
            }
            switch (choice) {
                case 1:  // Overview
                    System.out.println(textColors.get(userCar.getTextColor()) + bgColors.get(userCar.getBgColor()) + userCar + Constants.ANSI_RESET);
                    break;
                case 2:  // RepairCar
                    String choice2;
                    if (userCar.getCondition() == 100) {
                        System.out.println("Your car is not damaged. No repairs are needed.");
                        break;
                    }
                    do {
                        float repairCost = userCar.CalculateRepairCost();
                        System.out.printf("Your car's current condition is %d.%n",userCar.getCondition());
                        System.out.printf("Would you like to repair your car? A full repair costs %.2f credits.%n",repairCost);
                        System.out.printf("Current %s%n",userCar.getBalance());
                        choice2 = InputMethods.YesOrNo().toLowerCase();
                        if (choice2.equals("yes")) {
                            // Repairing car
                            userCar.UpdateBalance(-1*repairCost);
                            userCar.RepairCar();
                            System.out.printf("Your car's new condition is %d.%n",userCar.getCondition());
                            System.out.printf("New %s%n",userCar.getBalance());
                            CarLoading.UpdateCar(userCar.getId(),"Condition",100, repairCost);
                        } else if (choice2.equals("no")) {
                            System.out.printf("Car's condition remains at: %d%n",userCar.getCondition());
                        } else {
                            System.out.println("Please enter \"Yes\" or \"No\".");
                        }
                    } while (!choice2.equals("yes") && !choice2.equals("no"));
                    break;
                case 3: // UpgradeCar
                    System.out.println("You can upgrade your car by increasing its horsepower. Do you want to proceed?");
                    choice2 = InputMethods.YesOrNo().toLowerCase();
                    if (choice2.equals("yes")) {
                        // Upgrading car
                        int choiceUp;
                        float upgradeCost = userCar.CalculateUpgradeCost();
                        int maxHP = userCar.CalculateMaxHorsepower();
                        System.out.printf("Your car's current horsepower is: %d%n",userCar.getHorsePower());
                        System.out.printf("Your car's maximum allowed horsepower is: %d%n",maxHP);
                        if (userCar.getHorsePower() >= maxHP) {
                            System.out.println("Your car is already fully upgraded!");
                            break;
                        }
                        System.out.printf("Increasing your car's horsepower by 1 costs %.2f credits. Please enter your desired value for the increase:%n",upgradeCost);
                        Scanner in4 = new Scanner(System.in);
                        try {
                            choiceUp = in4.nextInt();
                        } catch (Exception e) {
                            System.out.println("You have to enter an integer value.");
                            System.out.printf("Car's horsepower remains at: %d%n",userCar.getHorsePower());
                            break;
                        }

                        if (userCar.getHorsePower() + choiceUp > maxHP) {
                            System.out.println("You're not allowed to increase your car's horsepower by that amount!");
                            System.out.printf("Your car's maximum allowed horsepower is: %d%n",maxHP);
                            break;
                        }

                        System.out.printf("Upgrading your car's horsepower by %d would cost %.2f. Would you like to finalize the upgrade?", choiceUp, upgradeCost*choiceUp);
                        choice2 = InputMethods.YesOrNo().toLowerCase();
                        if (choice2.equals("yes")) {
                            if (userCar.getBalanceFloat() < upgradeCost*choiceUp) {
                                System.out.println("Sorry, but you can't afford these upgrades.");
                            } else {
                                System.out.println("Upgrading car...\n");
                                MenuMethods.WaitTime(2);
                                System.out.println("Upgrades complete!");
                                System.out.printf("Your car's new horsepower is: %d%n", userCar.getHorsePower() + choiceUp);
                                CarLoading.UpdateCar(userCar.getId(), "HorsePower", userCar.getHorsePower() + choiceUp, upgradeCost * choiceUp);
                            }

                        } else if (choice2.equals("no")) {
                            System.out.println("Upgrades cancelled.");
                            System.out.printf("Car's horsepower remains at: %d%n",userCar.getHorsePower());
                        } else {
                            System.out.println("Incorrect input. Upgrades cancelled.");
                        }
                    } else if (choice2.equals("no")) {
                        System.out.printf("Car's horsepower remains at: %d%n",userCar.getHorsePower());
                    } else {
                        System.out.println("Please enter \"Yes\" or \"No\".");
                    }
                    userCar = CarLoading.LoadCar(userCar.getId());
                    break;
                case 4: // Customize car
                    String choice3;
                    int coloringComplete = 0; // when both text and background have been colored (=2), we exit
                    Scanner in2 = new Scanner(System.in);
                    System.out.println("What color would you like your car to be? Your options are:");
                    System.out.println("Black | Red | Green | Yellow | Blue | Purple | Cyan | Grey | No Color | No change");
                    System.out.println("Enter your color for text (must be one of the above):");
                    // Coloring text
                    do {
                        try {
                            choice3 = in2.nextLine();
                        } catch (Exception e) {
                            choice3 = "aaa";
                        }
                        choice3 = choice3.toLowerCase();
                        String value = textColors.get(choice3);
                        if (value != null) {
                            // Valid choice, change color
                            HashMap<String, Integer> colorCodes = ColoringMethods.InitColorCodes();
                            System.out.printf("Changing text color to %s...%n",choice3);
                            System.out.println("Changes complete!\n");
                            CarLoading.UpdateCar(userCar.getId(),"TextColor", colorCodes.get(choice3), 0);
                            coloringComplete += 1;
                        } else {
                            // Invalid choice, don't change color
                            if (choice3.equals("no change")) {
                                System.out.println("Text color will remain as is.");
                                coloringComplete += 1;
                            } else {
                                System.out.println("Invalid input. Your options are:");
                                System.out.println("Black | Red | Green | Yellow | Blue | Purple | Cyan | Grey | No color | No change");
                            }
                        }
                    } while (coloringComplete != 1);
                    // Coloring background
                    System.out.println("Black | Red | Green | Yellow | Blue | Purple | Cyan | Grey | No Color | No change");
                    System.out.println("Enter your color for background (must be one of the above):");
                    do {
                        try {
                            choice3 = in2.nextLine();
                        } catch (Exception e) {
                            choice3 = "aaa";
                        }
                        choice3 = choice3.toLowerCase();
                        String value = bgColors.get(choice3);
                        if (value != null) {
                            // Valid choice, change color
                            HashMap<String, Integer> colorCodes = ColoringMethods.InitColorCodes();
                            System.out.printf("Changing background color to %s...%n",choice3);
                            System.out.println("Changes complete!\n");
                            CarLoading.UpdateCar(userCar.getId(),"BgColor", colorCodes.get(choice3), 0);
                            coloringComplete += 1;
                        } else {
                            // Invalid choice, don't change color
                            if (choice3.equals("no change")) {
                                System.out.println("Background color will remain as is.");
                                coloringComplete += 1;
                            } else {
                                System.out.println("Invalid input. Your options are:");
                                System.out.println("Black | Red | Green | Yellow | Blue | Purple | Cyan | Grey | No Color | No change");
                            }
                        }
                    } while (coloringComplete != 2);
                    System.out.println("\nAll changes saved.\n");
                    userCar = CarLoading.LoadCar(userCar.getId());
                    break;
                case 5:  // Exit
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Please enter a proper number from 1 to 5.");
                    break;
            }
        } while (choice != 5);

    }
}
