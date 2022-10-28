# Empirical analysis

## Measurements

> Use Stopwatch to measure the total running time of PercolationStats for various values of n and T.
> How does doubling n affect the total running time? How does doubling T affect the total running
> time? Give a formula (using tilde notation) of the total running time on your computer
> (in seconds) as a single function of both n and T.

https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php

    Multiple runs of PercolationStats with WeightedQuickUnionUF as the backing implementation

    // 500, 30 -> 0.5, 0.539, 0.542
    // 500, 60 -> 1.05, 0.961, 1.035

    // 1000, 30 -> 2.659, 2.299, 2.307
    // 1000, 60 -> 4.826, 4.239, 4.051
    
    // 2000, 30 -> 17.019, 16.32, 16.45
    // 2000, 60 -> 31.967, 29.763, 31.082

    // 4000, 30 -> 104.689, 102.899, 100.749
    // 4000, 60 -> 216.208, 217.044

| N    | trials | time |
|------|--------|------|
| 250  | 30     | 0,1  |
| 500  | 30     | 0,5  |
| 1000 | 30     | 2,3  |
| 2000 | 30     | 16,6 |
| 4000 | 30     | 102  |
| 8000 | 30     | 506  |

## Standard plot

## Log-log plot

## Estimate

time = a * N^cube

a = time / N^cube






