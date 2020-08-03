package rx.classes;

import java.util.Scanner;

public class InputMethods {

    /**
     * Checks validity of string input (Handles exceptions). Also returns user's input.
     * @return StringInputObject (we want its inputValid property to be true)
     */
    public static StringInputObject isStringInputValid() {
        StringInputObject SIO = new StringInputObject();
        Scanner in = new Scanner(System.in);
        String userInput;
        // get input
        try {
            userInput = in.nextLine();
        } catch (Exception e) {
            // bad input - return false
            SIO.setStringInput("");
            SIO.setInputValidity(false);
            return SIO;
        }
        // input OK - return it
        SIO.setStringInput(userInput);
        SIO.setInputValidity(true);
        return SIO;
    }

    /**
     * Checks validity of integer input (Handles exceptions). Also returns user's input.
     * @return IntegerInputObject (we want its inputValid property to be true)
     */
    public static IntegerInputObject isIntegerInputValid() {
        IntegerInputObject IIO = new IntegerInputObject();
        Scanner in = new Scanner(System.in);
        int userInput;
        // get input
        try {
            userInput = in.nextInt();
        } catch (Exception e) {
            // bad input - return false
            IIO.setIntegerInput(-1);
            IIO.setInputValidity(false);
            return IIO;
        }
        // input OK - return it
        IIO.setIntegerInput(userInput);
        IIO.setInputValidity(true);
        return IIO;
    }
}
