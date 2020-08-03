package rx.classes;

public class IntegerInputObject {
    // Fields
    int integerInput;
    boolean inputValid;

    // Constructor
    public IntegerInputObject() {
    }

    // Getters/Setters
    public int getIntegerInput() {
        return integerInput;
    }

    public boolean isInputValid() {
        return inputValid;
    }

    public void setIntegerInput(int integerInput) {
        this.integerInput = integerInput;
    }

    public void setInputValidity(boolean inputValid) {
        this.inputValid = inputValid;
    }
}
