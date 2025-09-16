/**
 * Implementation of the DynamicArrayADT which mimics the functionality of an ArrayList in java.
 * Though some methods in group 3 are functional in DynamicArrays and not functional in ArrayList.
 * The object allocates memory for a set number of elements, with the ability to set, and get their values
 * as well as access the size of the object.
 * In addition to this, there is the option to add and remove elements themselves from the object.
 * These objects can be appended with other objects of the same type, as well as split into 
 * two using a given index as the splitting point. Other DynamicArrays can be inserted at a given index,
 * and sections of multiple elements can be removed using a start and end index. A selection of 
 * elements can also be specified and extracted as a separate DynamicArray object.
 * @param <T> the type of the DynamicArray object must be specified when a new instance is declared.
 */
public class DynamicArray<T> implements DynamicArrayADT<T> {
    /**
     * the internal array that holds the elements modified and accessed by the DynamicArray object.
     */
    private T[] values;

    /**
     * the number of elements set in the array
     */
    private int size;

    /**
     * Constructor for the DynamicArray class.
     * @param size an int that specifies the number of elements the object can store values in.
     */
    public DynamicArray(int size){
        this.values = allocate(size);
        this.size = 0; // initialized to zero because no elements have been set yet
    }

    /**
     * constructor for the DynamicArray class that makes a deep copy of the array passed as an argument
     * @param arr the DynamicArray being copied into the new DynamicArray
     */
    public DynamicArray(DynamicArray<T> arr) {
        this.values = this.allocate(arr.size());
        this.size = arr.size();

        for(int i = 0; i < arr.size(); i++){
            this.values[i] = arr.get(i);
        }
    }

    /**
     * private method to allocate space for an array of generic type
     * by first creating an array of type Object and then casting it to generic
     * @param len the length of the generic type array
     * @return the new generic array
     */
    @SuppressWarnings("unchecked")
    private T[] allocate(int len){
        return (T[]) new Object[len];
    }
    
    // GROUP ONE METHODS: BUILT IN FUNCTIONALITY FOR ARRAY //

    /**
     * Method to set an element at a specified index in the DynamicArray.
     * If the index is out of the valid range, [0, size) 
     * this method will throw an IndexOutOfBoundsException.
     * @param index the index of the element being set
     * @param val the value being stored in the specified index.
     * @return the value previously stored in that element, which could be null if it was previously unset.
     */
    public T set(int index, T val){
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (index == this.size) { // same thing as append(T val)
            this.size += 1; 
        }
        T previousVal = this.values[index];
        this.values[index] = val;
        return previousVal;
    }

    /**
     * Method to access a value stored in a specified index.
     * If the index is outside of the valid range of indicies, [0, size),
     * this method will throw an IndexOutOfBoundsException
     * @param index the index of the element being accessed.
     * @return the value stored in the specified element.
     */
    public T get(int index){
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        return this.values[index];
    }

    /**
     * Method for returning the number of elements stored in a DynamicArray object.
     * @return the length, or number of objects currently stored in the DynamicArray object
     */
    public int size(){
        return this.size;
    }

    // GROUP 2 : MUTABLE METHODS // 

    /**
     * Method to add an element to the DynamicArray object. 
     * An index must be within the valid range for the DynamicArray object, 
     * which includes [0, size]
     * If the index is outside of that range, it will throw an IndexOutOfBoundsException
     * @param index The index where the new element is being inserted.
     * @param val the value being stored at the newly created element.
     */
    public void add(int index, T val){
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        T[] newValues = this.allocate(this.size + 1); // new array with an added element
        int m = 0;
        for (int i = 0; i <= this.size; i++){
            if(i == index){
                newValues[i] = val;
            } else {
                newValues[i] = this.values[m];
                m+=1;
            }
        }
        this.size += 1;
        this.values = newValues;
    }

    /**
     * Overloaded append method that appends the new value onto the end of the current DynamicArray.
     * @param val the value being appended onto the DynamicArray.
     */
    public void append(T val){
        int index = this.size; // append the value onto the end
        if(index == this.values.length) {
            T[] newValues = this.allocate(index+1); // array one element larger
            for (int i = 0; i < index; i++) { // copy over the old elements
                newValues[i] = this.values[i];
            }
            this.values = newValues;
        }
        this.values[index] = val;
        this.size += 1;
    }

