package com.relearn.practice.Practice;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStrings {

    public static void main(String [] args){
        //count occurrence  of each char in  a string

        String str = "ilovejavatechie";
        
        countChar(str);

        findDuplicates(str);

        findUniqueElem(str);

        firstnonRepeatElement(str);

        //add delimiter(-)
        List<String> arr = Arrays.asList("1","2","3","4");
        joinStrings(arr);
    }

    private static void joinStrings(List<String> arr) {
        String join = String.join("-", arr);
        System.out.println(join);
    }

    private static void firstnonRepeatElement(String str) {

        String [] arr = str.split("");

        /*
        internally grouping by uses hash maps and we know hash maps do not preserve order
        hence we use hashlinked maps
         */

        String s = Arrays.stream(arr)
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .get();

        System.out.println("first unique element :" + s);
    }

    private static void findUniqueElem(String str) {

        String[] arr = str.split("");

        List<String> collect = Arrays.stream(arr)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static void findDuplicates(String str) {

        String [] arr = str.split("");

        Map<String, Long> collect = Arrays.stream(arr)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

      /*  collect.forEach((k,v)-> {
            if(v >= 2)
                System.out.println("key " + k + " " +"value " + v);
        });*/

        List<String> collect1 = collect.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println((collect));
        System.out.println(collect1);
    }

    private static void countChar(String str) {
        String [] arr = str.split("");
       // System.out.println(Arrays.toString(arr));
        //char [] arr = str.toCharArray();


        //concurrent maps are thread safe
        ConcurrentMap<String, Long> collect = Arrays.stream(arr)
                .collect(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting()));

        //hash maps are not thread safe but are better performance than concurrent maps
        Map<String, Long> collect1 = Arrays.stream(arr)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        /*
         For parallel stream pipelines, the combiner function operates by merging the
         keys from one map into another, which can be an expensive operation. If preservation
         of the order in which elements are presented to the downstream collector
         is not required, using groupingByConcurrent(Function, Collector) may offer better parallel performance.
         */

        System.out.println(collect);
        System.out.println(collect1);
    }
}
