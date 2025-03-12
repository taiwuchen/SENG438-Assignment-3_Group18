package org.jfree.data;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;


public class RangeTest {
	private Range exampleRange;
	private Range negativeRange;
    private Range positiveRange;
    private Range zeroRange;
    /** A sample Range from -1.0 to 1.0 used for boundary analysis. */
    private Range testRange;

   
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    negativeRange = new Range(-0.4,-0.1);
    positiveRange = new Range(8.01,24.11);
    zeroRange = new Range(0,0);
    testRange = new Range(-1.0, 1.0);


    }


//getCentralValue

 	@Test
 	/**
 	 * A method testing the getCentralValue() method for a negative output
 	 */
 	public void centralValueShouldBeNegative() {
 		Range values = new Range(-10, 5);
 		assertEquals("The central value of -10 and 5 should be -2.5", -2.5, values.getCentralValue(), .001d);
 	}

 	@Test
 	/**
 	 * A method testing the getCentralValue() method for a zero output
 	 */
 	public void centralValueShouldBeZero() {
 		Range values = new Range(-10, 10);
 		assertEquals("The central value of -10 and 10 should be 0", 0, values.getCentralValue(), .001d);
 	}

 	@Test
 	/**
 	 * A method testing the getCentralValue() method for a positive output
 	 */
 	public void centralValueShouldBePositive() {
 		Range values = new Range(-5, 10);
 		assertEquals("The central value of -5 and 10 should be 2.5", 2.5, values.getCentralValue(), .001d);
 	}

 	
//contains
    
    @Test
    /**
 	 * Test to see is contains() returns true for value above the lower bound
 	 */
    public void rangeContainsAboveLowerBound() { 
    	exampleRange = new Range(-10, 10);
    	assertEquals("Range should contain -9.95",
    	        true, exampleRange.contains(-9.95));
    }
    
    @Test
    /**
 	 * Test to see is contains() returns true for value below the upper bound
 	 */
    public void rangeContainsBelowUpperBound() {
    	exampleRange = new Range(-10, 10);
    	assertEquals("Range should contain 9.99",
    	        true, exampleRange.contains(9.99));
    }
     
    @Test
    /**
 	 * Test to see is contains() returns false for value above the upper bound
 	 */
    public void rangeDoesntContainAboveUpperBound() { 
    	exampleRange = new Range(-10, 10);
    	assertEquals("Range should not contain 10.1",
    	        false, exampleRange.contains(10.1));
    }
       
    @Test
    /**
 	 * Test to see is contains() returns false for value below the lower bound
 	 */
    public void rangeDoesntContainBelowLowerBound() { 
    	exampleRange = new Range(-10, 10);
    	assertEquals("Range should  not contain -10.01",
    	        false, exampleRange.contains(-10.01));
    }
    
    
//getLength
    
    @Test
    /**
 	 * This test checks one of the equivalence classes by checking the length of the following class of inputs: (Positive, Positive)
 	 */
    public void testGetLengthPositivePositive() {   
        exampleRange = new Range(1,5);  // setting range to (1,5)
        assertEquals("The length of the range is:",
        4, exampleRange.getLength(), .000000001d);  // Expected output 4
    }
    
    @Test
    /**
 	 * // This test checks one of the equivalence classes by checking the length of the following class of inputs: (Negative, Positive)
 	 */
    public void testGetLengthNegativePositive() {    
        exampleRange = new Range(-1,5); // setting range to (-1,5)
        assertEquals("The length of the range is:",
        6, exampleRange.getLength(), .000000001d); // Expected output 6
    }
    
    @Test
    /**
 	 * This test checks one of the equivalence classes by checking the length of the following class of inputs: (Negative, Negative)
 	 */
    public void testGetLengthNegativeNegative() { 
        exampleRange = new Range(-7,-5); // setting range to (-7,-5)
        assertEquals("The length of the range is:",
        2, exampleRange.getLength(), .000000001d); // Expected output 2
    }
    
    
 //getLowerBound

    @Test
    /**
 	 * This test checks negative lower bound of range
 	 */
    public void testNegativeRangeGetLower(){
        assertEquals("Lower bound of negative range",-0.4,negativeRange.getLowerBound(),.000000001d);
    }
    
    @Test
    /**
 	 * This test checks positive lower bound of range
 	 */
    public void testPositiveRangeGetLower(){
        assertEquals("Lower bound of positive range",8.01,positiveRange.getLowerBound(),.000000001d);
    }
    
    @Test
    /**
 	 * This test checks zero lower bound of range
 	 */
    public void testZeroRangeGetLower(){
        assertEquals("Lower",0,zeroRange.getLowerBound(),.000000001d);
    }
 	
 	
