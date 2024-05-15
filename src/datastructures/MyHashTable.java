package datastructures;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class MyHashTable <K, V>{

    private class HashNode<K, V>{
        private final K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        /*
         * Finds the number of nodes until the end of the bucket.
         *
         * Time complexity: O(N), where N is the number of nodes in the bucket.
         *
         * @return the number of nodes until the end of the bucket.
         */
        private int chainLength(){
            int length = 0;
            HashNode<K, V> node = this;
            while(node != null){
                length++;
                node = node.next;
            }
            return length;
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }
    private HashNode<K,V>[] chainArray;
    private int M = 11;
    private int size = 0;

    private void initializeChainArray(){
        chainArray = (HashNode<K,V>[]) new HashNode[M];
    }

    /*
     * Constructor with default number of buckets.
     */
    public MyHashTable(){
        initializeChainArray();
    }

    /*
     * Constructor with custom number of buckets.
     */
    public MyHashTable(int M){
        this.M = M;
        initializeChainArray();
    }

    /*
    * Checks if the object passed is null.
    *
    * Time complexity: O(1).
    *
    * @param o the object to be checked.
    * @throws NullPointerException if the object passed is null.
     */
    private void checkNull(Object o){
        if(o == null)
            throw new NullPointerException();
    }

    /*
    * Returns index of the bucket by hash of the key.
    * The bucket is determined by key.hashCode() % M.
    * Where M is number of buckets.
    *
    * Time complexity is that of the key's hashCode method.
    *
    * @param key the key to an object in HashTable.
    * @return index of the bucket of the key.
     */
    private int hash(K key){
        checkNull(key);
        return (key.hashCode()%M + M)%M;
    }
    /*
     * Helper method. Returns the closest larger prime number to provided one.
     * Uses sequential search through numbers larger than the argument.
     * Test each with divisors from 2 to less or equal to sqrt(tested number).
     *
     * Time complexity: (N * sqrt(A)), where N is an amount of numbers tested,
     * and A is the largest number tested.
     *
     * @param cur the number before the one from which the search starts.
     * @return the closest larger prime to the provided number.
     */
    private int getClosestLargerPrime(int cur){
        for(int i = cur+1;;i++){
            boolean good = true;
            for(int j = 2; j*j <= i; j++){
                if(i%j == 0){
                    good = false;
                    break;
                }
            }
            if(good)
                return i;
        }
    }

    /*
    * Doubles number of buckets when the load of the table exceeds the load factor.
    *
    * Time complexity: O(N), where N is the number of pairs inserted.
     */
    private void resize(){
        M = getClosestLargerPrime(M*2);
        HashNode<K,V>[] oldChainArray = chainArray;
        initializeChainArray();
        for(var node : oldChainArray){
            if(node == null)continue;
            while(node != null){
                // Put every key-value pair from old chainArray to new one.
                put(node.key, node.value);
                // The size increased using put method, but actual size stays the same.
                size--;
                node = node.next;
            }
        }
    }

    /*
    * Puts the key, value pair in the HashTable.
    * Finds the bucket of the key through hash() method.
    * If some node already has identical key,
    * then changes value of the node to value argument.
    * Otherwise, puts node with the pair in the front of the chain of the bucket.
    * Resizes the table if the load exceeds the load factor.
    *
    * Time complexity: O(N), where N is the number of pairs inserted; Θ(1).
    *
    * @param key the key to the value.
    * @param value the value for the key.
     */
    public void put(K key, V value){
        checkNull(key);
        HashNode<K, V> existingNode;
        try{
            // If node with key=key already exists, then change value.
            existingNode = getNodeByKey(key);
            existingNode.value = value;
        }catch (NoSuchElementException e){
            // If node with key=key does not exist, then put new node to the head of the chain.
            int index = hash(key);
            HashNode<K, V> newNode = new HashNode<>(key, value);
            newNode.next = chainArray[index];
            chainArray[index] = newNode;
            size++;
            // Resize the table if the load exceeds the load factor.
            if((double)size/M > 0.7)
                resize();
        }
    }

    /*
     * Helper method. Finds the node by the key.
     * Finds the bucket of the key through hash() method.
     * If the bucket is empty or cannot find node with key=key, then return null.
     * Otherwise, return node with key=key.
     *
     * Time complexity: O(N), where N is the number of pairs inserted; Θ(1);
     *
     * @param key the key of the node.
     * @return the node with key=key;
     * @throws NoSuchElementException if no node with key=key was found.
     */
    private HashNode<K, V> getNodeByKey(K key){
        checkNull(key);
        HashNode<K, V> node = chainArray[hash(key)];
        while(node != null && !node.key.equals(key))
            node = node.next;
        if(node == null) {
            throw new NoSuchElementException("No value with key = " + key + " was found.");
        }
        return node;
    }

    /*
     * Finds the value by the key.
     * Finds the bucket of the key through hash() method.
     * If the bucket is empty or cannot find node with key=key, then throw NoSuchElementException.
     * Otherwise, return the node with key=key.
     *
     * Time complexity: O(N), where N is the number of pairs inserted; Θ(1);
     *
     * @param key the key of the value.
     * @return the node with key=key;
     * @throws NoSuchElementException if value with key=key was found.
     */
    public V get(K key){
        checkNull(key);
        HashNode<K, V> node = getNodeByKey(key);
        return node.value;
    }

    /*
     * Helper method. Finds the previous node of the node with key=key.
     * Finds the bucket of the key through hash() method.
     * If the bucket is empty or cannot find node with key=key, then throw NoSuchElementException.
     * Otherwise, return the previous node of the node with key=key.
     *
     * Time complexity: O(N), where N is the number of pairs inserted; Θ(1);
     *
     * @param key the key of the node.
     * @return the previous node of the node with key=key;
     * @throws NoSuchElementException if no node with key=key was found.
     */
    private HashNode<K, V> getPrevNodeByKey(K key){
        checkNull(key);
        HashNode<K, V> node = chainArray[hash(key)], prev = null;
        while(node != null && !node.key.equals(key)) {
            prev = node;
            node = node.next;
        }
        if(node == null)
            throw new NoSuchElementException("No value with key = " + key + " was found.");
        return prev;
    }

    /*
     * Removes the key-value pair from the table.
     * Finds the bucket of the key through hash() method.
     * If the bucket is empty or cannot find node with key=key, then throw NoSuchElementException.
     * Otherwise, delete the node with key=key, and return the value of the node.
     *
     * Time complexity: O(N), where N is the number of pairs inserted; Θ(1);
     *
     * @param key the key of the value.
     * @return the value of the removed node.
     * @throws NoSuchElementException if value with key=key was found.
     */
    public V remove(K key) {
        checkNull(key);
        int index = hash(key);
        HashNode<K, V> prevNode = getPrevNodeByKey(key);
        V value;
        // If the previous node is null, then the node to be removed is the head of the bucket.
        if (prevNode == null) {
            value = chainArray[index].value;
            chainArray[index] = null;
        }
        // Otherwise, the next node of the previous node is the next of the node to be removed.
        else {
            value = prevNode.next.value;
            prevNode.next = prevNode.next.next;
        }
        size--;
        return value;
    }

    /*
     * Checks if the value with key=key is in the table.
     * Finds the bucket of the key through hash() method.
     * If the bucket is empty or cannot find node with key=key, then throw NoSuchElementException.
     * Otherwise, delete the node with key=key, and return the value of the node.
     *
     * Time complexity: O(N), where N is the number of pairs inserted; Θ(1);
     *
     * @param key the key of the value.
     * @return true if the value with key=key is in the table, false otherwise.
     * @throws NoSuchElementException if value with key=key was found.
     */
    public boolean contains(K key){
        try {
            getNodeByKey(key);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    /*
     * Finds the key with value=value.
     * Brute force through the table.
     *
     * Time complexity: O(N+M), where N is the number of pairs inserted,
     * and M is the number of buckets.
     *
     * @param value the value of the node.
     * @return the key of the value;
     * @throws NoSuchElementException if no key with value=value was found.
     */
    public K getKey(V value){
        for(var node : chainArray){
            while(node != null){
                if(node.value.equals(value))return node.key;
                node = node.next;
            }
        }
        throw new NoSuchElementException("No key with value = " + value + " was found.");
    }

    /*
     * Returns sizes of buckets of the table.
     *
     * Time complexity: O(N+M), where N is the number of pairs inserted,
     * and M is the number of buckets.
     *
     * @return sizes of buckets.
     */
    public ArrayList<Integer> getBucketSizes(){
        ArrayList<Integer> bucketSizes = new ArrayList<>();
        for(var node : chainArray) {
            if (node == null) {
                bucketSizes.add(0);
            } else {
                bucketSizes.add(node.chainLength());
            }
        }
        return bucketSizes;
    }

    public int size(){
        return size;
    }

    public int getM() {
        return M;
    }
}
