
# Enumerate all subset bitmasks
def subsets(xs, n):
    if len(xs) == n:
        print xs
    else:
        subsets(xs+[1], n)
        subsets(xs+[0], n)

# Enumerate all subset bitmasks of a certain size
def subsets_size(xs, l, n, k):
    if len(xs) == n and l == k:
        print xs
    else:
        if l < k:
            subsets_size(xs+[1], l+1, n, k)
        if n-len(xs) > k-l:
            subsets_size(xs+[0], l, n, k)

# Enumerate all permutations of a set
def permutations(xs, b, zs):
    if len(xs) == len(zs):
        print xs
    else:
        for i, z in enumerate(zs):
            if not b[i]:
                new_b = b[:]
                new_b[i] = True
                permutations(xs+[z], new_b, zs)

# Enumerate solutions to the N Queens puzzle
def queens(xs, n):
    if len(xs) == n:
        print xs
    else:
        this_col = len(xs)
        for this_row in range(n):
            if this_row not in xs:
                valid_diag = True
                for col, row in enumerate(xs):
                    if (this_col-col) == abs(this_row-row):
                        valid_diag = False
                        break
                if valid_diag:
                    queens(xs+[this_row], n)



print 'All subset masks of size 4:'
subsets([], 4)
print 'All subset masks of size 4 with 2 elements:'
subsets_size([], 0, 4, 2)
print 'All permutations of the list [3, 2, 1]:'
permutations([], [False, False, False], [3, 2, 1])
print 'Distinct solutions to the 5 Queens puzzle:'
queens([], 5)

