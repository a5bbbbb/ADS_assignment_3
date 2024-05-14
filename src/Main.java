import datastructures.BST;
import datastructures.MyHashTable;

import java.util.List;
import java.util.Random;

import static java.lang.Math.max;

public class Main {

    public static void testMyHashTable(){
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>();

        long startTime = System.currentTimeMillis();

        int sampleSize = 10000;
        Random rng = new Random();

        for (int i = 0; i < sampleSize; i++){
            table.put(new MyTestingClass(String.valueOf(rng.nextInt())), i);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time put " + sampleSize + " : " + elapsedTime + " mils");

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

    public static void testBST(){
        BST<Integer, Integer> tree = new BST<>();
        Random rng = new Random();

        long startTime = System.currentTimeMillis();

        int sampleSize = 10000;

        for (int i = 0; i < sampleSize; i++){
            tree.put(rng.nextInt(), i);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        for (var elem : tree) {

            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());

        }

        System.out.println("Tree size: " + tree.size());

        System.out.println("Elapsed time put " + sampleSize + " : " + elapsedTime + " mils");
    }

    public static void main(String[] args){
        testMyHashTable();
        testBST();
    }
}
