/**
 * Implementation of the DynamicArrayADT which mimics the functionality of an ArrayList in java.
 * The object allocates memory for a set number of elements, with the ability to set and get their values
 * as well as access the size/length of the object.
 * In addition to this, there is the option to add and remove elements themselves from the object.
 * These objects can be appended with other objects of the same type, as well as split into 
 * two using a given index as the splitting point. Other DynamicArrays can be inserted at a given index,
 * and sections of multiple elements can be removed using a start and end index. A selection of 
 * elements can also be specified and extracted as a separate DynamicArray object.
 */
public class DynamicArray<T> implements DynamicArrayADT<T> {

    private int size;
    private T[] values;

    /**
     * Constructor for the DynamicArray class.
     * @param length an int that specifies the number of elements the object can store values in.
     */
    public DynamicArray(int length){
        this.size = length;
        this.values = allocate(length);
    }

    /**
     * constructor for the DynamicArray class that makes a deep copy of the array passed as an argument
     * @param arr the DynamicArray being copied into the new DynamicArray
     */
    public DynamicArray(DynamicArray<T> arr) {
        this.size = arr.size();
        this.values = allocate(arr.size());

        for(int i = 0; i<arr.size(); i++){
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
    
    // BUILT IN FUNCTIONALITY FOR ARRAY //

    /**
     * Method to set an element at a specified index in the DynamicArray.
     * If the index is out of the valid range, from 0 until one less than size(), 
     * this method will throw an IndexOutOfBoundsException.
     * @param index the index of the element being set
     * @param val the value being stored in the specified index.
     * @return the value previously stored in that element, which could be null.
     */
    public T set(int index, T val){
        if(index<0 || index>=this.size){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        T prev_val = this.values[index];
        this.values[index] = val;
        return prev_val;
    }

    /**
     * Method to get or access a value stored in a specified element.
     * If the index is outside of the valid range of indicies, from zero until one less than size(),
     * this method will throw an IndexOutOfBoundsException
     * @param index the index of the element being accessed.
     * @return the value stored in the specified element.
     */
    public T get(int index){
        if(index<0 || index>=this.size){
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        return this.values[index];
    }

    /**
     * Method for returning the size of a DynamicArray object.
     */
    public int size(){
        return this.size;
    }

    // MUTABLE METHODS // 

    /**
     * Method to add an element to the DynamicArray object. 
     * An index must be within the valid range for the DynamicArray object. 
     * The valid range includes 0 to the size() of the DynamicArray.
     * For example, if a DynamicArray object had a length of 3, the valid indicies 
     * would include 0 through 3.
     * If the index is outside of that range, it will throw an IndexOutOfBoundsException
     * @param index The index where the new element is being inserted.
     * @param val the value being stored at the newly created element.
     */
    public void add(int index, T val){
        
        if(index<0 || index>this.size){
            throw new IndexOutOfBoundsException("Invalid Index");
        }

        T[] new_values = allocate(this.size + 1); // new array with an added element
        int m = 0; //separate counter variable
        for(int i = 0; i <= this.size; i++){
            if(i == index){
                new_values[i] = val;
            } else{
                new_values[i] = this.values[m];
                m+= 1;
            }
        }
        this.values = new_values;
        this.size = this.size + 1; // size has been increased by one element
    }

    /**
     * Method to remove an element from the DynamicArray object.
     * If the index specified is beyond the valid range for the DynamicArray, 
     * which is 0 until one less than size(),
     * it will throw an IndexOutOfBoundsException.
     * @param index the index of the element being removed
     * @return returns the value that was stored in the removed index
     */
    public T remove(int index){
        if(index < 0 || index>= this.size){
            throw new IndexOutOfBoundsException("Invalid index");
        }

        T[] new_values = allocate(this.size-1);

        int m = 0;
        for(int i = 0; i < this.size; i++){
            if(i == index){
                // do nothing so the counter increments and skips the removed element
            } else{
                new_values[m] = this.values[i];
                m+=1;
            }
        }

        T removed_elem = this.values[index]; // this is still the old array, so the removed elem is still there
        this.values = new_values; // now this.values does NOT hold the removed element
        this.size = this.size - 1;
        return removed_elem;
    }

    /**
     * Method to concatenate another DynamicArray onto this object.
     * @param new_array The DynamicArray being concatenated onto this object
     * @return this DynamicArray concatenated with the new DynamicArray as a single object of type DynamicArray
     */
    public DynamicArray<T> append(DynamicArrayADT<T> new_array){
        // you can return your specific class, but you have to be able to input anything implementing the interface 
        // resulting array is the length of both the inputs
        DynamicArray<T> result_array = this.insert(this.size(), new_array);

        return result_array; // result is retured without modifying either input
    }

    /**
     * Method for returning the elements after and including the specified index as a new DynamicArray.
     * If the specified index is beyond size of the DynamicArray, it will throw a RuntimeException.
     * @param index the index where the split begins, and this index is included in the new DynamicArray.
     * @return A new DynamicArray object of all the elements after and including the specified index.
     */
    public DynamicArray<T> splitSuffix(int index){

        DynamicArray<T> suffix_array = this.extract(index, this.size());

        return suffix_array;
    }

    // /**
    //  * Method for separating out the elements before, but not including, a specified index,
    //  * and returning those elements as a separate DynamicArray.
    //  * @param index the index where all the elements before it are separated out into the new DynamicArray.
    //  * @return The new DynamicArray made up of the separated out elements.
    //  */
    public DynamicArray<T> splitPrefix(int index){
        DynamicArray<T> prefixArray = this.extract(0, index);

        return prefixArray; 
    }

    /**
     * Method for removing multiple elements from, and including, a given index, up until
     * just before a second index.
     * @param start_index the starting index of the section being removed from the DynamicArray, this 
     * element is also included in the section being removed.
     * @param end_index the ending index of the section of elements being removed. The element stored 
     * at this index is not removed from the original DynamicArray.
     * @return the DynamicArray object without the removed elements.
     */
    public DynamicArray<T> delete(int start_index, int end_index){
        if(start_index<0 || start_index>=this.size){
            throw new IndexOutOfBoundsException("The starting index is invalid");
        }
        if(end_index<0 || end_index>this.size){
            throw new IndexOutOfBoundsException("The ending index is invalid");
        }
        if(end_index<=start_index){
            throw new RuntimeException("the starting index must be less than the ending index");
        }

        DynamicArray<T> result_arr = new DynamicArray<T>(this.size - (end_index-start_index));

        int m = 0; //separate iterating var for shorter result arr
        for(int i = 0; i < this.size; i++){
            if(i< start_index){
                T item = this.values[i];
                result_arr.set(m, item);
                m+=1;
            } 
            if(i >= end_index){
                T item = this.values[i];
                result_arr.set(m, item);
                m+=1;
            } else{
                // do nothing to skip elements being removed
            }
        }
        return result_arr;
    }

    /**
     * Method to print out the elements in a DynamicArray object.
     */
    public void print(){
        for(int i = 0; i < this.size; i++){
            System.out.println(values[i]);
        }
    }

    /**
     * Method for taking a 'clipping' from a DynamicArray starting at a given index and ending at another.
     * If the starting and ending indicies are beyond the valid range of the Dynamic Array, which is from one until size(),
     * it will throw an IndexOutOfBoundsException.
     * @param start_index the beginning of the extract, this index will be included in the resulting DynamicArray
     * @param end_index the ending index of the extract, the element at this index is not included in the result.
     * @return a new DynamicArray of the 'clipped' elements.
     */
    public DynamicArray<T> extract(int start_index, int end_index){
        if(start_index<0 || start_index> this.size){
            throw new IndexOutOfBoundsException("Invalid starting index");
        }
        if(end_index<0 || end_index> this.size){
            throw new IndexOutOfBoundsException("Invalid ending index");
        }
        if(end_index==start_index){ // no elements will be extracted (?)
            DynamicArray<T> extract_arr = new DynamicArray<T>(0);
            return extract_arr;
        }
        if(end_index<start_index){
            throw new IndexOutOfBoundsException("The starting index must be lower than the ending index");
        }

        DynamicArray<T> extract_arr = new DynamicArray<T>(end_index-start_index);

        int m = 0; //counter for shorter arr
        for(int i = 0; i<this.size; i++){
            if(i<start_index){
                continue; // do nothing to the elements before the clipping
            }
            if(i>=end_index){
                continue; // do nothing to the elements after the clipping
            } 
            T item = this.values[i];
            extract_arr.set(m, item);
            m+=1;
        }

        return extract_arr;
    }


    /**
     * Method for inserting multiple elements of a DynamicArray into this DynamicArray at
     * a specified index. If that index is outside of the valid range, from zero until size(), it
     * will throw a new IndexOutOfBoundsException.
     * @param index the index where the new elements will be inserted.
     * @param new_array the DynamicArray being inserted into this DynamicArray.
     * @return a new object of type DynamicArray which is the old DynamicArray with the new 
     * elements inserted.
     */
    public DynamicArray<T> insert(int index, DynamicArrayADT<T> new_array){
        if(index<0 || index>this.size){
            throw new IndexOutOfBoundsException("Invalid index");
        }

        DynamicArray<T> result_arr = new DynamicArray<T>(this.size+(new_array.size()));

        int m = 0;
        for(int i = 0; i<=this.size; i++){
            if(i<index){                     // items before the inserted segment
                T item = this.values[i];
                result_arr.set(m, item);
                m+=1;
            }
            if(i == index){
                for(int j = 0; j<new_array.size(); j++){ // items within the inserted segment 
                    T item = new_array.get(j);
                    result_arr.set(m, item);
                    m+=1;
                }
            }
            if(i > index){                  // items after the insertion
                T item = this.values[i-1]; // subtract one to compensate for skipping adding an original value last loop
                result_arr.set(m, item);
                m+=1;
            }
        }

        return result_arr;
    }

}