    /**
     * Method to remove an element from the DynamicArray object.
     * If the index specified is beyond the valid range for the DynamicArray, 
     * which is 0 until one less than the size of the DynamicArray object,
     * it will throw an IndexOutOfBoundsException.
     * @param index the index of the element being removed
     * @return returns the value that was stored in the removed index
     */
    public T remove(int index){
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        T[] newValues = this.allocate(this.size-1);
        T removedElem = this.values[index];  // store the element being removed so it can be returned

        int m = 0;
        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                continue; // do nothing so the counter increments and skips the removed element
            } else {
                newValues[m] = this.values[i];
                m+=1;
            }
        }
        this.values = newValues; // array without the removed elem is now the storage for this object
        this.size -= 1;
        return removedElem;
    }


    // GROUP 3 METHODS : FUNCTIONAL STYLE //

    /**
     * Method to concatenate another DynamicArray onto the end of the current DynamicArray object,
     * where the result is returned as a new DynamicArray object.
     * @param newArray The DynamicArray being concatenated onto the end of this current object
     * @return a new DynamicArray which is the result of concatenating a new DynamicArray onto the end of this current DynamicArray.
     */
    public DynamicArray<T> append(DynamicArrayADT<T> newArray){
        // you can return your specific class, but you have to be able to input anything implementing the interface 
        // which is why the argument is DynamicArrayADT<T> and not DynamicArray<T>

        // appending a DynamicArray is the same thing as inserting a DynamicArray 
        // beyond the last element of the current DynamicArray 
        DynamicArray<T> resultArray = this.insert(this.size, newArray);

        return resultArray; // result is retured without modifying either input
    }

    /**
     * Method for inserting multiple elements of a new DynamicArray into this current DynamicArray at
     * a specified index. The result is returned as a new DynamicArray object. 
     * If the index is outside of the valid range, from zero until the size of the current DynamicArray, it
     * will throw an IndexOutOfBoundsException.
     * @param index the index where the new elements will be inserted. The valid range is from zero until the size of the current DynamicArray.
     * @param newArray the DynamicArray being inserted into this current DynamicArray.
     * @return a new object of type DynamicArray which is the current DynamicArray with the new 
     * elements inserted.
     */
    public DynamicArray<T> insert(int index, DynamicArrayADT<T> newArray){
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        // contains space for the elements of this current DynamicArray and the elements of the inserted DynamicArray 
        DynamicArray<T> resultArr = new DynamicArray<T>(this.size + newArray.size());

        for (int i = 0; i <= this.size; i++) {
            if (i < index) {                     // items before the inserted segment
                T item = this.values[i];
                resultArr.append(item);
            } else if (i == index) {
                for (int j = 0; j < newArray.size(); j++) { // items within the inserted segment 
                    T item = newArray.get(j);
                    resultArr.append(item);
                }
            } else if (i > index) {                 // items after the insertion
                T item = this.values[i-1]; // subtract one to compensate for skipping adding an original value last loop
                resultArr.append(item);
            }
        }
        return resultArr;
    }

    /**
     * Method for returning the elements from a specified index and after as a new DynamicArray object.
     * If the specified index is beyond size of the DynamicArray, it will throw a IndexOutOfBounds exception.
     * @param index the index where the split begins, and this index is included in the new DynamicArray. The index must be between [0, this.size).
     * @return A new DynamicArray object of all the elements after and including the specified index.
     */
    public DynamicArray<T> splitSuffix(int index){
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }

        DynamicArray<T> suffixArray = this.extract(index, this.size); 
        // beginning index included in extract, ending index not included, so it's one beyond the indicies of the array

        return suffixArray; 
    }

    /**
     * Method for separating out the elements before, but not including, a specified index,
     * and returning those elements as a new DynamicArray.
     * It will throw an IndexOutOfBoundsException if the index is greater than the size of the array. Range is from (0, size].
     * @param index the index where all the elements before it are separated out into the new DynamicArray.
     * @return The new DynamicArray made up of the separated out elements.
     */
    public DynamicArray<T> splitPrefix(int index){
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }

        DynamicArray<T> prefixArray = this.extract(0, index);
        return prefixArray;  
    }

    /**
     * Method for removing multiple elements from, and including, a given index, up until
     * just before the second index in the current DynamicArray. If the index is outside of the valid range, [0, size), it will 
     * throw an IndexOutOfBoundsException.
     * The current DynamicArray object is not modified, and the array with the elements removed is returned as a new DynamicArray.
     * @param startIndex the starting index of the section being removed from the DynamicArray, this 
     * element is also included in the section being removed.
     * @param endIndex the ending index of the section of elements being removed. The element stored 
     * at this index is not removed and is included in the resulting DynamicArray.
     * @return A new DynamicArray object where elements from startIndex until just before endIndex have been removed from the current DynamicArray.
     */
    public DynamicArray<T> delete(int startIndex, int endIndex){
        int numVals = this.size();
        if (startIndex < 0 || startIndex > numVals) {
            throw new IndexOutOfBoundsException("The starting index is invalid");
        }
        if (endIndex < 0 || endIndex > numVals) {
            throw new IndexOutOfBoundsException("The ending index is invalid");
        }
        if (endIndex < startIndex) { 
            throw new IndexOutOfBoundsException("the starting index must be less than the ending index");
        }
        if (endIndex == startIndex) {
            DynamicArray<T> result = new DynamicArray<T>(this); // make a deep copy of the current array
            return result; 
        }

        DynamicArray<T> resultArr = new DynamicArray<T>(numVals - (endIndex-startIndex));

        for (int i = 0; i < numVals; i++) {
            if (i < startIndex) {
                T item = this.values[i];
                resultArr.append(item);
            } else if (i >= endIndex) {
                T item = this.values[i];
                resultArr.append(item);
            } else {
                continue; // do nothing to skip elements being removed
            }
        }
        return resultArr;
    }

    /**
     * Method for taking an extract from a DynamicArray starting at a given index and ending just before an ending index.
     * The result is returned as a new DynamicArray and the current DynamicArray remains unaltered.
     * If the starting and ending indicies are beyond the valid range of the DynamicArray, which is from 0 until the size of the DynamicArray,
     * it will throw an IndexOutOfBoundsException.
     * @param startIndex the beginning of the extract, this index will be included in the resulting DynamicArray
     * @param endIndex the ending index of the extract, the element at this index is not included in the resulting DynamicArray.
     * @return a new DynamicArray consisting of the elements from startIndex until just before endIndex.
     */
    public DynamicArray<T> extract(int startIndex, int endIndex){
        if (startIndex < 0 || startIndex > this.size) {
            throw new IndexOutOfBoundsException("Invalid starting index");
        }
        if (endIndex < 0 || endIndex > this.size) {
            throw new IndexOutOfBoundsException("Invalid ending index");
        }
        if (endIndex == startIndex) { // no elements will be extracted (?)
            DynamicArray<T> extractArr = new DynamicArray<T>(0); 
            return extractArr; 
        }
        if (endIndex < startIndex) {
            throw new IndexOutOfBoundsException("The starting index must be lower than the ending index");
        }

        DynamicArray<T> extractArr = new DynamicArray<T>(endIndex-startIndex);

        for (int i = 0; i < this.size; i++) {
            if (i < startIndex) {
                continue; // do nothing to the elements before the clipping
            } else if (i >= endIndex) {
                continue; // do nothing to the elements after the clipping
            } else { // items contained within the range of [startIndex, endIndex)
                T item = this.values[i];
                extractArr.append(item);
            }
        }
        return extractArr; 
    }

    /**
     * Method to convert the DynamicArray into a String representation of the object.
     * @return a String representation of the DynamicArray object
     */
    public String toString(){
        String result = "[";
        for(int i = 0; i < this.size; i++){
            String elem = this.values[i].toString();
            result = result.concat(elem);
            result = result.concat(", ");
        }
        if (this.size > 0){
            result = result.substring(0, result.length()-2); // get rid of the last whitespace and comma
        }
        result = result.concat("]");
        return result;
    }

    /**
     * Method to print out the elements in a DynamicArray object.
     */
    public void print(){
        int numVals = this.size();
        for (int i = 0; i < numVals; i++) {
            System.out.println(values[i]);
        }
    }
}