//equals

 	@Test
 	/**
 	 * A method testing the equals method for equality between two Range objects
 	 */
 	public void equalsTestForSameRange() {
 		Range values1 = new Range(-10, 10);
 		Range values2 = new Range(-10, 10);
 		assertEquals("Both input ranges have the same bounds", true, values1.equals(values2));
 	}

 	@Test
 	/**
 	 * A method testing the equals method for inequality between two Range objects
 	 * 
 	 */
 	public void equalsTestForLowerRange() {
 		Range values1 = new Range(-10, 10);
 		Range values2 = new Range(-20, 10);
 		assertEquals("The input ranges have different lower bounds", false, values1.equals(values2));
 	}
 	
 	@Test
 	/**
 	 * A method testing the equals method for null objects
 	 * 
 	 */
    public void equalsNull() {
        assertFalse(exampleRange.equals(null));
    }
 	
 	
 // Constraints
 	
    /**
     * Equivalence Class: Value within the range (-1,1).
     * Boundary Value Analysis: 0.0 is a valid in-range value.
     */
    @Test
    public void testConstrain_ValueInsideRange() {
        double input = 0.0;
        double expected = 0.0;
        double actual = testRange.constrain(input);
        assertEquals("An in-range value should return itself.",
                     expected, actual, 0.0001);
    }
    /**
     * Equivalence Class: Value at the lower bound.
     * Boundary Value Analysis: -1.0 is the lowest valid value.
     */
    @Test
    public void testConstrain_AtLowerBound() {
        double input = -1.0;
        double expected = -1.0;
        double actual = testRange.constrain(input);
        assertEquals("A value at the lower bound should return itself.",
                     expected, actual, 0.0001);
    }
    /**
     * Equivalence Class: Value at the upper bound.
     * Boundary Value Analysis: 1.0 is the uppermost valid value.
     */
    @Test
    public void testConstrain_AtUpperBound() {
        double input = 1.0;
        double expected = 1.0;
        double actual = testRange.constrain(input);
        assertEquals("A value at the upper bound should return itself.",
                     expected, actual, 0.0001);
    }
    @Test
    public void testConstrain_BelowLowerBound_AcceptsBug() {
        double input = -5.0;
        double expected = -1.0;  // Changed from 0.0 to -1.0 (correct behavior)
        double actual = testRange.constrain(input);
        assertEquals("Below lower bound should return lower bound (-1.0)",
                    expected, actual, 0.0001);
    }
    /**
     * Equivalence Class: Value above the upper bound.
     * Boundary Value Analysis: 5.0 is well beyond 1.0.
     */
    @Test
    public void testConstrain_AboveUpperBound() {
        double input = 5.0;
        double expected = 1.0;
        double actual = testRange.constrain(input);
        assertEquals("Above upper bound => should return the upper bound (1.0).",
                     expected, actual, 0.0001);
    }
    /**
     * Equivalence Class: Overlapping fully within the range.
     * Boundary Value Analysis: (-0.5, 0.5) should intersect with (-1,1).
     */
    @Test
    public void testIntersects_FullyWithinRange() {
        boolean result = testRange.intersects(-0.5, 0.5);
        assertTrue("(-0.5, 0.5) should intersect with (-1,1).", result);
    }
    /**
     * Boundary Value Analysis: Exact bounds overlap.
     */
    @Test
    public void testIntersects_ExactBounds() {
        boolean result = testRange.intersects(-1.0, 1.0);
        assertTrue("(-1,1) with (-1,1) should be a full overlap.", result);
    }
    /**
     * Equivalence Class: Completely non-overlapping range (right side).
     * Boundary Value Analysis: (2.0, 5.0) is completely outside [-1,1].
     */
    @Test
    public void testIntersects_CompletelyOutside() {
        boolean result = testRange.intersects(2.0, 5.0);
        assertFalse("(2.0,5.0) is completely outside (-1,1).", result);
    }
    /**
     * Known Bug: The SUT does not recognize partial overlap above the upper bound.
     *
     * Expected behavior: (-1,1) & (0.5,5) should intersect from 0.5 to 1.
     * However, the SUT returns false. We acknowledge this defect in our test.
     */
    
    // Older Version
    //@Test
    //public void testIntersects_PartialOverlapAboveUpper_AcceptsBug() {
    //    boolean result = testRange.intersects(0.5, 5.0);
    //    assertFalse("Bug: The SUT incorrectly does NOT recognize partial overlap above 1.0.", result);
    //}
    
    //  New Version
    @Test
    public void testIntersects_PartialOverlapAboveUpper_AcceptsBug() {
        boolean result = testRange.intersects(0.5, 5.0);
        assertTrue("Ranges (0.5,5.0) and (-1.0,1.0) should intersect", result);
    }
    
    /**
     * Equivalence Class: Partial overlap on the lower bound.
     * Boundary Value Analysis: Overlap from -1.0 to -0.5.
     */
    @Test
    public void testIntersects_PartialOverlapBelowLower() {
        boolean result = testRange.intersects(-5.0, -0.5);
        assertTrue("(-5,-0.5) overlaps (-1,1) from -1.0 to -0.5 => should return true.", result);
    }

 	
 	
 	@After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
 	
}