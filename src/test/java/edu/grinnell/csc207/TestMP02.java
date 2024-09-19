package edu.grinnell.csc207;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Tests of the various classes for MP02.
 *
 * In a typical Maven project, this should be stored in
 *   src/test/java/edu/grinnel/csc207.
 *
 * @author Samuel A. Rebelsky
 */
public class TestMP02 {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * 1/2
   */
  static final BigFraction ONE_HALF = new BigFraction(1, 2);

  /**
   * 1/3
   */
  static final BigFraction ONE_THIRD = new BigFraction(1, 3);

  /**
   * 2/3
   */
  static final BigFraction TWO_THIRDS = new BigFraction(2, 3);

  /**
   * 1/5
   */
  static final BigFraction ONE_FIFTH = new BigFraction(1, 5);

  /**
   * 1/6
   */
  static final BigFraction ONE_SIXTH = new BigFraction(1, 6);

  // +-----------------------+---------------------------------------
  // | R tests - BigFraction |
  // +-----------------------+

  /**
   * Can we add two BigFraction values?
   */
  @Test
  public void testBfAdd() {
    assertEquals("5/6", ONE_THIRD.add(ONE_HALF).toString(), 
        "R: Addition 1");
    assertEquals("13/15", ONE_FIFTH.add(TWO_THIRDS).toString(), 
        "R: Addition 2");
    assertEquals("4/3", TWO_THIRDS.add(TWO_THIRDS).toString(), 
        "R: Addition 3");
  } // testBfAdd()

  /**
   * Can we subtract one BigFraction from another?
   */
  @Test
  public void testBfSubtract() {
    assertEquals("1/6", ONE_HALF.subtract(ONE_THIRD).toString(), 
        "R: Subtraction 1");
    assertEquals("1/3", TWO_THIRDS.subtract(ONE_THIRD).toString(), 
        "R: Subtraction 2");
  } // testBfSubtract()

  /** 
   * Can we multiply two BigFractions?
   */
  @Test
  public void testBfMultiply() {
    assertEquals("1/4", ONE_HALF.multiply(ONE_HALF).toString(),
        "R: Multiplication 1");
    assertEquals("2/15", TWO_THIRDS.multiply(ONE_FIFTH).toString(),
        "R: Multiplication 2");
  } // testBfMultiply()

  /** 
   * Can we divide two BigFractions?
   */
  @Test
  public void testBfDivide() {
    assertEquals("3/2", ONE_HALF.divide(ONE_THIRD).toString(),
        "R: Division 1");
    assertEquals("2/3", ONE_THIRD.divide(ONE_HALF).toString(),
        "R: Division 2");
    assertEquals("10/3", TWO_THIRDS.divide(ONE_FIFTH).toString(),
        "R: Division 3");
  } // testBfDivide()

  // +-----------------------+---------------------------------------
  // | M tests - BigFraction |
  // +-----------------------+

  /**
   * Do BigFraction values simplify?
   */
  @Test
  public void testBfSimplify() {
    assertEquals("2/3", new BigFraction(4, 6).toString(),
      "M: Simplify 1");
    assertEquals("5/3", new BigFraction(15, 9).toString(),
      "M: Simplify 2");
    assertEquals("1/2", ONE_THIRD.add(ONE_SIXTH).toString(),
      "M: Simplify 3");
  } // testBfSimplify()

  /**
   * Can we parse fractions?
   */
  @Test
  public void testBfParse() {
    assertEquals("1/13", new BigFraction("1/13").toString(),
      "M: Parsing 1");
    assertEquals("5/11", new BigFraction("5/11").toString(),
      "M: Parsing 2");
    assertEquals(BigInteger.valueOf(22), new BigFraction("22/7").numerator(),
      "M: Parsing 3a");
    assertEquals(BigInteger.valueOf(7), new BigFraction("22/7").denominator(),
      "M: Parsing 3b");
  } // testBfParse()

  /**
   * When we parse fractions, do they simplify?
   */
  @Test
  public void testBfParseSimplify() {
    assertEquals("2/3", new BigFraction("4/6").toString(),
      "M: Parsing and simplifying 1");  
    assertEquals("4/7", new BigFraction("40/70").toString(),
      "M: Parsing and simplifying 2"); 
    assertEquals(BigInteger.valueOf(3), new BigFraction("15/10").numerator(),
      "M: Parsing and simplifying 3a");
    assertEquals(BigInteger.valueOf(2), new BigFraction("15/10").denominator(),
      "M: Parsing and simplifying 3a");
  } // testBfParseSimplify()

