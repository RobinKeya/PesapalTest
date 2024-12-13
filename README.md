# Arbitrary Precision Integer Calculator in Java

## Overview
An **arbitrary-precision calculator** is a type of calculator capable of performing mathematical operations on integers of any size, far exceeding the limits of standard primitive data types like `int` or `long`. In this project, we implement an arbitrary-precision integer calculator in Java without relying on external libraries for the core mathematical operations. The implementation wraps the functionality in a **Read-Eval-Print Loop (REPL)** for interactive usage.

### Why Arbitrary Precision?
Primitive data types in Java, such as `int` and `long`, are limited in the range of numbers they can represent:
- `int`: -2,147,483,648 to 2,147,483,647
- `long`: -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807

For calculations involving numbers beyond these ranges, an arbitrary-precision solution is necessary. This program handles large integers using strings to represent numbers and custom algorithms for arithmetic.

---

## How to Run the Program
1. **Clone or Download the Code:**
   Save the file `ArbitraryPrecisionCalculator.java` in your local directory.

2. **Compile the Program:**
   ```bash
   javac ArbitraryPrecisionCalculator.java
   ```

3. **Run the Program:**
   ```bash
   java ArbitraryPrecisionCalculator
   ```

4. **Interactive Mode:**
   - Type mathematical expressions directly in the REPL prompt.
   - Supported operations include addition, subtraction, multiplication, division, modulus, exponentiation, factorial, and logarithm.
   - Type `exit` to terminate the program.

---

## Features
### 1. Addition (`+`)
Add two large integers.
- **Example:**
  ```
  >> 999999999999999999 + 1
  1000000000000000000
  ```

### 2. Subtraction (`-`)
Subtract two large integers, supporting negative results.
- **Example:**
  ```
  >> 1000000000000000000 - 999999999999999999
  1
  ```

### 3. Multiplication (`*`)
Multiply two large integers.
- **Example:**
  ```
  >> 123456789 * 987654321
  121932631112635269
  ```

### 4. Division (`/`)
Perform integer division between two large integers.
- **Example:**
  ```
  >> 123456789 / 1000
  123456
  ```

### 5. Modulus (`%`)
Find the remainder of division between two large integers.
- **Example:**
  ```
  >> 10 % 3
  1
  ```

### 6. Exponentiation (`^`)
Calculate a large number raised to an integer power.
- **Example:**
  ```
  >> 2 ^ 10
  1024
  ```

### 7. Factorial (`!`)
Calculate the factorial of a large integer.
- **Example:**
  ```
  >> 5 !
  120
  ```

### 8. Logarithm (`log`)
Compute the logarithm of a number with a specified base (integer-based logarithm).
- **Example:**
  ```
  >> 1000 log 10
  3
  ```

### 9. Base Conversion (`base`)
Convert a number to a different numerical base (2 to 36).
- **Example:**
  ```
  >> 255 base 16
  FF
  ```

---

## Error Handling
The program includes comprehensive error handling for invalid inputs or operations:
- **Invalid Input Format:**
  ```
  Error: Invalid input. Use the format: <number1> <operation> <number2> Or <number> !
  ```
- **Unsupported Operations:**
  ```
  Error: Unsupported operation: @
  ```
- **Division by Zero:**
  ```
  Error: Division by zero
  ```
- **Invalid Base for Conversion or Logarithm:**
  ```
  Error: Base must be between 2 and 36
  ```

---

## Limitations
1. **Non-Integer Inputs:**
   - The calculator does not support floating-point numbers or operations on fractions.

2. **Performance:**
   - Operations involving extremely large integers (e.g., millions of digits) may become slow due to the custom implementation.

3. **Advanced Math Functions:**
   - Functions like square roots, trigonometry, and advanced logarithms are not implemented.

---
