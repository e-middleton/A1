import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Class to test all the methods and behavior of DynamicArray class which implements DynamicArrayADT.
 */
public class DynamicArrayTests {

    private DynamicArray<Character> a1;
    private DynamicArray<Character> a2;
    private DynamicArray<Character> empty;
    private DynamicArray<Character> s;

    /**
     * Initializes DynamicArray<Character> instances to be used for testing.
     * Re-initializes before each test.
     * This ensures that tests do not interfere with one another.
     */
    @Before
    public void setUp() {
        a1 = stringToArray("abcdef");
        a2 = stringToArray("wxyz");
        empty = stringToArray("");
        s = stringToArray("s");
    }

    /**
     * Puts the characters of a string into an array structure
     * @param s the string being turned into an array
     * @return the string object converted into an array
     */
    public DynamicArray<Character> stringToArray(String s) {
        DynamicArray<Character> result = new DynamicArray<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            result.set(i, s.charAt(i));
        }
        return result;
    }

    /**
     * Compares the sizes of a DynamicArray<Character> and a string
     */
    public void compareSize(DynamicArray<Character> arr, String s){
        assertEquals("["+s+"] Array lengths are equal", arr.size(), s.length());
    }

    /**
     * Compares each element in a DynamicArray<Character>
     * against those in a string.
     */
    public void compareToString(DynamicArray<Character> arr, String s) {
        for (int i = 0; i < arr.size(); i++) {
            assertEquals("["+s+"] Elements are equal at index " + i, arr.get(i).charValue(), s.charAt(i));
        }
    }

    // ~*~*~*~*~ Append Tests Below ~*~*~*~*~

    /**
     * Tests that appending two non-empty arrays results in
     * a new array containing the elements of both, in order.
     */
    @Test
    public void testAppendStandard() {
        compareToString(a1.append(a2), "abcdefwxyz");
        compareToString(a2.append(a1), "wxyzabcdef");
    }

    /**
     * Tests that appending a non-empty array to itself results in
     * a new array containing the elements repeated twice.
     */
    @Test
    public void testAppendSelf() {
        compareToString(a1.append(a1), "abcdefabcdef");
        compareToString(a2.append(a2), "wxyzwxyz");
    }

    /**
     * Tests that appending a non-empty array and an array of
     * length one results in a new array containing the elements
     * of both, in order.
     */
    @Test
    public void testAppendSingle() {
    compareToString(a1.append(s),"abcdefs");
    compareToString(s.append(a1),"sabcdef");
    compareToString(s.append(s),"ss");
    }

    /**
     * Tests that appending an empty array
     * results in a new array that matches the other array
     */
    @Test
    public void testAppendEmpty() {
        compareToString(a1.append(empty), "abcdef");
        compareToString(empty.append(a1), "abcdef");
        compareToString(empty.append(empty), "");
    }

    // ~*~*~*~*~ Add Extract Tests Below ~*~*~*~*~

   /**
    * Tests that for a nonempty DynamicArray, extracting elements [1, 3) selects
    * elements 1 and 2 and returns them as a separate DynamicArray.
    */
   @Test
   public void testExtractStandard() {
       compareToString(a1.extract(1, 3), "bc");
   }

   /**
    * Tests that for a non-empty array, extracting indicies 0 to size() extracts
    * the entire array.
    */
   @Test
   public void testExtractEntire() {
       compareToString(a1.extract(0, a1.size()), "abcdef");
   }

   /**
    * Tests that extracting using matching indicies, such as (0,0) will extract no elements,
    * and an empty array will be returned
    */
   @Test
   public void testExtractZero() {
       compareToString(a1.extract(0,0), "");
   }

   /**
    * Tests that extracting from an empty array will return an empty array.
    */
   @Test
   public void testExtractEmpty() {
       compareToString(empty.extract(0,0), "");
   }

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractBounds() {
        DynamicArray<Character> extract = a1.extract(-1, 5);
        // More bounds that you can check:
        DynamicArray<Character> extract1 = a1.extract(-3, 2); // low index is negative
        DynamicArray<Character> extract2 = a1.extract(2, 14); // high index is greater than array length
        DynamicArray<Character> extract3 = a1.extract(14, 14); // low index is greater than array length
        DynamicArray<Character> extract4 = a1.extract(3, -3); // high index is negative
        DynamicArray<Character> extract5 = a1.extract(3, 1); // high index is less than low
    }

    // ~*~*~*~*~ Add Add() Tests Below ~*~*~*~*~

    /**
     * Tests that when add is called for a given index, 
     * a new element is added with that value and the other elements shift right.
     * Also tests that the size of the DynamicArray object is updated to be one greater.
     */
    @Test
    public void testAddStandard() {
        Character addition = 'm';
        int prevSize = a1.size();

        a1.add(2, addition);
        compareToString(a1, "abmcdef");
        assertEquals(a1.size(), prevSize+1); 
    }

    /**
     * Tests that adding an element at the index size() adds an element to the very end of the array.
     */
    @Test
    public void testAddEnd() {
        Character addition = 'g';
        a1.add(a1.size(), addition);
        compareToString(a1, "abcdefg");
    }

    /**
     * Tests that adding an element at index 0 adds it to the very beginning of the array.
     */
    @Test
    public void testAddStart() {
        Character addition = 'm';
        a1.add(0, addition);
        compareToString(a1, "mabcdef");
    }

    /**
     * Tests that it is possible to add elements to an empty array.
     */
    @Test
    public void testAddEmpty() {
        Character letter = 'm';
        empty.add(0, letter);
        compareToString(empty, "m");

    }

    /**
     * Tests that null values can be added into DynamicArrays
     * It allows me to add them, but it's difficult to fully
     * check if it's been added correctly so more work would need
     * to be done here.
     */
    @Test
    public void testAddNull() {
        Character letter = null;
        a1.add(0, letter);
    }

    /**
     * Tests that invalid indicies throw the expected IndexOutOfBoundsException
     */
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testAddBounds(){
        Character letter = 'm';
        a1.add(-1, letter); // negative index
        a1.add(a1.size()+1, letter); // index greater than the length of the array
    }

    // ~*~*~*~*~Tests For Get Below ~*~*~*~*~

    /** 
     * Tests that accessing an element at an index returns the value stored there in a non-empty array
     */
    @Test 
    public void testGetStandard() {
        Character result = a1.get(1);
        Character expected = 'b';
        assertEquals("Characters should be equal" , result, expected);
    }

    /**
     * Tests that getting the very last element of an array (size - 1) returns the correct value.
     */
    @Test
    public void testGetEnd() {
        Character result = a1.get(a1.size()-1);
        Character expected = 'f';
        assertEquals("Characters should be equal" , result, expected);
    }

    /**
     * Tests that getting the first element returns the correct value.
     */
    @Test
    public void testGetStart() {
        Character result = a1.get(0);
        Character expected = 'a';
        assertEquals("Characters should be equal" , result, expected);
    }

    /**
     * Tests that getting the element of a single element array returns the correct element
     */
    @Test
    public void testGetSingle(){
        Character result = s.get(0);
        Character expected = 's';
        assertEquals("Characters should be equal" , result, expected);
    }

    /**
     * Tests that trying to get elements at negative indicies or indicies greater than the length throw the 
     * IndexOutOfBoundsException
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetBounds() {
        a1.get(-1); // negative index
        a1.get(44); // index greater than length
    }

    /**
     * Tests that trying to get an element from an empty array throws an IndexOutOfBoundsException
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEmpty() {
        empty.get(0);
    }
}





