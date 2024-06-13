package com.consultoriaTi.gestao.util.validator;

public class CPFValidator {
    public static boolean isValidCPF(String cpf) {

        String pattern = "^[0-9\\-\\.]+$";
        if(!cpf.matches(pattern)){
            return false;
        }

        // Remove any formatting from the CPF string
        cpf = cpf.replaceAll("[^0-9]", "");

        // Check that the CPF has 11 digits
        if (cpf.length() != 11) {
            return false;
        }

        // Check that all the digits are the same
        for (int i = 0; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                break;
            }
            if (i == 10) {
                return false;
            }
        }

        // Calculate the first digit verifier
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit == 10 || firstDigit == 11) {
            firstDigit = 0;
        }

        // Calculate the second digit verifier
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit == 10 || secondDigit == 11) {
            secondDigit = 0;
        }

        // Check that the calculated verifiers match the ones in the CPF
        if (Character.getNumericValue(cpf.charAt(9)) != firstDigit
                || Character.getNumericValue(cpf.charAt(10)) != secondDigit) {
            return false;
        }

        return true;
    }

}
