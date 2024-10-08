# Assignment: Package Challenge



## Problem Description



You want to send your friend a package with different items. You can choose from a number of `N` items. The items are numbered from 1 to `N`. Each one of these items has a given weight and a given cost (in €), where the weights and costs of the items might be different. The package itself has a weight limit. The combined weight of the items you put in the package must not exceed the weight limit of the package, otherwise the package would be too heavy.

Your goal is to determine which items to put in the package so that the total cost of the items you put inside is as large as possible. In case the total cost the of the packaged items is the same for two sets of items, you should prefer the combination of items which has a lower total weight.



## Constraints



1. The maximum weight that a package can hold must be <= 100.

2. There may be up to 15 items you can to choose from.

3. The maximum weight of an item should be <= 100.

4. The maximum cost of an item should be <= €100.



## Program Specification



Write a program, preferably in Java, which can be run on the command line in order to solve this problem. The program should take one command line argument, which contains the path to a text file. This text file should contain several lines, each line describing one test case for the problem.



Each line starts with the maximum weight of the package for this test case. It is followed by ` : ` and then the list of descriptions of the items available for packaging. Each item description contains, in parentheses, the item's number, starting at 1, its weight and its cost (preceded by a € sign).



In case of a constraint violation, your program should indicate this fact to the user, for example by throwing an exception with a descriptive message, allowing the user to address this problem.



### Sample Input



A sample input file looks like this:



```

81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)

8 : (1,15.3,€34)

75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)

56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)

```



### Sample Output



The solution for each test case should be printed out on a single line. On this line you should list the item numbers of the individual items to be put in the package to solve the problem. The numbers should be separated by commas. If no combination of items matches the requirements, the output should be a single `-`.



The sample output for the sample input file above should look like this:



```

4

-

2,7

8,9

```



## Concluding Remarks



Apply the best software design and development practices and document your 
approach, in particular regarding the algorithms and data structures you chose. Don't forget to put comments into your source files.



Good luck!

## Solution
This is a classic example of Knapsack algorithm.
The end to end approach is
1. Read input file
2. Validate input file to check
   1. File exists
   2. File is a plain text file
   3. File is not empty
3. Parse input file and convert to Package objects
4. Validate Package objects
   1. Validate package for weight
   2. Validate package for number of items
   3. Validate package for item weight
   4. Validate package for item cost
5. Process by application of knapsack algorithm
   1. Sort all items in a package in desc order of cost
   2. Store maximum allowed package weight as balance weight
   3. If item weight < balance weight, deduct item weight from balance weight 
      and add the index to result
   4. If the current item is not the last one in the sorted list of items 
      and it has the same cost as the next item in the sorted list then 
      consider the item with lesser weight
   5. Done when all items are processed



