# CSCE 350 Programming Assignment

I completed Problems 1-5 using the textbook and powerpoint slides as my reference.

## Compile Guide

The files were tested on OpenJDK Java Version 21.0.6 on Ubuntu

For compiling and executing a generic ProblemN.java, write the following code in the terminal:
```
javac ProblemN/ProblemN.java
java ProblemN/ProblemN
```
The `javac` command creates a .class file that is then run by the `java` command.

## Problem Input Formatting

### Problem 1
input.txt should be formatted using a single space delimiter between 2D array values,
and a new line character for every row.

Valid example for input.txt:
```
0 1 0 0 1 1
1 0 1 0 0 0
0 1 0 1 0 0
0 0 1 0 1 0
0 0 0 1 0 1
1 0 0 0 1 0
```

The result is printed to output.txt in the same format

### Problem 2
input.txt should be formatted using a single space delimiter between array values

Valid example for input.txt:
```
3.14 -0.5 42.0 0.001 2.71828 100.5 -3.14 1.618 5.0
```

You will be prompted via the console to either pick MergeSort or QuickSort.
The execution time will be printed into the console, and the result is printed to output.txt in the same format

### Problem 3
input.txt should be formatted using a single space delimiter between array key values

Valid example for input.txt:
```
4 1 3 2 16 9 10 14 8 7
```

You will be prompted via the console to either pick MaxHeap or MinHeap.
The execution time will be printed into the console, and the result is printed to output.txt in the same format

### Problem 4
input.txt should be formatted with the first line being the pattern string and the second being the text

Valid example for input.txt:
```
test
this is a test string
```

The execution time will be printed into the console, and the result is printed to output.txt in the same format

### Problem 5
input.txt should be formatted using a single space delimiter between 2D array values,
and a new line character for every row.

Valid example for input.txt:
```
0.0 2.5 9.1 4.0
2.5 0.0 3.3 6.7
9.1 3.3 0.0 1.2
4.0 6.7 1.2 0.0
```

The result is printed to output.txt in the same format

If the distance between two vertices is infinity (no edge exists), it's represented as "inf" in the matrix. Valid example of this in input.txt:
```
0.0 3.0 inf 7.0
8.0 0.0 2.5 inf
5.5 inf 0.0 1.0
2.0 inf inf 0.0
```