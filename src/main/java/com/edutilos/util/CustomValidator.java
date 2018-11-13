package com.edutilos.util;


import javafx.scene.control.TextField;

public class CustomValidator {
    public static String validateLong(String txt) {
        try {
            Long.parseLong(txt);
            return "";
        } catch(Exception ex) {
            return ex.getMessage();
        }
    }

    public static String validateLong(TextField field) {
        return validateLong(field.getText());
    }

    public static String validateString(String txt) {
        if(txt.isEmpty()) {
            return "string is empty";
        } else {
            return "";
        }
    }
    public static String validateString(TextField field) {
        return validateString(field.getText());
    }

    public static String validateInt(String txt) {
        try {
            Integer.parseInt(txt);
            return "";
        } catch(Exception ex) {
            return ex.getMessage();
        }
    }
    public static String validateInt(TextField field) {
        return validateInt(field.getText());
    }
    public static String validateDouble(String txt) {
        try {
            Double.parseDouble(txt);
            return "";
        } catch(Exception ex) {
            return ex.getMessage();
        }
    }
    public static String validateDouble(TextField field) {
        return validateDouble(field.getText());
    }
    public static String validateBoolean(String txt) {
        if(txt.equalsIgnoreCase("true") ||
                txt.equalsIgnoreCase("false"))
        return "";
        return "Wrong boolean value";
    }
    public static String validateBoolean(TextField field) {
        return validateBoolean(field.getText());
    }
}
