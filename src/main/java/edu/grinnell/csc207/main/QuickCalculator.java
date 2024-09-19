package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;

/**
 * A project for CSC-207 2024Fa.
 * Filename: QuickCalculator.java
 * Author: Nicole Moreno Gonzalez
 * Description: Takes arguments from
 * comand-line to do multiple fraction
 * or integer operations at the same time.
*/
public class QuickCalculator {

  /**
   * Creates an Interactive Calculator object.
   */
  private static InteractiveCalculator ic = new InteractiveCalculator();

  /**
   * Main Method: Implements Quick Calculator program.
   * In particular it takes inputs and arrenges the outputs.
   * @param args are command-line arguments.
   */
  public static void main(String[] args) {
    if (args.length == 0 || isEmpty(args)) {
      ic.println("FAILED [Invalid expression]");
      return;
    } // if
    for (String command : args) {
      if (command.startsWith("STORE")) {
        storeValue(command);
        continue;
      } // if
      if (ic.isRegister(command)) {
        ic.println(command + " -> " + ic.getCalculator().getRegister(command.charAt(0)));
        continue;
      } // if
      if (!command.contains(" ")) {
        ic.getCalculator().clear();
        ic.getCalculator().add(new BigFraction(command));
        ic.println(command + " -> " + ic.getCalculator().get());
        continue;
      } // if
      String[] values = command.split(" ");
      boolean isSuccessful = ic.compute(values);
      if (!isSuccessful) {
        ic.println(command + ": FAILED [Invalid expression]");
        continue;
      } // if
      ic.println(command + " -> " + ic.getCalculator().get());
    } // for
  } // Main Method

  /**
   * Tells if an argument is empty.
   * @param args command-line argument.
   * @return true if empty, false otherwise.
   */
  private static boolean isEmpty(String[] args) {
    return args.length == 1 && (args[0] == null || args[0].isEmpty());
  } // Method isEmpty

  /**
   * Read the a line that asks to store a value.
   * @param line STORE register.
   */
  private static void storeValue(String line) {
    String[] parts = line.split(" ");
    if (parts.length != 2 || !ic.isRegister(parts[1])) {
      ic.println("Invalid STORE command.");
      return;
    } else if (parts[1].charAt(0) < 'a' || parts[1].charAt(0) > 'z') {
      ic.println("*** ERROR [STORE command received invalid register] ***");
      return;
    } // if
    ic.getCalculator().store(parts[1].charAt(0));
    ic.println("STORE " + parts[1] + " -> STORED");
  } // Method storeValue
} // Class QuickCalculator
