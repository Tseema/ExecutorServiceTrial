package com.relearn.practice.Practice;

import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;



public class LongestStream {


    public static void main(String [] args){

        String[] arr = {"spring","Streamsjava","momDad","test"};


        findlongeststring(arr);

    }


    private static void findlongeststring(String[] arr) {

        Map<Integer, List<String>> collect = Arrays.stream(arr)
                .collect(Collectors.groupingBy(String::length));

        /*collect.entrySet().stream().
        .collect(Comparator.comparing(Map.Entry::comparingByKey));*/

         String result = Arrays.stream(arr)
                .reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2).get();


        System.out.println(result);

    }
}
