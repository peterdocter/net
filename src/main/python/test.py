from knn import *

# print createDataSet()
group, labels = dataset
print "org=", group

print "shape=", group.shape

print "tile= ", tile(group, (3, 2))

print "group**2", group**2
