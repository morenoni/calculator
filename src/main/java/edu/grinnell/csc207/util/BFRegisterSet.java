package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A project for CSC-207 2024Fa.
 * Filename: BFRegisterSet.java
 * Author: Nicole Moreno Gonzalez
 * Description: Stores and retrieves values
 * from registers.
 */
public class BFRegisterSet {

  /**
   * Array to store the values of registers 'a' through 'z'.
   */
  private BigFraction[] registers;

  /**
   * Amount of registers.
   */
  private static final int REGISTER_COUNT = 26;

  /**
   * Constructor: Initializes the registers array.
   */
  public BFRegisterSet() {
    registers = new BigFraction[REGISTER_COUNT];
  } // Constructor BFRegisterSet

  /**
  * Stores the given value in the specified register.
  * @param register The register letter ('a' to 'z')
  * @param val The value to store in the register
  */
  public void store(char register, BigFraction val) {
    if (register < 'a' || register > 'z') {
      System.err.println("*** ERROR [STORE command received invalid register] ***");
      return;
    } // if
    int index = register - 'a';
    registers[index] = val;
  } // Method store

  /**
  * Retrieves the value from the given register.
  * @param register The register letter ('a' to 'z')
  * @return The value stored in the register, or 0/1 if the register is empty
  */
  public BigFraction get(char register) {
    if (register < 'a' || register > 'z') {
      return new BigFraction(BigInteger.ZERO, BigInteger.ONE);
    } // if
    int index = register - 'a';
    return registers[index];
  } // Method BigFraction
} // Class BFRegisterSet
