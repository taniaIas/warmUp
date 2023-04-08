package com.endava.internship.warmup.domain.service;

import java.util.*;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class ArrayProcessorWithForLoops implements ArrayProcessor {

    /**
     * Return true if there are no numbers that divide by 10
     *
     * @param input non-null immutable array of ints
     */


    @Override
    public boolean noneMatch(final int[] input) {
        for (int i : input) {
            if (i % 10 == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return true if at least one value in input matches the predicate
     *
     * @param input     non-null immutable array of ints
     * @param predicate invoke the predicate.test(int value) on each input element
     */
    @Override
    public boolean someMatch(final int[] input, IntPredicate predicate) {
        for (int i : input) {
            if (predicate.test(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if all values processed by function, matches the predicate
     *
     * @param input     non-null immutable array of Strings. No element is null
     * @param function  invoke function.applyAsInt(String value) to transform all the input elements into an int value
     * @param predicate invoke predicate.test(int value) to test the int value obtained from the function
     */
    @Override
    public boolean allMatch(final String[] input,
                            ToIntFunction<String> function,
                            IntPredicate predicate) {
        for (String item : input) {
            int i = function.applyAsInt(item);
            if (!predicate.test(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Copy values into a separate array from specific index to stopindex
     *
     * @param input          non-null array of ints
     * @param startInclusive the first index of the element from input to be included in the new array
     * @param endExclusive   the last index prior to which the elements are to be included in the new array
     * @throws IllegalArgumentException when parameters are outside of input index bounds
     */
    @Override
    public int[] copyValues(int[] input, int startInclusive, int endExclusive) throws IllegalArgumentException {
        if (input.length - 1 < startInclusive
                || input.length - 1 < endExclusive
                || startInclusive > endExclusive
                || startInclusive < 0
                || endExclusive < 0)
            throw new IllegalArgumentException();
        int[] copiedArray = Arrays.copyOfRange(input, startInclusive, endExclusive);
        return copiedArray;
    }

    /**
     * Replace even index values with their doubles and odd indexed elements with their negative
     *
     * @param input non-null immutable array of ints
     * @return new array with changed elements
     */
    @Override
    public int[] replace(final int[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = i % 2 == 0 ? input[i] * 2 : input[i] * (-1);
        }
        return input;
    }

    /**
     * Find the second max value in the array
     *
     * @param input non-null immutable array of ints
     */
    @Override
    public int findSecondMax(final int[] input) {

        Arrays.stream(input).sorted().distinct();
        return input[input.length - 2];
    }


    /**
     * Return in reverse first negative numbers, then positive numbers from array
     *
     * @param input non-null immutable array of ints.
     * @return example: input {3, -5, 4, -7, 2 , 9}
     * result: {-7, -5, 9, 2, 4, 3}
     */
    @Override
    public int[] rearrange(final int[] input) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] < 0) list.add(input[i]);
        }
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] > 0) list.add(input[i]);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Remove (filter) all values which are smaller than (input max element - 10)
     *
     * @param input non-null immutable array of ints
     * @return The result array should not contain empty cells!
     */
    @Override
    public int[] filter(final int[] input) {
        Arrays.stream(input).sorted();
        List<Integer> list = Arrays.stream(input).boxed().toList();
        Arrays.stream(input).sorted().distinct();
        int max = input[input.length - 1];
        return list.stream().filter(i -> i > max - 10).mapToInt(i -> i).toArray();
    }

    /**
     * Insert values into input array at a specific index.
     *
     * @param input          non-null immutable array of ints.
     * @param startInclusive the index of input at which the first element from values array should be inserted
     * @param values         the values to be inserted from startInclusive index
     * @return new array containing the combined elements of input and values
     * @throws IllegalArgumentException when startInclusive is out of bounds for input
     */
    @Override
    public int[] insertValues(final int[] input, int startInclusive, int[] values) throws IllegalArgumentException {

        if (startInclusive < 0 || startInclusive >= input.length)
            throw new IllegalArgumentException();

        List<Integer> list = Arrays.stream(input)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> listToAdd = Arrays.stream(values).boxed().toList();
        list.addAll(startInclusive, listToAdd);
        return list.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Merge two sorted input and input2 arrays so that the return values are also sorted
     *
     * @param input  first non-null array
     * @param input2 second non-null array
     * @return new array containing all elements sorted from input and input2
     * @throws IllegalArgumentException if either input or input are not sorted ascending
     */
    @Override
    public int[] mergeSortedArrays(int[] input, int[] input2) throws IllegalArgumentException {

        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] > input[i + 1]) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < input2.length - 1; i++) {
            if (input2[i] > input2[i + 1]) {
                throw new IllegalArgumentException();
            }
        }

        List<Integer> list1 = Arrays.stream(input).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(input2).boxed().toList();
        list1.addAll(list2);
        return list1.stream().sorted().mapToInt(i -> i).toArray();
    }

    /**
     * In order to execute a matrix multiplication, in this method, please validate the input data throwing exceptions for invalid input. If the the
     * input params are satisfactory, do not throw any exception.
     * <p>
     * Please review the matrix multiplication https://www.mathsisfun.com/algebra/matrix-multiplying.html
     *
     * @param leftMatrix  the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @return
     * @throws NullPointerException     when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */
    @Override
    public void validateForMatrixMultiplication(int[][] leftMatrix, int[][] rightMatrix) throws NullPointerException, IllegalArgumentException {
        checkNotNull(leftMatrix);
        checkNotNull(rightMatrix);
        checkArrayWithEqualRow(leftMatrix);
        checkArrayWithEqualRow(rightMatrix);
        if (leftMatrix[0].length != rightMatrix.length) {
            throw new IllegalArgumentException("");
        }
    }

    private void checkArrayWithEqualRow(int[][] arr) {
        int rowLength = arr[0].length;
        for (int[] i : arr) {
            if (rowLength != i.length) {
                throw new IllegalArgumentException("row size is wrong");
            }
        }
    }

    private void checkNotNull(int[][] arr) {
        if (arr.length == 0) throw new IllegalArgumentException("Zero");
        for (int[] row : arr) {
            if (row.length == 0) {
                throw new NullPointerException("Zero");
            }
        }
    }

    /**
     * Perform the matrix multiplication as described in previous example Please review the matrix multiplication
     * https://www.mathsisfun.com/algebra/matrix-multiplying.html
     *
     * @param leftMatrix  the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @throws NullPointerException     when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */
    @Override
    public int[][] matrixMultiplication(final int[][] leftMatrix, final int[][] rightMatrix) throws NullPointerException, IllegalArgumentException {
        validateForMatrixMultiplication(leftMatrix,rightMatrix);
        int lenghtLeftMatrix = leftMatrix.length;
        int lenghtRightMatrix = rightMatrix.length;
        int matrix[][] = new int[lenghtLeftMatrix][lenghtLeftMatrix];
        for (int i = 0; i < lenghtLeftMatrix; i++) {

            for (int m = 0; m < lenghtLeftMatrix; m++) {
                int sum = 0;
                for (int j = 0; j < lenghtRightMatrix; j++) {
                    sum += leftMatrix[i][j] * rightMatrix[j][m];
                }
                matrix[i][m] = sum;

            }
        }
        return matrix;
    }

    /**
     * Return only distinct values in an array.
     *
     * @param input non-null immutable array of ints.
     */
    @Override
    public int[] distinct(final int[] input) {
        return Arrays.stream(input).distinct().toArray();
    }
}
