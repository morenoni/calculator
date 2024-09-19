package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;

import java.util.Scanner;
import java.io.PrintWriter;

/**
 * A project for CSC-207 2024Fa.
 * Filename: InteractiveCalculator.java
 * Author: Nicole Moreno Gonzalez
 * Description: Takes a line of operations with fractions
 * and integers, and then thorws the result.
 * Type of input: line by line until QUIT.
 */
public class InteractiveCalculator {

  /**
   * Main Method: Implements Interactive Calculator program.
   * In particular it takes inputs, commands, operators,
   * handles errors and arrenges the outputs.
   * @param args are command-line arguments.
   */
  public static void main(String[] args) {
    InteractiveCalculator ic = new InteractiveCalculator();
    Scanner scanner = new Scanner(System.in);

    ic.println("Welcome to Interactive Calculator. Type QUIT to exit.");
    while (true) {
      ic.initPen();
      String line = scanner.nextLine();
      if (line.equalsIgnoreCase("QUIT")) {
        ic.println("Exiting calculator.");
        scanner.close();
        return;
      } // if
      if (line.startsWith("STORE") || line.startsWith("store")) {
        ic.storeValue(line);
        continue;
      } // if
      if (!line.contains(" ")) {
        BigFraction value = null;
        if (ic.isRegister(line)) {
          value = ic.getCalculator().getRegister(line.charAt(0));
        } else {
          ic.getCalculator().clear();
          ic.getCalculator().add(new BigFraction(line));
          value = ic.getCalculator().get();
        } // if
        ic.println("" + value);
        continue;
      } // if
      String[] values = line.split(" ");
      boolean isSuccessful = ic.compute(values);

      if (!isSuccessful) {
        ic.println("*** ERROR [Invalid expression] ***");
        continue;
      } // if
      ic.println("" + ic.getCalculator().get());
    } // while
  } // Main Method

  /**
   * Calculator variable type BFCalculator.
   */
  private BFCalculator calculator;
  /**
   * pen variable type printwriter.
   */
  private PrintWriter pen;

  /**
   * Makes possible procedure sharing between ic and qc.
   * @return calculator.
   */
  public BFCalculator getCalculator() {
    return calculator;
  } // Method getCalculator();

  /**
   * Interactive Calculator constructor.
   */
  public InteractiveCalculator() {
    calculator = new BFCalculator();
    pen = new PrintWriter(System.out, true);
  } // Method InteractiveCalculator

  /**
   * Prints a string.
   * @param str is a string.
   */
  public void println(String str) {
    pen.println(str);
  } // Method println

  /**
   * Prints '>' that means: enter input.
   */
  public void initPen() {
    pen.print("> ");
    pen.flush();
  } // initPen

  /**
   * Checks for storing commands.
   * @param line storing input.
   */
  public void storeValue(String line) {
    String[] parts = line.split(" ");

    if (parts.length != 2 || !isRegister(parts[1])) {
      pen.println("Invalid STORE command.");
      return;
    } else if (parts[1].charAt(0) < 'a' || parts[1].charAt(0) > 'z') {
      pen.println("*** ERROR [STORE command received invalid register] ***");
      return;
    } // if

    calculator.store(parts[1].charAt(0));
    System.err.println("STORED");
  } // Method storeValue

  /**
   * Checks if there's a valid operator in an argument.
   * @param tokens command-line arguments
   * @return true if a valid argument, otherwise false.
   */
  public boolean compute(String[] tokens) {
    if (tokens.length % 2 == 0) {
      return false;
    } // if

    calculator.clear();

    String operator = null;
    BigFraction firstOperando = null;
    BigFraction secondOperando = null;

    for (String token : tokens) {
      BigFraction value = null;
      if (isOperator(token)) {
        operator = token;
      } else if (!isRegister(token)) {
        value = new BigFraction(token);
      } else {
        value = calculator.getRegister(token.charAt(0));
        if (value == null || value.toString() == "0") {
          return false;
        } // if
      } // if
      if (operator == null) {
        firstOperando = value;
        calculator.add(firstOperando);
      } else {
        secondOperando = value;
      } // if
      if (secondOperando != null) {
        if (operator == null || firstOperando == null) {
          return false;
        } // if
        applyOperation(secondOperando, operator);
      } // if
    } // for

    if (secondOperando == null) {
      return false;
    } // if

    return true;
  } // Method Compute

  /**
   * Checks if a character is a valid operator.
   * @param token operator string.
   * @return true if valid operator, false otherwise.
   */
  private boolean isOperator(String token) {
    return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
  } // Method isOperator

  /**
   * Determines if an argument contains a valid register.
   * @param token register substring.
   * @return true if register valid, false otherwise.
   */
  public boolean isRegister(String token) {
    return token.length() == 1 && Character.isLetter(token.charAt(0));
  } // Method isRegister

  /**
   * Does the corresponding operation told by the input.
   * If operator is null, no operation is done.
   * @param right fraction to the right of the operator.
   * @param operator basic math operator.
   */
  private void applyOperation(BigFraction right, String operator) {
    switch (operator) {
      case "+":
        calculator.add(right);
        break;
      case "-":
        calculator.subtract(right);
        break;
      case "*":
        calculator.multiply(right);
        break;
      case "/":
        calculator.divide(right);
        break;
      default:
        System.err.println("*** ERROR [Invalid Operation] ***");
        break;
    } // switch
  } // Method applyOperation
} // Class InteractiveCalculator
