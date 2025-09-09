# Assignment 1: Deluxe Array

For this assignment you will write a new class called `DynamicArray` that implements that features of a dynamic array as described during lecture.  In a sense, we are writing our own version of `ArrayList`, although we won't try to exactly match all the methods in that class.Your class should be tested thoroughly to make sure all the methods work properly.  As an application, you will use it to write a simple file editing program that can replace text in a file.  (There is a command in Unix called `sed` that can do this, which we will be imitating.)

*This file contains partial instructions to help you get started, while we refine the details for the remainder.  We will release the full assignment soon.*

## Deluxe Array ADT

For this assignment we are providing a text version of the ADT.  You should translate this into a generic Java interface.  The operations included are listed below.

First up is a set describing basic array behavior.
* `set` stores a value at a given index.
* `get` returns the value stored at a given index.
* `size` returns the length of the array (# of elements stored)

Next are the methods that deal with adding or removing individual elements.
* `add` grows the array size by 1, adding a new value at a specified index.  Throws an exception if that index is outside the valid range (the position just past the last current element is considered valid for purposes of this method).
* `remove` shrinks the array size by 1, removing and returning the value at a specified index.  Throws an exception if that index is outside the valid range.

Finally we have methods that work on whole arrays at a time.
* `append` concatenates a deluxe array to this one and returns the result as a new deluxe array.
* `splitSuffix` returns the elements from a specified index and after as a new deluxe array.
* `splitPrefix` returns the elements before a specified index as a new deluxe array.
* `delete` removes elements from one index up to just before another, and returns the shorter resulting deluxe array.
* `extract` returns a new deluxe array containing the elements from one index up to just before another.
* `insert` inserts all the elements of a deluxe array at the specified index, returning the result as a new deluxe array.

Note that we have a mix of styles here:  `add` and `remove` are defined in a mutable style (they modify the deluxe array they are called upon), while the last six methods are all defined in a functional style (they return a newly created deluxe array and should not alter the original(s) in any way).

## Optional Addition

Our deluxe array can exceed the standard array in another respect:  we can make it allow for negative indices and indexing that does not start from 0.  To do this, you can add the methods below.

* `lowIndex` returns the lowest valid index for this collection.  For traditional Java arrays this will always be 0, but our deluxe array might have a different range of valid values (see below).
* `highIndex` returns the highest valid index for this collection.  For traditional Java arrays this will always be the same as `size-1`, but our deluxe array might have a different range of valid values (see below).
* `size` returns the number of elements between `lowIndex` and `highIndex`, inclusive.
* `indexInRange` returns a boolean value indicating whether a particular index is valid for purposes of the `get` method.

## Part Two:  Further Steps

In part two of this assignment we will describe the requirements for writing and testing a class that implements the interface described above.  Check back soon for details!