  // +-----------------------+---------------------------------------
  // | E tests - BigFraction |
  // +-----------------------+

  /**
   * Do we get whole numbers back?
   */
  @Test
  public void testBfReturnWhole() {
    assertEquals("3", new BigFraction("9/3").toString(),
       "E: Simplify to whole number 1");
    assertEquals("1", ONE_HALF.add(ONE_HALF).toString(),
       "E: Simplify to whole number 2");
    assertEquals("2", TWO_THIRDS.divide(ONE_THIRD).toString(),
       "E: Simplify to whole number 3");
    assertEquals("0", 
       TWO_THIRDS.subtract(ONE_THIRD).subtract(ONE_THIRD).toString(),
       "E: Simplify to whole number 4");
  } // testBfReturnWhole

  /**
   * Can we parse whole numbers?
   */
  @Test
  public void testBfParseWhole() {
    assertEquals("42", new BigFraction("42").toString(),
        "E: Parse whole number 1");
    assertEquals("3", new BigFraction("3").toString(),
        "E: Parse whole number 2");
  } // testBfParseWhole()

  /**
   * Can we parse negative numbers?
   */
  @Test
  public void testBfParseNegative() {
    assertEquals("-3/5", new BigFraction("-3/5").toString(),
        "E: Parse negative number 1");
    assertEquals("-1/2", 
        new BigFraction("-1/4").add(new BigFraction("-1/4")).toString(),
        "E: Parse negative number 2");
  } // testBfParseNegative()

  // +------------------------+--------------------------------------
  // | R tests - BFCalculator |
  // +------------------------+

  /**
   * Can we create a BFCalculator?
   */
  @Test
  public void testBfcNew() {
    assertNotNull(new BFCalculator(), 
        "R: Create new BFCalculator");
  } // testBfcNew()

  /**
   * Does a new BFCalculator return 0 when started?
   */
  @Test
  public void testBFcInit() {
    BFCalculator bfc = new BFCalculator();
    assertEquals("0", bfc.get().toString(),
        "R: Calculator starts at 0");
  } // testBFcInit()

  /**
   * Does a new BFCalculator return 0 after clean?
   */
  @Test
  public void testBfcClear() {
    BFCalculator bfc = new BFCalculator();
    bfc.clear();
    assertEquals("0", bfc.get().toString(),
        "R: Clear resets calculator to 0");
  } // testBfcClear

  // +------------------------+--------------------------------------
  // | M tests - BFCalculator |
  // +------------------------+

  /**
   * Does addition work?
   */
  @Test
  public void testBfcAdd() {
    BFCalculator bfc = new BFCalculator();

    bfc.add(ONE_HALF);
    assertEquals("1/2", bfc.get().toString(),
        "M: Calculator addition 1");

    bfc.add(ONE_THIRD);
    assertEquals("5/6", bfc.get().toString(),
        "M: Calculator addition 2");

    bfc.add(ONE_THIRD);
    assertEquals(BigInteger.valueOf(7), bfc.get().numerator(),
        "M: Calculator addition 3a");
    assertEquals(BigInteger.valueOf(6), bfc.get().denominator(),
        "M: Calculator addition 3a");
  } // testBfcAdd()

  /**
   * Does clear work after we add?
   */
  @Test
  public void testBfcClearAfterAdd() {
    BFCalculator bfc = new BFCalculator();
    bfc.add(ONE_HALF);
    assertEquals("1/2", bfc.get().toString(),
        "M: Calculator reset 1");
    bfc.clear();
    assertEquals("0", bfc.get().toString(),
        "M: Calculator reset 2");
    bfc.add(TWO_THIRDS);
    assertEquals("2/3", bfc.get().toString(),
        "M: Calculator rest 3");
  } // testBfcClearAfterAdd()

  /**
   * Does subtraction work?
   */
  @Test
  public void testBfcSubtract() {
    BFCalculator bfc = new BFCalculator();

    bfc.add(ONE_HALF);

    bfc.subtract(ONE_THIRD);
    assertEquals("1/6", bfc.get().toString(),
        "M: Calculator subtraction 1");

    bfc.subtract(TWO_THIRDS);
    assertEquals("-1/2", bfc.get().toString(),
        "M: Calculator subtraction 2");
  } // testBfcSubtract()

