#include <iostream>
#include <vector>
#include <stdlib.h>

void Display(std::vector<int> &set, int p, int r);
void Swap(std::vector<int> &list, int i, int j);
int Select(std::vector<int> &set, int p, int r, int i);
int Partition(std::vector<int> &set, int p, int r);
int SelectPivotRandomly(std::vector<int> &set, int p, int r);
int SelectPivotMedian(std::vector<int> &set, int p, int r);
int InsertionSort(std::vector<int> &set, int p, int r);

int main()
{
    // Reads data
    int elemCount = 0;
    std::cin >> elemCount;
    std::vector<int> set;
    for (unsigned i = 0; i < elemCount; ++i) {
        int elem = 0;
        std::cin >> elem;
        set.push_back(elem);
    } 
    Display(set, 0, elemCount-1);

    int k = 4;
    int orderStat = Select(set, 0, elemCount-1, k);
    InsertionSort(set, 0, elemCount-1);
    if (orderStat == set[k-1]) {
        std::cout << "Success!" << std::endl;
    }
    else {
        std::cout << "Failure." << std::endl;
    }

    std::cout << orderStat << std::endl;
    return 0;
}

void Display(std::vector<int> &set, int p, int r)
{
    for (unsigned i = p; i <= r; ++i) {
        std::cout << set[i] << " ";
    }
    std::cout << std::endl;
}

void Swap(std::vector<int> &list, int i, int j)
{
    if (i != j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}

int Select(std::vector<int> &set, int p, int r, int i)
{
    if (p == r) {
        return set[p];
    }
    int q = Partition(set, p, r);
    int k = q-p+1;
    if (i == k) {
        return set[q];
    }
    else if (i < k) {
        return Select(set, p, q-1, i);
    }
    else {
        return Select(set, q+1, r, i-k);
    }
}

int Partition(std::vector<int> &set, int p, int r)
{
    int pivot = SelectPivotRandomly(set, p, r);
    Swap(set, pivot, r);
    int x = set[r];
    int i = p-1;
    for (unsigned j = p; j < r; ++j) {
        if (set[j] <= x) {
            i++;
            Swap(set, i, j);
        }
    }
    Swap(set, i+1, r);
    return i+1;
}

int SelectPivotRandomly(std::vector<int> &set, int p, int r)
{
    return p + (rand() % (p-r));
}

int SelectPivotMedian(std::vector<int> &set, int p, int r)
{
    if (r-p+1 <= 5) {
        InsertionSort(set, p, r);
        return p+((r-p+1)/2);
    }
    std::vector<int> medians;
    for (unsigned i = 0; i < r-p+1; i+=5) {
        int median = SelectPivotMedian(set, p+i, p+i+4);
        medians.push_back(set[i+2]);
    }
    return SelectPivotMedian(medians, 0, medians.size()-1);
}

int InsertionSort(std::vector<int> &set, int p, int r)
{
    for (unsigned i = p+1; i <= r; ++i) {
        int key = set[i];
        int j = i-1;
        while (j >= 0 && set[j] > key) {
            set[j+1] = set[j];
            j--;
        }
        set[j+1] = key;
    }
}

