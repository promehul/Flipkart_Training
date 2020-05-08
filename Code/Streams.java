package learnJava;

import java.util.stream.*;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class Duplicates {
    private static int prev;
    public static void playWithDuplicates() {
        Stream<Integer> values = Stream.of(1, 2, 2, 3, 3, 3, 4, 2);
        // filter is Higher Order Function
        // Operator
        values = values.filter( x -> { 
            boolean r = prev != x; 
            prev = x; 
            return r; });

        values.forEach(System.out::println);
    }
}


class CountLongWords {
    public static void doCounting() throws IOException {
        String contents = Files.readString(Paths.get("alice.txt"));
        List<String> words = List.of(contents.split("\\PL+"));

        long count = 0;
        for (String w : words) {
            if (w.length() > 12)
                count++;
        }
        System.out.println(count);

        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);

        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
}

public class Streams {
    public static void main(String[] args) {
        Duplicates.playWithDuplicates();
        CountLongWords.doCounting();
    }
}


