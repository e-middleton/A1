public class DynamicArray<T> implements DynamicArrayADT<T> {

    int size;
    T[] values;

    public DynamicArray(int size){
        this.size = size;
        this.values = allocate(size);
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

    public void set(int index, T val){
        this.values[index] = val;
    }

    public T get(int index){
        return this.values[index];
    }

    public int size(){
        return this.size;
    }

    // MUTABLE METHODS // 

    /**
     * Method to add an element to the DynamicArray object. 
     * An index must be within the valid range for the DynamicArray object. 
     * The valid range includes the first element, until one beyond the last pre-existing element.
     * For example, if a DynamicArray object had a length of 3, the valid indicies 
     * would include 0 through 3.
     * @param index The index where the new element is being inserted.
     * @param val the value being stored at the newly created element.
     */
    public void add(int index, T val){
        // create a new array one size larger
        // create a loop that copies until the index,
        // insert the new element and increment the counter
        // finish copying the old array

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

    public T remove(int index){
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

        T removed_elem = this.values[index];
        this.values = new_values;
        this.size = this.size - 1;
        return removed_elem;
    }


    // you can return your specific class, but you have to be able to input anything implementing the interface 
    public DynamicArray<T> append(DynamicArrayADT<T> new_array){
        // resulting array is the length of both the inputs
        DynamicArray<T> end_array = new DynamicArray<T>(this.size + new_array.size()); 

        // add the elements of this current DynamicArray to the resulting array
        for(int i = 0; i < this.size; i++){
            T val = this.values[i];
            end_array.set(i, val);
        }

        // add the elements of the array being concatenated to the resulting array
        for(int j = 0; j < new_array.size(); j++){
            T val = new_array.get(j);
            end_array.set(this.size+j, val);
        }

        return end_array; // result is retured without modifying either input
    }

    public DynamicArray<T> splitSuffix(int index){

        DynamicArray<T> suffix_array = new DynamicArray<T>(this.size - index); 
        // new length is shorter by whatever the index is

        for(int i = 0; i < this.size-index; i++){
            T val = this.values[index+i];
            suffix_array.set(i, val);
        }

        return suffix_array;

    }

public static void main(String[] args) {
    DynamicArray<Integer> test = new DynamicArray<Integer>(2);

    // tests for get, set, and add methods
    test.set(0, 9);
    test.set(1, 14);
    test.add(1, 99999);
    // System.out.println(test.get(0));
    // System.out.println(test.get(1));
    // System.out.println(test.get(2));

    // System.out.println(test.size());

    // test.remove(1);
    // System.out.println(test.get(0));
    // System.out.println(test.get(1));
    // System.out.println(test.size());

    DynamicArray<Integer> test_two = new DynamicArray<Integer>(2);
    test_two.set(0, 55);

    DynamicArray<Integer> test_three = test.append(test_two);

    test.add(3, 88);
    test.add(4, 97);

    DynamicArray<Integer> test_four = test.splitSuffix(1);
    System.out.println(test_four.get(0));
    System.out.println(test_four.get(1));
    System.out.println(test_four.get(2));
    System.out.println(test_four.get(3));
}

}
