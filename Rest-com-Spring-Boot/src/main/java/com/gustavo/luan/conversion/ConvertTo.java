package com.gustavo.luan.conversion;

import com.gustavo.luan.exception.UnsupportedMathOperationException;

public class ConvertTo {

    public static Double covertToDouble(String strNumber) throws IllegalArgumentException {

        if (strNumber == null || strNumber.isEmpty())
            throw new UnsupportedMathOperationException("Por favor, informe valores numéricos");
        String number = strNumber.replace(",",".");
        return Double.parseDouble(number);
    }

}
