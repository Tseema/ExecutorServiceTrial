package com.relearn.practice.Practice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Numbers {

    public static void main(String [] args){
        int [] numbers ={1,2,3,4,5,6,7 ,11};

        //find second highest nbr
        findSecondHighest(numbers);

        //find nbrs that start with 1
        nbrsWith1(numbers);

        //skip limit ex
        IntStream.rangeClosed(1,10)
                .skip(2)
                .limit(7)
                .forEach(System.out::println);
    }

    private static void nbrsWith1(int[] numbers) {

        List<String> collect = Arrays.stream(numbers).boxed()
                .map(i -> i + "")
                .filter(i -> i.startsWith("1"))
                .collect(Collectors.toList());

        System.out.println(collect);
    }

    private static void findSecondHighest(int[] numbers) {

        Optional<Integer> i = Arrays.stream(numbers).boxed()
                .sorted(Comparator.reverseOrder())
                .toList()
                .stream().skip(1)
                .findFirst();

        System.out.println(i);


    }
}