  /**
   * Does multiplication work?
   */
  @Test
  public void testBfcMultiply() {
    BFCalculator bfc = new BFCalculator();

    bfc.add(ONE_HALF);

    bfc.multiply(ONE_THIRD);
    assertEquals("1/6", bfc.get().toString(),
        "M: Calculator multiplication 1");

    bfc.multiply(TWO_THIRDS);
    assertEquals("1/9", bfc.get().toString(),
        "M: Calculator multiplication 2");
  } // testBfcMultiply()

  /**
   * Does division work?
   */
  @Test
  public void testBfcDivide() {
    BFCalculator bfc = new BFCalculator();

    bfc.add(ONE_HALF);

    bfc.divide(ONE_THIRD);
    assertEquals(BigInteger.valueOf(3), bfc.get().numerator(),
        "M: Calculator division 1a");
    assertEquals(BigInteger.valueOf(2), bfc.get().denominator(),
        "M: Calculator division 1b");

    bfc.divide(TWO_THIRDS);
    assertEquals(BigInteger.valueOf(9), bfc.get().numerator(),
        "M: Calculator division 2a");
    assertEquals(BigInteger.valueOf(4), bfc.get().denominator(),
        "M: Calculator division 2b");

    bfc.clear();

    bfc.add(ONE_THIRD);
    bfc.divide(ONE_HALF);
    assertEquals("2/3", bfc.get().toString(),
        "M: Calculator division 3");
  } // testBfcDivide

  // +------------------------+--------------------------------------
  // | E tests - BFCalculator |
  // +------------------------+

  /**
   * Can we create two or more independent BFCalculators?
   */
  @Test
  public void testBfcMany() {
    BFCalculator bfcA = new BFCalculator();
    BFCalculator bfcB = new BFCalculator();
    BFCalculator bfcC = new BFCalculator();

    bfcA.add(ONE_HALF);
    assertEquals("1/2", bfcA.get().toString(),
        "E: Multiple calculators 1a");
    assertEquals("0", bfcB.get().toString(),
        "E: Multiple calculators 1b");
    assertEquals("0", bfcC.get().toString(),
        "E: Multiple calculators 1c");

    bfcB.add(ONE_THIRD);
    assertEquals("1/2", bfcA.get().toString(),
        "E: Multiple calculators 2a");
    assertEquals("1/3", bfcB.get().toString(),
        "E: Multiple calculators 2b");
    assertEquals("0", bfcC.get().toString(),
        "E: Multiple calculators 2c");

    bfcB.add(ONE_THIRD);
    assertEquals("1/2", bfcA.get().toString(),
        "E: Multiple calculators 3a");
    assertEquals("2/3", bfcB.get().toString(),
        "E: Multiple calculators 3b");
    assertEquals("0", bfcC.get().toString(),
        "E: Multiple calculators 3c");

    bfcC.multiply(ONE_THIRD);
    assertEquals("1/2", bfcA.get().toString(),
        "E: Multiple calculators 4a");
    assertEquals("2/3", bfcB.get().toString(),
        "E: Multiple calculators 4b");
    assertEquals("0", bfcC.get().toString(),
        "E: Multiple calculators 4c");

    bfcA.clear();
    assertEquals("0", bfcA.get().toString(),
        "E: Multiple calculators 5a");
    assertEquals("2/3", bfcB.get().toString(),
        "E: Multiple calculators 5b");
    assertEquals("0", bfcC.get().toString(),
        "E: Multiple calculators 5c");
  } // testBfcMany()

  // +-------------------------+-------------------------------------
  // | R tests - BFRegisterSet |
  // +-------------------------+

  /**
   * Can we create a BFRegisterSet?
   */
  @Test
  public void testBfrsNew() {
    assertNotNull(new BFRegisterSet(), 
        "R: Create new BFRegisterSet");
  } // testBfrsNew()

  // +-------------------------+-------------------------------------
  // | M tests - BFRegisterSet |
  // +-------------------------+

  /**
   * Can we safely store and get a value?
   */
  @Test
  public void testBfrsBasics() {
    BFRegisterSet registers = new BFRegisterSet();

    registers.store('a', ONE_HALF);
    registers.store('b', ONE_THIRD);
    registers.store('p', TWO_THIRDS);
    registers.store('q', ONE_FIFTH);
    registers.store('z', ONE_SIXTH);

    assertEquals("1/2", registers.get('a').toString(),
        "M: Register basics 1");
    assertEquals("1/3", registers.get('b').toString(),
        "M: Register basics 2");
    assertEquals("2/3", registers.get('p').toString(),
        "M: Register basics 3");
    assertEquals("1/5", registers.get('q').toString(),
        "M: Register basics 4");
    assertEquals("1/6", registers.get('z').toString(),
        "M: Register basics 5");
  } // testBfrsBasics()

