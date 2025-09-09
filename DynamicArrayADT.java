public interface DynamicArrayADT<T>{

    // basic array behavior
    public void set(int index, T val);

    public T get(int index);

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

    // Functional methods (return the new DynamicArray)

    /**
     * method to concatenate another DynamicArray onto this object.
     * @param new_array The DynamicArray being concatenated onto this object
     * @return this DynamicArray concatenated with the new DynamicArray as a single object of type DynamicArray
     */
    public DynamicArrayADT<T> append(DynamicArrayADT<T> new_array);

    /**
     * Returns the elements after and including the specified index.
     * @param index
     * @return
     */
    public DynamicArrayADT<T> splitSuffix(int index);

    public DynamicArrayADT<T> splitPrefix(int index);

    public DynamicArrayADT<T> delete(int start_index, int end_index);

    public DynamicArrayADT<T> extract(int start_index, int end_index);

    public DynamicArrayADT<T> insert(int index, DynamicArrayADT<T> new_array);

    /*
     * splitSuffix` returns the elements from a specified index and after as a new deluxe array.
     * `splitPrefix` returns the elements before a specified index as a new deluxe array.
     * `delete` removes elements from one index up to just before another, and returns the shorter resulting deluxe array.
     * `extract` returns a new deluxe array containing the elements from one index up to just before another.
     * `insert` inserts all the elements of a deluxe array at the specified index, returning the result as a new deluxe array.
     */


}