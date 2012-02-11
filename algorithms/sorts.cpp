#include <iostream>
#include <vector>

bool IsOrdered(std::vector<int> &list);
void Display(std::vector<int> &list);
void Swap(std::vector<int> &list, int i, int j);
void Quicksort(std::vector<int> &list, int p, int r);
int Partition(std::vector<int> &list, int p, int r);
void CountingSort(std::vector<int> &list, int k);
void RadixSort(std::vector<int> &list, int bits);

int main() 
{
    // Reads data
    int elemCount = 0;
    std::cin >> elemCount;
    std::vector<int> list;
    for (unsigned i = 0; i < elemCount; ++i) {
        int elem = 0;
        std::cin >> elem;
        list.push_back(elem);
    } 

    // Sorts
    //Quicksort(list, 0, elemCount-1);
    //CountingSort(list, 16);
    RadixSort(list, 32);

    // Prints success/failure
    if (IsOrdered(list))
        std::cout << "Success!" << std::endl;
    else 
        std::cout << "Failure." << std::endl;

    return 0;
}

bool IsOrdered(std::vector<int> &list)
{
    bool isOrdered = true;
    for (unsigned i = 0; i < list.size()-1; ++i) {
        if (list[i] > list[i+1]) {
            isOrdered = false;
        }
    }
    return isOrdered;
}

void Display(std::vector<int> &list)
{
    for (unsigned i = 0; i < list.size(); ++i) {
        std::cout << list[i] << " ";
    }
    std::cout << std::endl;
}

void Swap(std::vector<int> &list, int i, int j)
{
    int temp = list[i];
    list[i] = list[j];
    list[j] = temp;
}

void Quicksort(std::vector<int> &list, int p, int r)
{
    if (p < r) {
        int q = Partition(list, p, r);
        Quicksort(list, p, q-1);
        Quicksort(list, q+1, r);
    } 
}

int Partition(std::vector<int> &list, int p, int r)
{
    int x = list[r];    
    int i = p-1;
    for (unsigned j = p; j < r; ++j) {
        if (list[j] <= x) {
            i++;
            Swap(list, i, j);
        }
    }
    Swap(list, i+1, r);
    return (i+1);
}

void CountingSort(std::vector<int> &list, int k)
{
    std::vector<int> sorted = list;
    std::vector<int> scratch;
    for (int i = 0; i < k; ++i) {
        scratch.push_back(0);
    }
    for (int i = 0; i < list.size(); ++i) {
        scratch[list[i]]++;
    }
    for (int i = 1; i < k; ++i) {
        scratch[i] += scratch[i-1]; 
    }
    for (int i = list.size()-1; i >= 0; --i) {
        sorted[scratch[list[i]]-1] = list[i];
        scratch[list[i]]--;
    }
    list = sorted;
}

void RadixSort(std::vector<int> &list, int bits)
{
    std::vector<int> scratch = list;
    for (int bit = 1; bit < (1 << 30); bit = (bit << 1)) {
        int zeroes = 0;
        int ones = 0;
        for (int i = 0; i < list.size(); ++i) {
            if (list[i] & bit) {
                ones++;
            }
            else {
                zeroes++;
            }
        }
        ones += zeroes;
        for (int i = list.size()-1; i >= 0; --i) {
            if (list[i] & bit) {
                scratch[ones-1] = list[i];
                ones--;
            }
            else {
                scratch[zeroes-1] = list[i];
                zeroes--;
            }
        }
        list = scratch;
    }    
}

