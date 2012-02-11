#include <iostream>
#include <cstdio>
#include <vector>
#include <limits.h>
#include <time.h>
#include <stdlib.h>
#include <algorithm>

#define OPTIMIZED 1
#define BASECASE 10

class RetVal
{
public:
    int val;
    int low;
    int high;
    RetVal(int v, int l, int h) {
        val = v;
        low = l;
        high = h;
    }
};

RetVal BruteForce(std::vector<int> &elements, int low, int high)
{
    RetVal best(INT_MIN, 0, 0);
    int sum = 0;
    for (int start = low; start <= high; ++start) {
        for (int end = start; end <= high; ++end) {
            sum += elements[end];    
            if (sum > best.val) {
                best = RetVal(sum, start, end);
            }
        }
        sum = 0;
    }
    return best;
}

RetVal FindMaxCrossingSubarray(std::vector<int> &elements,
                                int low, int mid, int high)
{
    int sum = 0;
    int leftSum = INT_MIN;
    int maxLeft = 0;
    for (int idx = mid; idx >= low; --idx) {
        sum += elements[idx];
        if (sum > leftSum) {
            leftSum = sum;
            maxLeft = idx; 
        }
    }
    sum = 0;
    int rightSum = INT_MIN;
    int maxRight = 0;
    for (int idx = mid+1; idx <= high; ++idx) {
        sum += elements[idx];
        if (sum > rightSum) {
            rightSum = sum;
            maxRight = idx; 
        }
    }
    return RetVal(leftSum+rightSum, maxLeft, maxRight);
}

RetVal FindMaximumSubarray(std::vector<int> &elements, int low, int high)
{
#if OPTIMIZED
    if (high - low < BASECASE) {
        return BruteForce(elements, low, high);
    }
#else
    if (high == low) {
        return RetVal(elements[low], low, high);
    } 
#endif
    else {
        int mid = ((low + high) >> 1);
        RetVal lower = FindMaximumSubarray(elements, low, mid);
        RetVal higher = FindMaximumSubarray(elements, mid+1, high);
        RetVal middle = FindMaxCrossingSubarray(elements, low, mid, high);
        if (lower.val >= higher.val && lower.val >= middle.val) {
            return lower;
        }
        else if (higher.val >= lower.val && higher.val >= middle.val) {
            return higher;
        }
        else {
            return middle;
        }
    }
}

int Linear(std::vector<int> &elements) {
    int maxSoFar = 0;
    int maxEndingHere = 0;
    for (int idx = 0; idx < elements.size(); idx++) {
        maxEndingHere = std::max(0, maxEndingHere+elements[idx]);
        maxSoFar = std::max(maxSoFar, maxEndingHere);
    }
    return maxSoFar;
}

int main(int argc, char** argv)
{
    int elemCount = 0;
    std::vector<int> elements;
    if (argc == 2) {
        elemCount = atoi(argv[1]);
        printf("Generating... ");
        for (int i = 0; i < elemCount; ++i) {
            int element = rand();
            if (element % 2 == 0) {
                element *= -1;
            }
            elements.push_back(element % 100);
        }
    }
    else {
        std::cin >> elemCount;
        printf("Reading... ");
        for (int i = 0; i < elemCount; ++i) {
            int element = 0;
            std::cin >> element;
            elements.push_back(element);
        }
    }
    printf("done.\n");
    printf("For set of size %d:\n", elemCount);
    clock_t start, finish;
    float execTime = 0.0f;
    start = clock();
    int val = Linear(elements);
    finish = clock();
    execTime = ((float)(finish - start) / (float)CLOCKS_PER_SEC);
    printf("    Linear - executed in %.2f seconds\n", execTime);
    printf("    [x:x] sums to %d\n", val);
    start = clock();
    RetVal max = FindMaximumSubarray(elements, 0, elemCount-1);
    finish = clock();
    execTime = ((float)(finish - start) / (float)CLOCKS_PER_SEC);
    printf("    Divide & conquer - executed in %.2f seconds\n", execTime);
    printf("    [%d:%d] sums to %d\n", max.low, max.high, max.val);
    /*
    start = clock();
    max = BruteForce(elements, 0, elemCount-1);
    finish = clock();
    execTime = ((float)(finish - start) / (float)CLOCKS_PER_SEC);
    printf("    Brute force - executed in %.2f seconds\n", execTime);
    printf("    [%d:%d] sums to %d\n", max.low, max.high, max.val);
    */
    return 0;
}

