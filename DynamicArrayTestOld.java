public class DynamicArrayTestOld {

    public static void main(String[] args) {
        DynamicArray<Integer> test = new DynamicArray<Integer>(5);

        /**
        * Puts the characters of a string into an array structure
        */
        // tests for get, set, and add methods
        // test.set(0, 9);
        // test.set(1, 14);
        // test.add(1, 99999);
        // System.out.println(test.get(0));
        // System.out.println(test.get(1));
        // System.out.println(test.get(2));

        // System.out.println(test.size());

        // test.remove(1);
        // System.out.println(test.get(0));
        // System.out.println(test.get(1));
        // System.out.println(test.size());

        // DynamicArray<Integer> test_two = new DynamicArray<Integer>(2);
        // test_two.set(0, 55);

        // DynamicArray<Integer> test_three = test.append(test_two);

        // test.add(3, 88);
        // test.add(4, 97);

        // DynamicArray<Integer> test_four = test.splitSuffix(1);
        // System.out.println(test_four.get(0));
        // System.out.println(test_four.get(1));
        // System.out.println(test_four.get(2));
        // System.out.println(test_four.get(3));

        // test.set(0, 0);
        // test.set(1, 1);
        // test.set(2, 2);
        // test.set(3, 3);
        // test.set(4, 4);

        // System.out.println("original");
        // test.print();

        // System.out.println();
        // DynamicArray<Integer> extract = test.extract(1, 3);
        // System.out.println("extract");
        // extract.print();
        // System.out.println();
        // System.out.println("extract removed");
        // DynamicArray<Integer> result = test.delete(1, 3);
        // System.out.println();
        // result.print();

        // System.out.println();
        // System.out.println("extract re-inserted");
        // DynamicArray<Integer> resultTwo = result.insert(1, extract);
        // resultTwo.print();
        String s = "abcdefg";
        DynamicArray<Character> result = new DynamicArray<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            result.add(i, s.charAt(i));
        }
        result.print();

        String s1 = "";
        DynamicArray<Character> result1 = new DynamicArray<Character>(s1.length());
        for (int i = 0; i < s1.length(); i++) {
            result1.add(i, s1.charAt(i));
        }
        System.out.println(result1.size());

    }
}
