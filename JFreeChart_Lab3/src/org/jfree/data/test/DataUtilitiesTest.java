package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class DataUtilitiesTest {

    // ======================
    // calculateColumnTotal Tests
    // ======================

    @Test
    public void calculateColumnTotal_firstColumnIndex() {
        // Setup a mock for Values2D with 3 rows, summing first column (index 0)
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(1.0));
            one(values).getValue(1, 0); will(returnValue(2.0));
            one(values).getValue(2, 0); will(returnValue(3.0));
        }});
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(6.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateColumnTotal_middleColumnIndex() {
        // Setup a mock for Values2D with 3 rows, summing middle column (index 1)
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(3));
            one(values).getValue(0, 1); will(returnValue(4.0));
            one(values).getValue(1, 1); will(returnValue(5.0));
            one(values).getValue(2, 1); will(returnValue(6.0));
        }});
        double result = DataUtilities.calculateColumnTotal(values, 1);
        assertEquals(15.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateColumnTotal_lastColumnIndex() {
        // Setup a mock for Values2D with 2 rows, summing last column (index 2)
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(2));
            one(values).getValue(0, 2); will(returnValue(7.0));
            one(values).getValue(1, 2); will(returnValue(8.0));
        }});
        double result = DataUtilities.calculateColumnTotal(values, 2);
        assertEquals(15.0, result, 0.000000001d);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void calculateColumnTotal_outOfBoundsColumnIndex() {
        // Simulate an out-of-bounds index (e.g., index 5 for a 2-row dataset)
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(2));
            // When trying to access an invalid column index, throw an exception.
            one(values).getValue(0, 5); will(throwException(new IndexOutOfBoundsException("Column index out of bounds")));
        }});
        DataUtilities.calculateColumnTotal(values, 5);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void calculateColumnTotal_negativeColumnIndex() {
        // Negative column index should result in an exception.
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(2));
            one(values).getValue(0, -1); will(throwException(new IndexOutOfBoundsException("Negative column index")));
        }});
        DataUtilities.calculateColumnTotal(values, -1);
    }
    
    // Older version
    // @Test(expected = NullPointerException.class)
    // public void calculateColumnTotal_nullDataObject() {
    //     DataUtilities.calculateColumnTotal(null, 0);
    // }
    
    // New version
    @Test(expected = IllegalArgumentException.class)
    public void calculateColumnTotal_nullDataObject() {
        DataUtilities.calculateColumnTotal(null, 0);
    }


    
    // ======================
    // calculateRowTotal Tests
    // ======================
    
    @Test
    public void calculateRowTotal_firstRowIndex() {
        // Setup a mock for Values2D for row total calculation (first row, index 0)
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        // DataUtilities.calculateRowTotal uses getColumnCount() to iterate columns.
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(0, 0); will(returnValue(1.0));
            one(values).getValue(0, 1); will(returnValue(2.0));
            one(values).getValue(0, 2); will(returnValue(3.0));
        }});
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(6.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateRowTotal_middleRowIndex() {
        // Setup a mock for Values2D for row total calculation (middle row, index 1)
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(1, 0); will(returnValue(4.0));
            one(values).getValue(1, 1); will(returnValue(5.0));
            one(values).getValue(1, 2); will(returnValue(6.0));
        }});
        double result = DataUtilities.calculateRowTotal(values, 1);
        assertEquals(15.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateRowTotal_lastRowIndex() {
        // Setup a mock for Values2D for row total calculation (last row)
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        // Assume 2 rows (indexes 0 and 1); we test row index 1.
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(1, 0); will(returnValue(7.0));
            one(values).getValue(1, 1); will(returnValue(8.0));
            one(values).getValue(1, 2); will(returnValue(9.0));
        }});
        double result = DataUtilities.calculateRowTotal(values, 1);
        assertEquals(24.0, result, 0.000000001d);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void calculateRowTotal_outOfBoundsRowIndex() {
        // When an invalid (out-of-bounds) row index is passed, expect an exception.
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(5, 0); will(throwException(new IndexOutOfBoundsException("Row index out of bounds")));
        }});
        DataUtilities.calculateRowTotal(values, 5);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void calculateRowTotal_negativeRowIndex() {
        // Negative row index should throw an exception.
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getColumnCount(); will(returnValue(3));
            one(values).getValue(-1, 0); will(throwException(new IndexOutOfBoundsException("Negative row index")));
        }});
        DataUtilities.calculateRowTotal(values, -1);
    }
    
    // Older version
    // @Test(expected = NullPointerException.class)
    // public void calculateRowTotal_nullDataObject() {
    //    DataUtilities.calculateRowTotal(null, 0);
    // }
    
    // New version
    @Test(expected = IllegalArgumentException.class)
    public void calculateRowTotal_nullDataObject() {
        DataUtilities.calculateRowTotal(null, 0);
    }
    
    // ======================
    // getCumulativePercentages Tests
    // ======================
    
    @Test
    public void getCumulativePercentages_checkKeys() {
        Mockery mockingContext = new Mockery();
        final KeyedValues keyedValues = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {{
            allowing(keyedValues).getItemCount(); will(returnValue(2));
            allowing(keyedValues).getKey(0); will(returnValue("X"));
            allowing(keyedValues).getKey(1); will(returnValue("Y"));
            allowing(keyedValues).getValue(0); will(returnValue(2));
            allowing(keyedValues).getValue(1); will(returnValue(3));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
        // Verify that the keys remain the same and the cumulative percentages are correct.
        assertEquals("X", result.getKey(0));
        assertEquals("Y", result.getKey(1));
        assertEquals(0.4,  (Double) result.getValue(0), 1e-9);
        assertEquals(1.0,  (Double) result.getValue(1), 1e-9);

    }
    
    @Test
    public void getCumulativePercentages_firstValue() {
        // Setup a mock for KeyedValues with two keys "X" and "Y"
        // Values: 2 and 3 (total = 5). The first cumulative percentage should be 2/5 = 0.4.
        Mockery mockingContext = new Mockery();
        final KeyedValues keyedValues = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {{
            allowing(keyedValues).getItemCount(); will(returnValue(2));
            allowing(keyedValues).getKey(0); will(returnValue("X"));
            allowing(keyedValues).getKey(1); will(returnValue("Y"));
            allowing(keyedValues).getValue(0); will(returnValue(2));
            allowing(keyedValues).getValue(1); will(returnValue(3));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
        assertEquals(0.4, (Double) result.getValue(0), 0.000000001d);
    }
    
    @Test
    public void getCumulativePercentages_lastValue() {
        // Setup a mock for KeyedValues with two keys "X" and "Y"
        // Values: 4 and 6 (total = 10). The last cumulative percentage should equal 1.0.
        Mockery mockingContext = new Mockery();
        final KeyedValues keyedValues = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {{
            allowing(keyedValues).getItemCount(); will(returnValue(2));
            allowing(keyedValues).getKey(0); will(returnValue("X"));
            allowing(keyedValues).getKey(1); will(returnValue("Y"));
            allowing(keyedValues).getValue(0); will(returnValue(4));
            allowing(keyedValues).getValue(1); will(returnValue(6));
        }});
        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
        assertEquals(1.0, (Double) result.getValue(1), 0.000000001d);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getCumulativePercentages_nullDataObject() {
        // Passing a null KeyedValues should throw an IllegalArgumentException.
        DataUtilities.getCumulativePercentages(null);
    }
    
    // ======================
    // createNumberArray Tests
    // ======================
    
    @Test
    public void testcreateNumberArray() {
        double[] data = {1.0, 2.5, 3.5};
        Number[] result = DataUtilities.createNumberArray(data);
        assertEquals("Array length should match", data.length, result.length);
        assertEquals("Element 0 should be 1.0", 1.0, result[0]);
        assertEquals("Element 1 should be 2.5", 2.5, result[1]);
        assertEquals("Element 2 should be 3.5", 3.5, result[2]);
    }
    
    @Test
    public void testcreateNumberArrayOne() {
        double[] data = {42.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertEquals("Single-element array length", 1, result.length);
        assertEquals("Element 0 should be 42.0", 42.0, result[0]);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createNumberArrayException() {
        // Passing null should throw an IllegalArgumentException.
        DataUtilities.createNumberArray(null);
    }
    
    // ======================
    // createNumberArray2D Tests
    // ======================
    
    @Test
    public void testcreateNumberArray2D() {
        double[][] data = { {1.0, 2.0}, {3.5, 4.5} };
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertEquals("Row count should match", 2, result.length);
        assertEquals("Column count for row 0 should match", 2, result[0].length);
        assertEquals("Column count for row 1 should match", 2, result[1].length);
        assertEquals("Element [0][0] should be 1.0", 1.0, result[0][0]);
        assertEquals("Element [0][1] should be 2.0", 2.0, result[0][1]);
        assertEquals("Element [1][0] should be 3.5", 3.5, result[1][0]);
        assertEquals("Element [1][1] should be 4.5", 4.5, result[1][1]);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createNumberArrayException2D() {
        // Passing a null 2D array should throw an IllegalArgumentException.
        DataUtilities.createNumberArray2D(null);
    }
    
   
}
