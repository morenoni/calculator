package edu.grinnell.csc207.util;

import java.math.BigInteger;
import java.util.Objects;

/**
 * A project for CSC-207 2024Fa.
 * Filename: BigFraction.java
 * Author: Nicole Moreno Gonzalez
 * Description: Contructs fractions and
 * impletements basic operations on them.
 */
public class BigFraction {
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  private BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  private BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   * @param numerator of the fraction.
   * @param denominator of the fraction.
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;
    simplify();
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   * @param numerator of the fraction.
   * @param denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   * @param str
   * The fraction in string form
   */
  public BigFraction(String str) {
    String[] values = str.split("/");
    this.num = new BigInteger(values[0]);
    this.denom = (values.length == 1) ? BigInteger.ONE : new BigInteger(values[1]);
    simplify();
  } // BigFraction (String str)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * simplify reduces a fraction to it's simplest form.
   */
  public void simplify() {
    BigInteger gcd = num.gcd(denom); // Find the greatest common divisor
    this.num = this.num.divide(gcd); // Divide the numerator by the GCD
    this.denom = this.denom.divide(gcd); // Divide the denominator by the GCD
    // Ensure the denominator is positive
    if (denom.compareTo(BigInteger.ZERO) < 0) {  // If the denominator is negative
      this.num = this.num.multiply(BigInteger.valueOf(-1));  // Make numerator negative
      this.denom = this.denom.multiply(BigInteger.valueOf(-1));  // Make denominator positive
    } // if
  } // Method Simplify

  /**
   * Express this fraction as a double.
   * @return the fraction approxiamted as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add another faction to this fraction.
   * @param addend The fraction to add.
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;
    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(addend.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));
    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * Subtracs another faction to this fraction.
   * @param subtrahend The fraction to add.
   * @return the result of the subtraction.
   */
  public BigFraction subtract(BigFraction subtrahend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;
    resultDenominator = this.denom.multiply(subtrahend.denom);
    resultNumerator =
    (this.num.multiply(subtrahend.denom)).subtract(subtrahend.num.multiply(this.denom));
    return new BigFraction(resultNumerator, resultDenominator);
  } // method subtract

  /**
   * Get the denominator of this fraction.
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   * @return a string that represents the fraction.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if
    return Objects.equals(this.denom, BigInteger.ONE) ? this.num.toString()
        : this.num + "/" + this.denom;
  } // Method toString

  /**
   * Multiply two BigFractions, yielding another BigFraction.
   * @param multiple
   * @return A new BigFraction
   */
  public BigFraction multiply(BigFraction multiple) {
    return new BigFraction(this.num.multiply(multiple.num), this.denom.multiply(multiple.denom));
  } // Method multiply

    /**
   * Divide two BigFractions, yielding another BigFraction.
   * @param divisor
   * @return A new BigFraction
   */
  public BigFraction divide(BigFraction divisor) {
    if (divisor.num.equals(BigInteger.ZERO)) {
      System.err.println("Error: Division by zero.");
    } // if
    return new BigFraction(this.num.multiply(divisor.denom), this.denom.multiply(divisor.num));
  } // Method divide

  /**
   * Get the fractional remainder of the BigFraction.
   * @return If the BigFraction is represented a/b, we return (a mod b)/b.
   */
  public BigFraction fractional() {
    return new BigFraction(this.num.mod(this.denom), this.denom);
  } // fractional
} // class BigFraction