  /**
   * Can we independently update values?
   */
  @Test
  public void testBfrsUpdate() {
    BFRegisterSet registers = new BFRegisterSet();

    registers.store('l', ONE_HALF);
    registers.store('m', ONE_THIRD);

    assertEquals("1/2", registers.get('l').toString(),
        "M: Register update 1a");
    assertEquals("1/3", registers.get('m').toString(),
        "M: Register update 1b");

    registers.store('l', ONE_FIFTH);
    assertEquals("1/5", registers.get('l').toString(),
        "M: Register update 2a");
    assertEquals("1/3", registers.get('m').toString(),
        "M: Register udpate 2b");

    registers.store('l', TWO_THIRDS);
    assertEquals("2/3", registers.get('l').toString(),
        "M: Register update 3a");
    assertEquals("1/3", registers.get('m').toString(),
        "M: Register udpate 3b");

    registers.store('m', ONE_HALF);
    assertEquals("2/3", registers.get('l').toString(),
        "M: Register update 4a");
    assertEquals("1/2", registers.get('m').toString(),
        "M: Register udpate 4b");
  } // testBfrsUpdate()

  // +-------------------------+-------------------------------------
  // | E tests - BFRegisterSet |
  // +-------------------------+

  /**
   * Can we independently update multiple register sets?
   */
  @Test
  public void testBfrsIndependentUpdate() {
    BFRegisterSet registersA = new BFRegisterSet();
    BFRegisterSet registersB = new BFRegisterSet();
    BFRegisterSet registersC = new BFRegisterSet();

    registersA.store('i', ONE_HALF);
    registersA.store('j', ONE_HALF);
    registersB.store('i', ONE_THIRD);
    registersB.store('j', TWO_THIRDS);
    registersC.store('i', ONE_FIFTH);
    registersC.store('j', ONE_SIXTH);

    assertEquals("1/2", registersA.get('i').toString(),
      "E: Independent updates 1ai");
    assertEquals("1/2", registersA.get('j').toString(),
      "E: Independent updates 1aj");
    assertEquals("1/3", registersB.get('i').toString(),
      "E: Independent updates 1bi");
    assertEquals("2/3", registersB.get('j').toString(),
      "E: Independent updates 1bj");
    assertEquals("1/5", registersC.get('i').toString(),
      "E: Independent updates 1ci");
    assertEquals("1/6", registersC.get('j').toString(),
      "E: Independent updates 1cj");

    registersA.store('i', ONE_SIXTH);
    assertEquals("1/6", registersA.get('i').toString(),
      "E: Independent updates 2ai");
    assertEquals("1/2", registersA.get('j').toString(),
      "E: Independent updates 2aj");
    assertEquals("1/3", registersB.get('i').toString(),
      "E: Independent updates 2bi");
    assertEquals("2/3", registersB.get('j').toString(),
      "E: Independent updates 2bj");
    assertEquals("1/5", registersC.get('i').toString(),
      "E: Independent updates 2ci");
    assertEquals("1/6", registersC.get('j').toString(),
      "E: Independent updates 2cj");

    registersC.store('i', ONE_HALF);
    assertEquals("1/6", registersA.get('i').toString(),
      "E: Independent updates 3ai");
    assertEquals("1/2", registersA.get('j').toString(),
      "E: Independent updates 3aj");
    assertEquals("1/3", registersB.get('i').toString(),
      "E: Independent updates 3bi");
    assertEquals("2/3", registersB.get('j').toString(),
      "E: Independent updates 3bj");
    assertEquals("1/2", registersC.get('i').toString(),
      "E: Independent updates 3ci");
    assertEquals("1/6", registersC.get('j').toString(),
      "E: Independent updates 3cj");

    registersB.store('j', ONE_FIFTH);
    assertEquals("1/6", registersA.get('i').toString(),
      "E: Independent updates 4ai");
    assertEquals("1/2", registersA.get('j').toString(),
      "E: Independent updates 4aj");
    assertEquals("1/3", registersB.get('i').toString(),
      "E: Independent updates 4bi");
    assertEquals("1/5", registersB.get('j').toString(),
      "E: Independent updates 4bj");
    assertEquals("1/2", registersC.get('i').toString(),
      "E: Independent updates 4ci");
    assertEquals("1/6", registersC.get('j').toString(),
      "E: Independent updates 4cj");

  } // testBfrsIndependentUpdate()

} // class TestMP02

