# Assignment 1 — Divide & Conquer Algorithms

##  Learning Goals
- Implement classic divide-and-conquer algorithms with **safe recursion patterns**.  
- Analyze **running-time recurrences** using **Master Theorem** (Cases 1–3) and **Akra–Bazzi intuition**.  
- Collect metrics (time, recursion depth, comparisons/allocations) and discuss empirical results.  
- Use a clean **Git workflow** (issues, branches, commits).

---

##  Architecture Overview

Each algorithm is implemented in its own demo class:

| File | Algorithm | Key Features |
|------|------------|---------------|
| `MergeSortDemo.java` | Merge Sort | Divide & Conquer (Case 2), linear merge, reusable buffer, small-n cutoff possible |
| `QuickSortDemo.java` | Quick Sort | Randomized pivot, recurse on smaller partition, iterative on larger |
| `SelectKthDemo.java` | Deterministic Select (Median-of-Medians) | Groups of 5, median-of-medians pivot, O(n), recurse only on needed side |
| `ClosestPairDemo.java` | Closest Pair of Points (2D) | Divide by x-coordinate, check strip by y-order, O(n log n) |

### Depth & Allocation Control
- **MergeSort:** Recursion depth ≈ ⌈log₂ n⌉, with a shared temp buffer to minimize allocations.  
- **QuickSort:** Always recurse into **smaller** partition and iterate on larger, ensuring depth ≤ ~2 × log₂ n.  
- **Select:** Recurse only into the necessary partition → linear time and logarithmic depth.  
- **ClosestPair:** Recursive splitting stops at small n (≤ 3) for direct O(1) comparison.

Metrics (time, recursion depth, comparisons) are gathered through a simple measurement harness and exported to CSV for plotting.

---

##  Recurrence Analyses

### 1️ Merge Sort
- **Recurrence:** `T(n) = 2T(n/2) + Θ(n)`  
- **Master Theorem (Case 2):**  
  `a = 2, b = 2, f(n) = Θ(n) ⇒ T(n) = Θ(n log n)`  
- **Observation:** Linear merge dominates small recursive overhead.

---

### 2️ Quick Sort (Randomized)
- **Expected Recurrence:** `T(n) = T(k) + T(n−k−1) + Θ(n)`  
- **Average Case:** `E[T(n)] = 2T(n/2) + Θ(n) ⇒ Θ(n log n)`  
- **Worst Case:** Θ(n²), but randomized pivot makes it extremely rare.  
- **Depth:** `≤ 2·log₂ n + O(1)` (recursing on smaller side).  

---

### 3️ Deterministic Select (Median of Medians)
- **Recurrence:** `T(n) = T(n/5) + T(7n/10) + Θ(n)`  
- **Akra–Bazzi intuition:** Linear term dominates ⇒ `T(n) = Θ(n)`  
- **Comment:** Only one recursive branch → bounded recursion depth, guaranteed linear time.  

---

### 4️ Closest Pair of Points
- **Recurrence:** `T(n) = 2T(n/2) + Θ(n)`  
- **Master Theorem (Case 2):** `T(n) = Θ(n log n)`  
- **Intuition:** Sorting by x and merging “strip” candidates costs linear time per level.

---

##  Experimental Plots & Observations

| Metric | Expected Trend | Measured Trend | Notes |
|---------|----------------|----------------|--------|
| **Time vs n (Merge/Quick)** | Θ(n log n) | Matches theory; QuickSort slightly faster for random data | Cache and branch prediction matter |
| **Time vs n (Select)** | Θ(n) | Linear growth verified | Overhead constant higher than `sort()` |
| **Depth vs n (Quick)** | O(log n) | Confirmed; ≤ 2 × ⌊log₂ n⌋ | Controlled recursion validated |
| **Closest Pair** | Θ(n log n) | Matches; quadratic baseline verified for n ≤ 2000 | Minor constant-factor effects from sorting |

**Constant-factor effects:**  
- JVM warm-up and GC overhead can distort timing for small n.  
- MergeSort benefits from sequential memory access (cache-friendly).  
- QuickSort’s partitioning introduces branch mispredictions.  
- Deterministic Select shows a higher constant factor due to multiple median calculations.

---

##  Summary

| Algorithm | Theoretical | Measured | Alignment |
|------------|--------------|-----------|------------|
| MergeSort | Θ(n log n) | n log n | ✅ Excellent |
| QuickSort | Θ(n log n) expected | n log n avg | ✅ Excellent |
| Select (MoM5) | Θ(n) | Linear | ✅ Good |
| Closest Pair | Θ(n log n) | n log n | ✅ Excellent |

> **Conclusion:** Experimental results align closely with theoretical asymptotics.  
> Minor mismatches arise from constant factors, JVM optimization, and memory locality.

---


