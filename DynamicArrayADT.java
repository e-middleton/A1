/**
 * Interface for implementing a DynamicArray in java, which has all of the built in behaviors of an array,
 * such as being able to store multiple values, setting them to a specific index and retrieving them from 
 * that index and being able to index through, and access the size of the object.
 * It also includes additional functionality such as being able to add and delete elements, thus changing
 * the size of the DynamicArray, as well as functional methods such as sticking two DynamicArrays together,
 * and deleting and inserting multiple elements at once, taking an exerpt from the DynamicArray,
 * and splitting the DynamicArray before or after a specified index.
 * @param <T> the type of the class implementing DynamicArrayADT must be specified when an instance is declared.
 */
public interface DynamicArrayADT<T>{

    // basic array behavior
    
    /**
     * Method to set an element at a specified index in the object implementing DynamicArrayADT.
     * If the index is out of the valid range, [0, size) 
     * this method will throw an IndexOutOfBoundsException.
     * @param index the index of the element being set
     * @param val the value being stored in the specified index.
     * @return the value previously stored in that element, which could be null if it was previously unset.
     */
    public T set(int index, T val);

    /**
     * Method to access a value stored in a specified index.
     * If the index is outside of the valid range of indicies, [0, size),
     * this method will throw an IndexOutOfBoundsException
     * @param index the index of the element being accessed.
     * @return the value stored in the specified element.
     */
    public T get(int index);

   /**
     * Method for returning the number of elements stored in an object implementing DynamicArrayADT.
     * @return the length, or number of objects stored, of the object implementing DynamicArrayADT.
     */
    public int size();

    // mutable methods (modify the old DynamicArray)

    /**
     * Method to add an element to the object implementing DynamicArrayADT. 
     * An index must be within the valid range for the DynamicArrayADT object. 
     * The valid range includes 0 until, and including, the size of the DynamicArrayADT.
     * If the index is outside of that range, it will throw an IndexOutOfBoundsException
     * @param index The index where the new element is being inserted.
     * @param val the value being stored at the newly created element.
     */
    public void add(int index, T val);

    /**
     * Method to remove an element from the DynamicArrayADT object.
     * If the index specified is beyond the valid range for the DynamicArrayADT, 
     * which is 0 until one less than the size of the DynamicArrayADT object,
     * it will throw an IndexOutOfBoundsException.
     * @param index the index of the element being removed
     * @return returns the value that was stored in the removed index
     */
    public T remove(int index);

    // Functional methods (return the new DynamicArray) ///

    /**
     * Method to concatenate another DynamicArrayADT object onto the end of the current DynamicArrayADT object,
     * where the result is returned as a new DynamicArrayADT object.
     * @param newArray The DynamicArrayADT being concatenated onto the end of this current object
     * @return a new DynamicArrayADT which is the result of concatenating a new DynamicArrayADT onto the end of this current DynamicArrayADT.
     */
    public DynamicArrayADT<T> append(DynamicArrayADT<T> newArray); 

    /**
     * Method for inserting multiple elements of a new DynamicArrayADT into this current DynamicArrayADT at
     * a specified index. The result is returned as a new DynamicArrayADT object. 
     * If the index is outside of the valid range, from zero until the size of the current DynamicArrayADT, it
     * will throw an IndexOutOfBoundsException.
     * @param index the index where the new elements will be inserted. The valid range is from zero until the size of the current DynamicArrayADT.
     * @param newArray the DynamicArrayADT being inserted into this current DynamicArrayADT.
     * @return a new object of type DynamicArrayADT which is the current DynamicArrayADT with the new 
     * elements inserted.
     */
    public DynamicArrayADT<T> insert(int index, DynamicArrayADT<T> newArray);

    /**
     * Method for returning the elements from a specified index and after as a new DynamicArrayADT object.
     * If the specified index is beyond size of the DynamicArrayADT, it will throw a IndexOutOfBoundsException.
     * @param index the index where the split begins, and this index is included in the new DynamicArray. The index must be from 0 to the size of the DynamicArrayADT.
     * @return A new DynamicArrayADT object of all the elements after and including the specified index.
     */
    public DynamicArrayADT<T> splitSuffix(int index);

    /**
     * Method for separating out the elements before, but not including, a specified index,
     * and returning those elements as a new DynamicArrayADT.
     * If the index is zero, it will throw an IndexOutOfBoundsException, because there are no
     * elements to access before the index zero, and it will throw an IndexOutOfBoundsException
     * if the index is greater than the size of the array. Range is from (0, size).
     * @param index the index where all the elements before it are separated out into the new DynamicArrayADT.
     * @return The new DynamicArrayADT made up of the separated out elements.
     */
    public DynamicArrayADT<T> splitPrefix(int index);

    /**
     * Method for removing multiple elements from, and including, a given index, up until
     * just before the second index in the current DynamicArrayADT. If the index is outside of the valid range, from zero 
     * until the size of the DynamicArrayADT, it will 
     * throw an IndexOutOfBoundsException.
     * The current DynamicArrayADT object is not modified, and the array with the elements removed is returned as a new DynamicArrayADT.
     * @param startIndex the starting index of the section being removed from the DynamicArrayADT, this 
     * element is also included in the section being removed.
     * @param endIndex the ending index of the section of elements being removed. The element stored 
     * at this index is not removed and is included in the resulting DynamicArrayADT.
     * @return A new DynamicArrayADT object where elements from startIndex until just before endIndex have been removed from the current DynamicArrayADT.
     */
    public DynamicArrayADT<T> delete(int startIndex, int endIndex);

    /**
     * Method for taking an extract from a DynamicArrayADT starting at a given index and ending just before an ending index.
     * The result is returned as a new DynamicArrayADT and the current DynamicArrayADT remains unaltered.
     * If the starting and ending indicies are beyond the valid range of the DynamicArrayADT, which is from 0 until the size of the DynamicArrayADT,
     * it will throw an IndexOutOfBoundsException.
     * @param startIndex the beginning of the extract, this index will be included in the resulting DynamicArrayADT
     * @param endIndex the ending index of the extract, the element at this index is not included in the resulting DynamicArrayADT.
     * @return a new DynamicArrayADT consisting of the elements from startIndex until just before endIndex.
     */
    public DynamicArrayADT<T> extract(int startIndex, int endIndex);

    /**
     * Method to turn the object implementing the DynamicArrayADT into a String representation
     * @return the String representation of the object implementing the DynamicArrayADT.
     */
    public String toString();

}