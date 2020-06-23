package ca.jrvs.apps.practice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamImp  {

    /**
     * Create a String stream from array
     *
     * note: arbitrary number of value will be stored in an array
     *
     * @param strings
     * @return
     */
    public Stream<String> createStrStream(String ... strings){
        ArrayList<String> strArray = new ArrayList<String>();
        for(String i : strings) {
            strArray.add(i);
        }
        Stream<String> strStream = strArray.stream();
        return strStream;
    }

    /**
     * Convert all strings to uppercase
     * please use createStrStream
     *
     * @param strings
     * @return
     */
    public Stream<String> toUpperCase(String ... strings) {
        Stream<String> initialStream = createStrStream(strings);
        Stream newOne = initialStream.map(String::toUpperCase);
        return newOne;

    }

    /**
     * filter strings that contains the pattern
     * e.g.
     * filter(stringStream, "a") will return another stream which no element contains a
     *
     *
     * @param stringStream
     * @param pattern
     * @return
     */
    Stream<String> filter(Stream<String> stringStream, String pattern) {
        Stream<String> newStream = stringStream.filter(element -> element.matches(pattern));
        return newStream;
    }

    /**
     * Create a intStream from a arr[]
     * @param arr
     * @return
     */
    IntStream createIntStream(int[] arr) {
        IntStream newStream = Arrays.stream(arr);
        return newStream;
    }
    /**
     * Convert a stream to list
     *
     * @param stream
     * @param <E>
     * @return
     */
    <E> List<E> toList(Stream<E> stream) {
        List<E> finalOne = stream.collect(Collectors.toList());
        return finalOne;
    }

    /**
     * Create a IntStream range from start to end inclusive
     * @param start
     * @param end
     * @return
     */
    IntStream createIntStream(int start, int end) {
        IntStream answerOne = IntStream.range(start,end+1);
        return answerOne;
    }

    /**
     * filter all even number and return odd numbers from a intStream
     * @param intStream
     * @return
     */
    IntStream getOdd(IntStream intStream) {
        IntStream answerOne = intStream.filter(number -> number % 2 !=0);
        return answerOne;
    }

    /**
     * Return a lambda function that print a message with a prefix and suffix
     * This lambda can be useful to format logs
     *
     * You will learn:
     *   - functional interface http://bit.ly/2pTXRwM & http://bit.ly/33onFig
     *   - lambda syntax
     *
     * e.g.
     * LambdaStreamExc lse = new LambdaStreamImp();
     * Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
     * printer.accept("Message body");
     *
     * sout:
     * start>Message body<end
     *
     * @param prefix prefix str
     * @param suffix suffix str
     * @return
     */
    Consumer<String> getLambdaPrinter(String prefix, String suffix){
        Consumer<String> newOne = (s) -> System.out.println(prefix+  s + suffix);
        return newOne;
    }

    /**
     * Print each message with a given printer
     * Please use `getLambdaPrinter` method
     *
     * e.g.
     * String[] messages = {"a","b", "c"};
     * lse.printMessages(messages, lse.getLambdaPrinter("msg:", "!") );
     *
     * sout:
     * msg:a!
     * msg:b!
     * msg:c!
     *
     * @param messages
     * @param printer
     */
    void printMessages(String[] messages, Consumer<String> printer) {
        Stream<String> newOne = Arrays.asList(messages).stream();
        newOne.forEach(printer);
    }

    /**
     * Print all odd number from a intStream.
     * Please use `createIntStream` and `getLambdaPrinter` methods
     *
     * e.g.
     * lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
     *
     * sout:
     * odd number:1!
     * odd number:3!
     * odd number:5!
     *
     * @param intStream
     * @param printer
     */
    void printOdd(IntStream intStream, Consumer<String> printer) {
        Stream<String> finalOne = intStream.filter(n -> n % 2 !=0 ).mapToObj(Integer::toString);
        finalOne.forEach(printer);
    }
    /**
     * Square each number from the input.
     * Please write two solutions and compare difference
     *   - using flatMap
     *
     * @param ints
     * @return
     */
    Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
       Stream<Integer> finalOne =  ints.flatMap((child) -> child.stream()).map(child -> child*child);
       return finalOne;
    }


    public static void main(String[] args) {
        LambdaStreamImp a = new LambdaStreamImp();
        a.printOdd(a.createIntStream(1,5),a.getLambdaPrinter("oddnumber:", "!"));
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        a.flatNestedInt(inputStream).forEach(System.out::println);
//        Stream newOne = a.toUpperCase("a","b","c");
//        a.filter(newOne,"\\w").forEach(System.out::println);
//        int[] b = new int[2];
//        b[0] = 1;
//        b[1] = 2;
//        IntStream newTwo = a.createIntStream(b);
//        newTwo.forEach(System.out::println);
        String[] messages = {"a","b", "c"};
        a.printMessages(messages,a.getLambdaPrinter("msg:","!"));




    }
}
