/* Example implementation of Steinhaus-Johnson-Trotter permutation generation algorithm,
	with Even's speedup.
*/

#include <iostream>
#include <vector>

std::vector<int> elements;
std::vector<int> direction;

bool next_permutation() {
    int highest_element = -1;
    int highest_index = -1;

    /* Find the highest mobile element. */
    for(int i = 0; i < elements.size(); i ++) {
        if(elements[i] > highest_element) {
            if(direction[i] == -1 && i == 0) continue;
            else if(direction[i] == 1 && i == elements.size()-1) continue;
            else if(elements[i + direction[i]] > elements[i]) continue;

            highest_element = elements[i];
            highest_index = i;
        }
    }
    /* If there are no mobile integers, then we are done. */
    if(highest_index == -1) return false;
    
    /* We have the highest mobile element now. Move it in the direction indicated by the direction. */
    int et = elements[highest_index];
    int ep = direction[highest_index];
    elements[highest_index] = elements[highest_index + ep];
    direction[highest_index] = direction[highest_index + ep];
    elements[highest_index + ep] = et;
    direction[highest_index + ep] = ep;

    /* Now swap the direction of all elements higher than the mobile element. */
    for(int i = 0; i < elements.size(); i ++) {
        if(elements[i] > highest_element) {
            direction[i] = -direction[i];
        }
    }

    return true;
}

int main(int argc, char *argv[]) {
	std::cout << "Number of integers to permute? " << std::endl;
	int n;
    std::cin >> n;
    for(int i = 0; i < n; i ++) {
        elements.push_back(i+1);
        direction.push_back(-1);
    }
    
    int p = 1;
    do {
        /* Print out each permutation as it is generated. */
        std::cout << "Permutation #" << p << ": ";
        for(int i = 0; i < n; i ++) {
            if(direction[i] == 1) std::cout << ">";
            else if(direction[i] == -1) std::cout << "<";
            std::cout << elements[i] << " ";
        }
        std::cout << std::endl;
        p ++;
    } while(next_permutation());

	return 0;
}
