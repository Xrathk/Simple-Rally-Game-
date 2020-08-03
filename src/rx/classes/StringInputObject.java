package rx.classes;

public class StringInputObject {
    // Fields
    String StringInput;
    boolean inputValid;

    // Constructor
    public StringInputObject() {
    }

    // Getters/Setters
    public String getStringInput() {
        return StringInput;
    }

    public boolean isInputValid() {
        return inputValid;
    }

    public void setStringInput(String StringInput) {
        this.StringInput = StringInput;
    }

    public void setInputValidity(boolean inputValid) {
        this.inputValid = inputValid;
    }
}
