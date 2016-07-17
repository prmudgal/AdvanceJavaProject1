package edu.pdx.cs410J.pmudgal;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Created by Vijay on 7/13/2016.
 */
public class PolishCalculatorTest {

    @Test
    public void verifyAddition() {
    PolishCalculator polishCalculator = new PolishCalculator();
        assertEquals(3, polishCalculator.doOperation(new String("1 2 +"))) ;
  }
@Test
    public void verifyOperationWithEmptyString() {
    PolishCalculator polishCalculator = new PolishCalculator();
        assertEquals(3, polishCalculator.doOperation(new String("1 2 3"))) ;
  }
@Ignore
    public void verifyOperationWithoutSpace() {
    PolishCalculator polishCalculator = new PolishCalculator();
        fail(String.valueOf(polishCalculator.doOperation(new String("12+"))));
  }
@Ignore
    public void verifyOperationDividedByZero() {
    PolishCalculator polishCalculator = new PolishCalculator();
        fail(String.valueOf(polishCalculator.doOperation(new String("1 0 /"))));
  }

    @Test
    public void verifyCalculations() {
    PolishCalculator polishCalculator = new PolishCalculator();
        assertEquals(14, polishCalculator.doOperation(new String("5 1 2 + 4 * + 3 -"))) ;
  }


}
