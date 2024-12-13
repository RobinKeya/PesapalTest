import java.util.Objects;
import java.util.Scanner;

public class ArbitraryPrecisionCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Arbitrary Precision Integer Calculator\nType 'exit' to quit.");

        while (true) {
            System.out.print(">> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;

            try {
                System.out.println(evaluate(input));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // REPL evaluator
    private static String evaluate(String input) throws Exception {
        String[] tokens = input.split(" ");
        if (tokens.length < 3 && (tokens.length == 2 && !Objects.equals(tokens[1], "!"))) {
            throw new Exception("Invalid input. Use the format: <number1> <operation> <number2> Or <number> !");
        }

        String num1 = tokens[0];
        String operation = tokens[1];
        String num2 = tokens.length > 2 ? tokens[2] : null;

        switch (operation) {
            case "+":
                return add(num1, num2);
            case "-":
                return subtract(num1, num2);
            case "*":
                return multiply(num1, num2);
            case "/":
                return divide(num1, num2);
            case "%":
                return modulo(num1, num2);
            case "^":
                return exponentiate(num1, num2);
            case "!":
                return factorial(num1);
            case "log":
                return log(num1, num2);
            case "base":
                return convertBase(num1, num2);
            default:
                throw new Exception("Unsupported operation: " + operation);
        }
    }

    // Addition
    private static String add(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0, i = num1.length() - 1, j = num2.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            result.append(sum % 10);
            carry = sum / 10;
        }

        return result.reverse().toString();
    }

    // Subtraction
    private static String subtract(String num1, String num2) {
        if (num1.equals(num2)) return "0";

        boolean negative = false;
        if (num1.length() < num2.length() || (num1.length() == num2.length() && num1.compareTo(num2) < 0)) {
            negative = true;
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        StringBuilder result = new StringBuilder();
        int borrow = 0, i = num1.length() - 1, j = num2.length() - 1;

        while (i >= 0) {
            int digit1 = num1.charAt(i--) - '0';
            int digit2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int diff = digit1 - digit2 - borrow;

            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.append(diff);
        }

        while (result.length() > 1 && result.charAt(result.length() - 1) == '0') {
            result.setLength(result.length() - 1);
        }

        return (negative ? "-" : "") + result.reverse().toString();
    }

    // Multiplication
    private static String multiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] result = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = product + result[i + j + 1];
                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            if (!(sb.length() == 0 && num == 0)) {
                sb.append(num);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    // Division
    private static String divide(String num1, String num2) throws Exception {
        if (num2.equals("0")) {
            throw new Exception("Division by zero");
        }

        StringBuilder result = new StringBuilder();
        String dividend = "";

        for (char digit : num1.toCharArray()) {
            dividend += digit;
            int quotient = 0;

            while (compare(dividend, num2) >= 0) {
                dividend = subtract(dividend, num2);
                quotient++;
            }

            result.append(quotient);
        }

        return result.toString().replaceFirst("^0+", "");
    }

    // Modulo
    private static String modulo(String num1, String num2) throws Exception {
        return subtract(num1, multiply(divide(num1, num2), num2));
    }

    // Exponentiation
    private static String exponentiate(String base, String exp) {
        String result = "1";
        while (!exp.equals("0")) {
            result = multiply(result, base);
            exp = subtract(exp, "1");
        }
        return result;
    }

    // Factorial
    private static String factorial(String num) {
        String result = "1";
        while (!num.equals("0")) {
            result = multiply(result, num);
            num = subtract(num, "1");
        }
        return result;
    }

    // Logarithm
    private static String log(String num, String base) throws Exception {
        if (base == null || base.equals("0") || num.equals("0")) {
            throw new Exception("Invalid base or number for logarithm");
        }
        String result = "0";
        while (compare(num, base) >= 0) {
            num = divide(num, base);
            result = add(result, "1");
        }
        return result;
    }

    // Convert between bases
    private static String convertBase(String num, String newBase) throws Exception {
        int base = Integer.parseInt(newBase);
        if (base < 2 || base > 36) {
            throw new Exception("Base must be between 2 and 36");
        }

        int decimalValue = 0;
        for (char c : num.toCharArray()) {
            decimalValue = decimalValue * 10 + (c - '0');
        }

        StringBuilder result = new StringBuilder();
        while (decimalValue > 0) {
            int remainder = decimalValue % base;
            result.append(remainder < 10 ? (char) ('0' + remainder) : (char) ('A' + remainder - 10));
            decimalValue /= base;
        }

        return result.reverse().toString();
    }

    // Compare two numbers (helper method)
    private static int compare(String num1, String num2) {
        if (num1.length() != num2.length()) {
            return num1.length() - num2.length();
        }
        return num1.compareTo(num2);
    }
}
