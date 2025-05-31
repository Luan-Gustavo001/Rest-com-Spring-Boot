package com.gustavo.luan.controllers;

import com.gustavo.luan.conversion.ConvertTo;
import com.gustavo.luan.exception.UnsupportedMathOperationException;
import com.gustavo.luan.operations.SimpleMath;
import com.gustavo.luan.validation.It_Is;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private SimpleMath math = new SimpleMath();

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) {

        if (!It_Is.isNumeric(numberOne) || !It_Is.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Por favor, informe valores numéricos");

        return math.sum(ConvertTo.covertToDouble(numberOne),ConvertTo.covertToDouble(numberTwo));
    }

    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) {

        if (!It_Is.isNumeric(numberOne) || !It_Is.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Por favor, informe valores numéricos");
        return math.subtraction(ConvertTo.covertToDouble(numberOne),ConvertTo.covertToDouble(numberTwo));
    }

    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) {

        if (!It_Is.isNumeric(numberOne) || !It_Is.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Por favor, informe valores numéricos");
        return math.division(ConvertTo.covertToDouble(numberOne),ConvertTo.covertToDouble(numberTwo));
    }

    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) {

        if (!It_Is.isNumeric(numberOne) || !It_Is.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Por favor, informe valores numéricos");
        return math.multiplication(ConvertTo.covertToDouble(numberOne),ConvertTo.covertToDouble(numberTwo));
    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo) {

        if (!It_Is.isNumeric(numberOne) || !It_Is.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Por favor, informe valores numéricos");
        return math.mean(ConvertTo.covertToDouble(numberOne),ConvertTo.covertToDouble(numberTwo));
    }

    @RequestMapping("/raiz/{numberOne}")
    public Double raiz(
            @PathVariable("numberOne") String number) {

        if (!It_Is.isNumeric(number))
            throw new UnsupportedMathOperationException("Por favor, informe valores numéricos");
        return math.raiz(ConvertTo.covertToDouble(number));
    }
}
