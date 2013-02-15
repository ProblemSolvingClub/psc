#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>

class Rational;
Rational abs(Rational const& r);
std::ostream& operator<< (std::ostream& out, Rational const& r);
std::istream& operator>> (std::istream& in, Rational& r);

template <typename N>
class FiniteField {
public:
    FiniteField(int val) {
        m_val = (val % N);
    }
    
private:
    int m_val;
};

class Rational {
public:
    Rational() {
        num = 0;
        denom = 1;
    }
    Rational(int val) {
        *this = val;
    }
    Rational(int num, int denom) {
        num = num;
        denom = denom;
    }
    Rational(Rational const& that) {
        *this = that;
    }
    Rational& operator= (int val) {
        num = val;
        denom = 1;
    }
    Rational& operator= (Rational const& that) {
        num = that.num;
        denom = that.denom;
    }
    Rational operator+ (Rational const& o) const {
        Rational c = *this;
        if (o.denom == 0) {
            return c;
        }
        int least = lcm(denom, o.denom);
        c.denom = least;
        int aCo = least / denom;
        int bCo = least / o.denom;
        c.num = aCo*num + bCo*o.num;
        int div = gcd(c.num, c.denom);
        c.num /= div;
        c.denom /= div;
        return c;
    }
    Rational& operator+= (Rational const& o) {
        *this = (*this + o);
        return *this;
    }
    Rational operator- (Rational const& o) const {
        Rational neg = o;
        neg.num *= -1;
        return (*this + neg);
    }
    Rational& operator-= (Rational const& o) {
        *this = (*this - o);
        return *this;
    }
    Rational operator* (Rational const& o) const {
        Rational c = *this;
        int numR = num*o.num;
        int denomR = denom*o.denom;
        int div = gcd(numR, denomR);
        c.num = numR / div;
        c.denom = denomR / div;
        div = gcd(c.num, c.denom);
        c.num /= div;
        c.denom /= div;
        return c;
    }
    Rational& operator*= (Rational const& o) {
        *this = (*this * o);
        return *this;
    }
    Rational operator/ (Rational const& o) const {
        Rational reciprocal (o.denom, o.num);
        return (*this * reciprocal);
    }
    Rational& operator/= (Rational const& o) {
        *this = (*this / o);
        return *this; 
    }
    bool operator< (Rational const& o) const {
        int least = lcm(denom, o.denom);
        int aCo = least / denom;
        int bCo = least / o.denom;
        return (aCo*num < bCo*o.num);
    }
    bool operator== (Rational const& o) const {
        int least = lcm(denom, o.denom);
        int aCo = least / denom;
        int bCo = least / o.denom;
        return (aCo*num == bCo*o.num); 
    }
    bool operator<= (Rational const& o) const {
        return (*this < o || *this == o);
    }
    bool operator!= (Rational const& o) const {
        return !(*this == o);
    }
    bool operator> (Rational const& o) const {
        return !(*this <= o);
    }
    bool operator>= (Rational const& o) const {
        return !(*this < o);
    }
    friend Rational abs(Rational const& r);
    friend std::ostream& operator<< (std::ostream& out, Rational const& r);
    friend std::istream& operator>> (std::istream& in, Rational& r);
private:
    // Finds least common multiple of a and b
    int lcm(long a, long b) const {
        return a/gcd(a, b)*b;
    }
    // Finds greatest common denominator of a and b
    int gcd(long a, long b) const {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
    long num;
    long denom;
};

Rational abs(Rational const& r) {
    Rational positive = r;
    positive.num = abs(positive.num);
    positive.denom = abs(positive.denom);
    return positive;
}

std::ostream& operator<< (std::ostream& out, Rational const& r) {
    out << r.num << "/" << r.denom;
    return out;
}

std::istream& operator>> (std::istream& in, Rational& r) {
    int val = 0;
    in >> val;
    r = Rational(val);
}

template <typename T>
std::ostream& operator<< (std::ostream& out, std::vector< std::vector<T> > const& m) {
    int rowCount = m.size();
    if (rowCount == 0) {
        out << "[ ]";
        return out;
    }
    int colCount = m.at(0).size();
    if (colCount == 0) {
        for (int j = 0; j < rowCount-1; ++j) {
            out << "[ ]" << std::endl;
        }
        out << "[ ]";
        return out;
    }
    out << "[";
    for (int j = 0; j < rowCount-1; ++j) {
        for (int i = 0; i < colCount-1; ++i) {
            out << m[j][i] << ", ";
        }
        out << m[j].back() << "]" << std::endl << "[";
    }
    for (int i = 0; i < colCount-1; ++i) {
        out << m.back()[i] << ", ";
    }
    out << m.back().back() << "]";
    return out;
}

template <typename T>
std::ostream& operator<< (std::ostream& out, std::vector<T> const& v) {
    int size = v.size();
    if (size == 0) {
        out << "[ ]";
        return out;
    }
    out << "[";
    for (int i = 0; i < size-1; ++i) {
        out << v.at(i) << ", ";
    }
    out << v.back() << "]";
    return out;
}


template <typename Field>
void LUPdecomp(std::vector< std::vector<Field> >& A, std::vector<int>& P) {
    int n = A.size();
    for (int k = 0; k < n; ++k) {
        Field pivot = 0;
        int kP = k;
        for (int i = k; i < n; ++i) {
            if (abs(A[i][k]) > pivot) {
                pivot = abs(A[i][k]);
                kP = i;
            }
        }
        if (pivot == 0) {
            std::cout << "ERROR: unsolvable" << std::endl;
            exit(-1);
        }
        double temp = P[k];
        P[k] = P[kP];
        P[kP] = temp;
        for (int i = 0; i < n; ++i) {
            Field temp = A[k][i];
            A[k][i] = A[kP][i];
            A[kP][i] = temp;
        }
        for (int i = k+1; i < n; ++i) {
            A[i][k] = (A[i][k] / A[k][k]);
            for (int j = k+1; j < n; ++j) {
                A[i][j] -= A[i][k]*A[k][j];
            }
        }
    }
}

template <typename Field>
std::vector<Field> const* LUPsolve(std::vector< std::vector<Field> > const& A,
                                    std::vector<int> const& P,
                                    std::vector<Field> const& b) {
    int n = A.size();
    std::vector<Field>* xRet = new std::vector<Field>(n, 0);
    std::vector<Field>& x = *xRet;
    std::vector<Field> y (n, 0);
    for (int i = 0; i < n; ++i) {
        Field sum = 0;
        for (int j = 0; j < i; ++j) {
            sum += A[i][j]*y[j];
        }
        y[i] = b[P[i]] - sum;
    }
    for (int i = n-1; i >= 0; --i) {
        Field sum = 0;
        for (int j = i+1; j < n; ++j) {
            sum += A[i][j]*x[j];
        }
        x[i] = (y[i] - sum) / A[i][i];
    }
    return xRet;
}

std::vector<Rational> GetRow(int x, int y, int n) {
    std::vector<Rational> row (n*n, 0);
    bool mask[3][3] =  {{0, 1, 0},
                        {1, 1, 1},
                        {0, 1, 0}};
    for (int i = -1; i < 2; ++i) {
        for (int j = -1; j < 2; ++j) {
            int xIdx = x+i;
            int yIdx = y+j;
            if (mask[i+1][j+1] && 0 <= xIdx && xIdx < n && 0 <= yIdx && yIdx < n) {
                row[yIdx*n+xIdx] = 1;
            }
        }
    }
    return row;
}

void LightsOut() {
    int n = 0;
    std::cin >> n;
    std::vector<Rational> b (n*n, 0);
    for (int i = 0; i < n*n; ++i) {
        std::cin >> b[i];
    }
    std::cout << b << std::endl << std::endl;
    std::vector< std::vector<Rational> > A (n*n, std::vector<Rational>(n*n, 0));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            A[j*n+i] = GetRow(i, j, n);
        }
    }
    std::cout << A << std::endl;
    std::vector<int> P;
    for (int i = 0; i < n*n; ++i) {
        P.push_back(i);
    }
    LUPdecomp(A, P);
    std::cout << A << std::endl << std::endl;
    std::cout << P << std::endl << std::endl;
    std::vector<Rational> const* x = LUPsolve(A, P, b);
    if (x != NULL) {
        std::cout << *x << std::endl;
        delete x;
        x = NULL;
    }
}

int main() {
    LightsOut();
    /*
    unsigned n = 0;
    std::cin >> n;
    matrix A (n, std::vector<double>(n, 0));
    for (unsigned r = 0; r < n; ++r) {
        for (unsigned c = 0; c < n; ++c) {
            std::cin >> A[r][c];
        }
    }
    std::vector<int> P;
    for (int i = 0; i < n; ++i) {
        P.push_back(i);
    }
    std::vector<double> b (n, 0);
    for (int i = 0; i < n; ++i) {
        std::cin >> b[i];
    }
    LUPdecomp(A, P);
    std::cout << A << std::endl;
    std::cout << std::endl;
    std::cout << P << std::endl;
    std::cout << std::endl;
    std::cout << b << std::endl;
    std::cout << std::endl;

    std::vector<double> const* x = LUPsolve(A, P, b);
    if (x != NULL) {
        std::cout << *x << std::endl;
        delete x;
        x = NULL;
    }
    */
    return 0;
};

