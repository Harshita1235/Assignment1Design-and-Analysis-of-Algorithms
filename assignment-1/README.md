# Assignment 1 – Divide and Conquer Algorithms

## Algorithms Implemented
- MergeSort (O(n log n))
- QuickSort (average O(n log n), worst O(n²))
- Select K-th (simple version: O(n log n))
- Closest Pair of Points (brute force: O(n²))

## How to Compile and Run
Open a terminal in this folder and run:

### MergeSort

javac MergeSortDemo.java
java MergeSortDemo


### QuickSort

javac QuickSortDemo.java
java QuickSortDemo


### Select K-th
javac SelectKthDemo.java
java SelectKthDemo

### Closest Pair
javac ClosestPairDemo.java
java ClosestPairDemo



### Running-Time Analysis

MergeSort: T(n) = 2T(n/2) + O(n) → Θ(n log n)

QuickSort: Average Θ(n log n), Worst Θ(n²)

Select K-th (sort version): Θ(n log n)

Closest Pair (brute force): Θ(n²)


### Notes

These are simple beginner versions.

They work correctly on small and medium inputs.

Future improvements:

Median-of-Medians Select (O(n))

Divide-and-Conquer Closest Pair (O(n log n))