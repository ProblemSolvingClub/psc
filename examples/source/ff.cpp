/* Implementation of Ford-Fulkerson max-flow algorithm.
*/

#include <iostream>
#include <vector>
#include <algorithm>

int nodes;

int *flowMatrix;
int *capMatrix;

int rcapMatrix(int i) {
    return std::max(capMatrix[i] - flowMatrix[i], 0);
}

int indexFor(int u, int v) {
    return u + v*nodes;
}

int uFor(int i) {
    return i % nodes;
}

int vFor(int i) {
    return i / nodes;
}

std::vector<int> path;

int findPathHelper(int source, int sink, std::vector<int> &path) {
    if(source == sink) return -1;
    
    for(int i = 0; i < nodes; i ++) {
        if(rcapMatrix(indexFor(source, i)) > 0) {
            path.push_back(i);
            int c;
            if((c = findPathHelper(i, sink, path)) != 0) {
                if(c == -1) return rcapMatrix(indexFor(source, i));
                else return std::min(c, rcapMatrix(indexFor(source, i)));
            }
            path.pop_back();
        }
    }

    return 0;
}

int findPath(int source, int sink) {
    path.clear();
    path.push_back(source);
    return findPathHelper(source, sink, path);
}

int main(int argc, char *argv[]) {
    nodes = 4;

    flowMatrix = new int[nodes*nodes];
    capMatrix = new int[nodes*nodes];

    for(int i = 0; i < nodes*nodes; i ++) {
        flowMatrix[i] = capMatrix[i] = 0;
    }

    capMatrix[indexFor(1, 0)] = -(capMatrix[indexFor(0, 1)] = 5);
    capMatrix[indexFor(2, 0)] = -(capMatrix[indexFor(0, 2)] = 1);
    capMatrix[indexFor(2, 1)] = -(capMatrix[indexFor(1, 2)] = 3);
    capMatrix[indexFor(3, 1)] = -(capMatrix[indexFor(1, 3)] = 2);
    capMatrix[indexFor(3, 2)] = -(capMatrix[indexFor(2, 3)] = 3);
    
    int source = 0;
    int sink = 3;
    
    int inc = 0;

    while((inc = findPath(source, sink)) > 0) {
        std::cout << "Path found! inc: " << inc << std::endl << "\t";
        for(int i = 0; i < path.size(); i ++) {
            std::cout << path[i] << " ";
        }
        std::cout << std::endl;
        int cursor = 1;
        int u, v = path[0];
        while(cursor < path.size()) {
            u = v;
            v = path[cursor++];
            
            flowMatrix[indexFor(u, v)] += inc;
            flowMatrix[indexFor(v, u)] -= inc;
        }
    }

    int flow = 0;
    for(int i = 0; i < nodes; i ++) {
        flow += flowMatrix[indexFor(i, sink)];
    }
    std::cout << "Finished! Overall flow: " << flow << std::endl;
    for(int i = 0; i < nodes*nodes; i ++) {
        if(flowMatrix[i] <= 0) continue;
        std::cout << "\t" << uFor(i) << " ==> " << vFor(i) << ": " << flowMatrix[i] << std::endl;
    }

    return 0;
}
