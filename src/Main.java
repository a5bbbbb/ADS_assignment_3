import datastructures.BST;
import datastructures.MyHashTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;

public class Main {

    private static final Random rnd = new Random();
    private static final int sampleSize = 10000;

    private static final MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>();
    private static final BST<Integer, Integer> tree = new BST<>();

    private static String getRandomString(int length) {
        String dict = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder();
        while (builder.length() < length) { // length of the random string.
            int index = rnd.nextInt(0,dict.length()-1);
            builder.append(dict.charAt(index));
        }
        return builder.toString();
    }

    private static void insertHashTable(){
        for (int i = 0; i < sampleSize; i++){
            table.put(new MyTestingClass(getRandomString(20)), i);
        }
    }

    private static void printBucketsAndStatistics(){
        List<Integer> bucketSizes = table.getBucketSizes();
        double usedBuckets = 0, sum = 0, max = 0, aboveAvg = 0;
        for(int i = 0; i < bucketSizes.size(); i++){
            if(bucketSizes.get(i) != 0){
                usedBuckets++;
                sum+=bucketSizes.get(i);
                max = max(max, bucketSizes.get(i));
            }
            System.out.println("Bucket " + i + ": " + bucketSizes.get(i));
        }
        for(int i = 0; i < bucketSizes.size(); i++){
            aboveAvg += (bucketSizes.get(i) > (sum/usedBuckets)?1:0);
        }
        System.out.println("Used buckets: " + usedBuckets + " / " + table.getM());
        System.out.println("Avg per a non-empty bucket: " + (sum/usedBuckets));
        System.out.println("Number of above avg sized buckets: " + aboveAvg);
        System.out.println("Max in a bucket: " + max);
        System.out.println("Unique keys: " + table.size());
    }

    private static void testMyHashTable(){
        long startTime = System.currentTimeMillis();
        insertHashTable();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        printBucketsAndStatistics();
        System.out.println("Elapsed time put " + sampleSize + " : " + elapsedTime + " mils");
    }

    private static void testBST(){
        ArrayList<Integer> arr = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++){
            arr.add(rnd.nextInt());
            tree.put(arr.getLast(), i);
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
        System.out.println("Tree size: " + tree.size());
        System.out.println("Elapsed time put 10 : " + elapsedTime + " mils");
    }

    public static void main(String[] args){
        testMyHashTable();
        System.out.println("\n------------------------------------------------\n");
        testBST();
    }
}
