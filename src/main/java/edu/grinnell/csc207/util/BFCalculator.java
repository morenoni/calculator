package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A project for CSC-207 2024Fa.
 * Filename: BFCalculator.java
 * Author: Nicole Moreno Gonzalez
 * Description: Implements oparations with fractions
 * and stores last outputs.
 * from registers.
 */
public class BFCalculator {
  /**
   * Field to store the last computed value.
   */
  private BigFraction lastValue;

  /**
   * Field to create a register.
   */
  private BFRegisterSet register;

  /**
   * Initialize a register.
   */
  public BFCalculator() {
    register = new BFRegisterSet();
    clear();
  } // Method BFCalculator

  /**
   * Gets the last computed value (returns 0 if there is no such value).
   * @return last value/output.
   */
  public BigFraction get() {
    return this.lastValue;
  } // Method get

  /**
   * Adds a BigFraction to the last computed value.
   * @param val other value.
   */
  public void add(BigFraction val) {
    this.lastValue = this.lastValue.add(val);
  } // Method add

  /**
   * Subtracts a BigFraction from the last computed value.
   * @param val other value.
  */
  public void subtract(BigFraction val) {
    this.lastValue = this.lastValue.subtract(val);
  } // Method subtract

   /**
    * Multiplies the last computed value by a BigFraction.
    * @param val is the multiplier.
    */
  public void multiply(BigFraction val) {
    this.lastValue = this.lastValue.multiply(val);
  } // Method multiply

  /**
   * Divides the last computed value by a BigFraction.
   * @param val is the divisor.
   */
  public void divide(BigFraction val) {
    if (val.numerator().equals(BigInteger.ZERO)) {
      System.err.println("Error: Division by zero.");
    } else {
      this.lastValue = this.lastValue.divide(val);
    } // if
  } // Method divide

  /**
   * Clears the last computed value (resets to 0/1).
   */
  public void clear() {
    this.lastValue = new BigFraction(BigInteger.ZERO, BigInteger.ONE); // Reset to 0/1
  } // Method clear

  /**
   * Sets a value to lastValue according to a register.
   * @param id of the register
   */
  public void setByRegister(char id) {
    this.lastValue = register.get(id);
  } // Method SetByRegister

  /**
   * Gets the value stored in a register.
   * @param id of the register.
   * @return the stored value.
   */
  public BigFraction getRegister(char id) {
    return register.get(id);
  } // Method getRegister

  /**
   * Stores the last value in the desired register.
   * @param id is the desired register.
   */
  public void store(char id) {
    register.store(id, this.lastValue);
  } // Method store

} // Class BFCalculator

