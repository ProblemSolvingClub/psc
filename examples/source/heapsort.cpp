#include <vector>
#include <iostream>
#include <algorithm>

int Left(int i);
int Right(int i);
int Parent(int i);
void MaxHeapify(std::vector<int> &vals, int i, int heapSize);
void BuildMaxHeap(std::vector<int> &vals);
void HeapSort(std::vector<int> &vals);

int Left(int i)
{
    return (i << 1);
}

int Right(int i)
{
    return ((i << 1) | 1);
}

int Parent(int i)
{
    return (i >> 1);
}

void MaxHeapify(std::vector<int> &vals, int i, int heapSize)
{
    int l = Left(i);
    int r = Right(i);
    int largest = i;
    if (l < heapSize && vals[l] > vals[i]) {
        largest = l;
    }
    else {
        largest = i;
    }
    if (r < heapSize && vals[r] > vals[largest]) {
        largest = r;
    }
    if (largest != i) {
        vals[i] ^= vals[largest];
        vals[largest] ^= vals[i];
        vals[i] ^= vals[largest];
        MaxHeapify(vals, largest, heapSize);
    }
}

void BuildMaxHeap(std::vector<int> &vals)
{
    for (int i = (vals.size() >> 1); i >= 0; --i) {
        MaxHeapify(vals, i, vals.size());
    }
}

void HeapSort(std::vector<int> &vals)
{
    int heapSize = vals.size();
    BuildMaxHeap(vals);
    for (int i = heapSize-1; i >= 1; --i) {
        vals[0] ^= vals[i];
        vals[i] ^= vals[0];
        vals[0] ^= vals[i]; 
        --heapSize;
        MaxHeapify(vals, 0, heapSize);
    }
}

int main()
{
    std::vector<int> vals;
    vals.push_back(27);
    vals.push_back(17);
    vals.push_back(3);
    vals.push_back(16);
    vals.push_back(13);
    vals.push_back(10);
    vals.push_back(1);
    vals.push_back(5);
    vals.push_back(7);
    vals.push_back(12);
    vals.push_back(4);
    vals.push_back(8);
    vals.push_back(9);
    vals.push_back(0);
    HeapSort(vals);
    for (int i = 0; i < vals.size(); ++i) {
        std::cout << vals[i] << " ";
    }
    std::cout << std::endl;
    return 0;
}
