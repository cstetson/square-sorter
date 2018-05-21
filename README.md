# square-sorter
An Exploration into the BigO consequences of various sorting approaches to lists.

In order to test the speed of the QuickSort vs. partition/compare approaches, you need to have at least 1,000,000 entries in the list. To get this many, the app required a generator, which also required bounds. When the bounds are stretched, it often overflowed the integer values when squared, causing odd behavior in the application. Switching to longs fixed this problem and demonstrated that the partition/compare is quite a bit faster.

To run the application, compile it to a jar and run like this:

`java -cp square-sorter.jar com.metadogs.cstetson.SquareSorter <start int> <end int> <number int>`

- `start` int is the lower bound of the initial ArrayList value
- `end` int is the upper bound of the initial ArrayList value
- `number` int is the number of values to be generated in the initial ArrayList
