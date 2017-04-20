from numpy import *
import operator

def createDataSet():
	group = array([[1.0, 1.1], 
				   [1.0, 1.0],
				   [0, 0],
				   [0, 0.1]])
	labels = ['a', 'a', 'b', 'b']
	return group, labels

def classify(intX, dataset, labels, k):
	dataSize = dataset.shape[0]
	print 'dataSize', dataSize
	diffMat = tile(intX, (dataSize, 1)) - dataset
	print 'tile(intX, (dataSize, 1)) - dataset', tile(intX, (dataSize, 1)), '-', dataset
	print 'diffMat', diffMat
	sqDiffMat = diffMat**2
	print 'sqDiffMat', sqDiffMat
	sqDistance = sqDiffMat.sum(axis = 1)
	print 'sqDistance', sqDistance
	distance = sqDistance**0.5
	print 'distance', distance
	sortedDisIndicies = distance.argsort()
	print 'sortedDisIndicies', sortedDisIndicies
	classCount = {}
	for i in range(k):
		voteLabel = labels[sortedDisIndicies[i]]
		print 'voteLabel', voteLabel
		classCount[voteLabel] = classCount.get(voteLabel, 0) + 1
	print 'classCount', classCount
	print 'classCount.items()', classCount.items()
	for it in classCount.iteritems():
		print it
	sortedClassCount = sorted(classCount.iteritems(), key=operator.itemgetter(1), reverse=True)
	print 'sortedClassCount', sortedClassCount
	return sortedClassCount[0][0]

dataset, labels = createDataSet()
intX = [1.1, 1.2]
print 'labels', labels
print 'dataset', dataset
print 'intX', intX

print classify(intX, dataset, labels, 4)

