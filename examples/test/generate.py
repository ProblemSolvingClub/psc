import random
import sys

filename = sys.argv[1]
elemCount = int(sys.argv[2])
with open(filename, 'w') as f:
    f.write('%d\n' %elemCount)
    for i in range(0, elemCount):
        f.write('%d ' %random.randrange(-100, 100))
    f.write('\n-1\n')
