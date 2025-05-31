package com.gustavo.luan.validation;

public class It_Is {

    public static boolean isNumeric(String strNumber) {

        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
