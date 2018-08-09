package com.metadogs.cstetson;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SquareSorter {

    public static void main(String[] args)
    {
        SquareSorter sorta = new SquareSorter();
        ArrayList<Long> testValues;
        int start = -100000;
        int end = 100000;
        int number = 10000;
        if (args.length == 3)
        {
            start = new Integer(args[0]);
            end =  new Integer(args[1]);
            number = new Integer(args[2]);
            System.out.println("You are running Square-Sorter with the values of \n" +
                    "start = " + start + "\n" +
                    "end = " + end + "\n" +
                    "number = " + number + "\n");
        }
        else
        {
            System.out.println("You are running Square-Sorter with the default values of \n" +
                    "start = " + start + "\n" +
                    "end = " + end + "\n" +
                    "number = " + number + "\n" +
                    "You can pass in start, end and total number of squares to sort by adding 3 ints after the class \n" +
                    "\"java -cp square-sorter.jar com.metadogs.cstetson.SquareSorter <start int> <end int> <number int>\"\n");
        }

        testValues = sorta.generateList( start, end, number);

        long startTime = System.nanoTime();
        ArrayList<Long> simpleList = new ArrayList<>();
        for(int i = 0; i < 500; ++i) {
            simpleList = (ArrayList<Long>) sorta.simpleSquareSorter(testValues);
        }
        long endTime = System.nanoTime();
        long simpleDuration = (endTime - startTime);
        System.out.println("Simple Sort took: " + TimeUnit.NANOSECONDS.toMillis(simpleDuration) + " ms");

        long optStartTime = System.nanoTime();
        ArrayList<Long> optimizedList = new ArrayList<>();
        for(int i = 0; i < 500; ++i) {
            optimizedList = (ArrayList<Long>) sorta.optimizedSquareSorter(testValues);
        }
        long optendTime = System.nanoTime();
        long optimizedDuration = (optendTime - optStartTime);
        System.out.println("Optimized Sort took: " + TimeUnit.NANOSECONDS.toMillis(optimizedDuration) + " ms\n");
        if (simpleList.equals(optimizedList)) {System.out.println("Data Values are the same! "); }
        else {System.out.println("Data Values are different, something went wrong! "); }
    }

    private List<Long> optimizedSquareSorter(List<Long> list)
    {
        ArrayList<Long> negList = new ArrayList<>();
        ArrayList<Long> posList = new ArrayList<>();
        ArrayList<Long> returnList = new ArrayList<>();
        int i = 0;

        long optStartTime = System.nanoTime();
        for (Long number : list)
        {
            if (number < 0)
            {
                negList.add(number * number);
                i++;
            } else break;
        }
        long optendTime = System.nanoTime();
        long optimizedDuration = (optendTime - optStartTime);
        System.out.println("Optimized List creation took: " + TimeUnit.NANOSECONDS.toMillis(optimizedDuration) + " ms");

        Collections.reverse(negList);

        Long number;
        for (i = i ;  i < list.size(); i++)
        {
            number = list.get(i);
            posList.add(number * number);
        }


        ListIterator<Long> posIt = posList.listIterator();
        ListIterator<Long> negIt = negList.listIterator();
        long posInt = 0;
        long negInt = 0;
        if (negIt.hasNext()) { negInt = negIt.next(); }
        if (posIt.hasNext()) { posInt = posIt.next();}

        optStartTime = System.nanoTime();
        while (posIt.hasPrevious() && negIt.hasPrevious())
        {
            if (posInt > negInt)
            {
                returnList.add(negInt);
                if (negIt.hasNext()) { negInt = (long) negIt.next(); }
                else {
                    returnList.add(posInt);
                    break;
                }
            }
            else
            {
                returnList.add(posInt);
                if (posIt.hasNext()) { posInt = (long) posIt.next();}
                else {
                    returnList.add(negInt);
                    break;
                }
            }
        }
        optendTime = System.nanoTime();
        optimizedDuration = (optendTime - optStartTime);
        System.out.println("Optimized List merging took: " + TimeUnit.NANOSECONDS.toMillis(optimizedDuration) + " ms");

        if (posIt.hasNext())
        {
            returnList.addAll(posList.subList(posIt.nextIndex(), posList.size() ));
        }
        else if (negIt.hasNext())
        {
            returnList.addAll(negList.subList(negIt.nextIndex(), negList.size()));
        }

        return returnList;
    }

    private List<Long> simpleSquareSorter(List<Long> list)
    {
        //simple, clean, O(n log n)
        ArrayList<Long> returnList = new ArrayList<>();

        for(long number : list)
        {
            returnList.add(number * number);
        }

        Collections.sort(returnList);
        return returnList;

    }

    private ArrayList<Long> generateList(int start, int end, int number)
    {
        ArrayList<Long> longArray = new ArrayList<>();

        for( int i = 0; i < number; i++)
        {
            Random random = new Random();
            int bounds = end - start;
            int value = random.nextInt(bounds);
            longArray.add((long) (value + start));
        }
        Collections.sort(longArray);
        return longArray;
    }
}
