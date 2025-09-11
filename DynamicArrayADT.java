/**
 * Interface for implementing a DynamicArray in java, which has all of the built in behaviors of an array,
 * such as being able to store multiple values, setting them to a specific index and retrieving them from 
 * that index and being able to index through, and access the size of the object.
 * It also includes additional functionality such as being able to add and delete elements, thus changing
 * the size of the DynamicArray, as well as functional methods such as sticking two DynamicArrays together,
 * and deleting and inserting multiple elements at once, taking an exerpt from the DynamicArray,
 * and splitting the DynamicArray before or after a specified index.
 */
public interface DynamicArrayADT<T>{

    // basic array behavior
    /**
     * Method to set an element at a specified index in the DynamicArray.
     * @param index the index of the element being set
     * @param val the value being stored in the specified index.
     */
    public void set(int index, T val);

    /**
     * Method to get or access a value stored in a specified element.
     * @param index the index of the element being accessed.
     * @return the value stored in the specified element.
     */
    public T get(int index);

    /**
     * Method to return the length of the DynamicArray.
     * @return the length or size of the DynamicArray
     */
    public int size();

    // mutable methods (modify the old DynamicArray)

    /**
     * Method to add an element to the DynamicArray object. 
     * An index must be within the valid range for the DynamicArray object. 
     * The valid range includes the first element, until one beyond the last pre-existing element.
     * For example, if a DynamicArray object had a length of 3, the valid indicies 
     * would include 0 through 3.
     * @param index The index where the new element is being inserted.
     * @param val the value being stored at the newly created element.
     */
    public void add(int index, T val);

    /**
     * Method to remove an element from the DynamicArray object.
     * If the index specified is beyond the valid range for the DynamicArray, 
     * it will throw a RuntimeException
     * @param index the index of the element being removed
     * @return returns the value that was stored in the removed index
     */
    public T remove(int index);

    // Functional methods (return the new DynamicArray) ///

    /**
     * Method to concatenate another DynamicArray onto this object.
     * @param new_array The DynamicArray being concatenated onto this object
     * @return this DynamicArray concatenated with the new DynamicArray as a single object of type DynamicArray
     */
    public DynamicArrayADT<T> append(DynamicArrayADT<T> new_array); 

    /**
     * Method for returning the elements after and including the specified index as a new DynamicArray.
     * If the specified index is beyond size of the DynamicArray, it will throw a RuntimeException.
     * @param index the index where the split begins, and this index is included in the new DynamicArray.
     * @return A new DynamicArray object of all the elements after and including the specified index.
     */
    public DynamicArrayADT<T> splitSuffix(int index);

    /**
     * Method for separating out the elements before, but not including, a specified index,
     * and returning those elements as a separate DynamicArray.
     * @param index the index where all the elements before it are separated out into the new DynamicArray.
     * @return The new DynamicArray made up of the separated out elements.
     */
    public DynamicArrayADT<T> splitPrefix(int index);

    /**
     * Method for removing multiple elements from, and including, a given index, up until
     * just before a second index.
     * @param start_index the starting index of the section being removed from the DynamicArray, this 
     * element is also included in the section being removed.
     * @param end_index the ending index of the section of elements being removed. The element stored 
     * at this index is not removed from the original DynamicArray.
     * @return the DynamicArray object without the removed elements.
     */
    public DynamicArrayADT<T> delete(int start_index, int end_index);

    /**
     * Method for taking a 'clipping' from a DynamicArray starting at a given index and ending at another.
     * @param start_index the beginning of the extract, this index will be included in the resulting DynamicArray
     * @param end_index the ending index of the extract, the element at this index is not included in the result.
     * @return a new DynamicArray of the 'clipped' elements.
     */
    public DynamicArrayADT<T> extract(int start_index, int end_index);

    /**
     * Method for inserting multiple elements of a DynamicArray into this DynamicArray at
     * a specified index. 
     * @param index the index where the new elements will be inserted.
     * @param new_array the DynamicArray being inserted into this DynamicArray.
     * @return a new object of type DynamicArray which is the old DynamicArray with the new 
     * elements inserted.
     */
    public DynamicArrayADT<T> insert(int index, DynamicArrayADT<T> new_array);

}