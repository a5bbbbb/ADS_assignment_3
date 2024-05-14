package datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BST <K extends Comparable<K>, V> implements Iterable<BST<K, V>.KeyValuePair> {
    private Node root;
    private int size = 0;

    private class Node{
        private final K key;
        private V value;
        private Node left, right;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    public class KeyValuePair{
        private final K key;
        private final V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public class MyIterator implements Iterator<KeyValuePair> {
        private final List<KeyValuePair> arr;
        private int cur = 0;

        public MyIterator(List<KeyValuePair> arr) {
            this.arr = arr;
        }

        @Override
        public boolean hasNext() {
            return cur < arr.size();
        }

        public KeyValuePair next(){
            if(hasNext())
                return arr.get(cur++);
            throw new IndexOutOfBoundsException();
        }
    }

    /*
     * Helper method. Puts a node in the tree.
     * If the current node's key is greater than key of key-value pair,
     * then recursively go to the left subtree, if equal, then change value,
     * otherwise, recursively go to right subtree,
     * finally if the subtree the recursion is supposed to go to is empty,
     * then place key-value pair there.
     *
     * Time complexity: O(N) - where N is size of the tree.
     *
     * @param node the current node.
     * @param key the key.
     * @param value the value.
     */
    private void put(Node node, K key, V value){
        if(node == null){
            root = new Node(key, value);
            return;
        }
        if(node.key.compareTo(key) > 0){
            if(node.left == null)
                node.left = new Node(key, value);
            else
                put(node.left, key, value);
        }
        else if(node.key.compareTo(key) < 0){
            if(node.right == null)
                node.right = new Node(key, value);
            else
                put(node.right, key, value);
        }
        else{
            node.value = value;
        }
    }

    /*
     * Puts a node in the tree.
     * If the current node's key is greater than key of key-value pair,
     * then recursively go to the left subtree, if equal, then change value,
     * otherwise, recursively go to right subtree,
     * finally if the subtree the recursion is supposed to go to is empty,
     * then place key-value pair there.
     *
     * Time complexity: O(N) - where N is size of the tree.

     * @param key the key.
     * @param value the value.
     */
    public void put(K key, V value){
        put(root, key, value);
        size++;
    }

    /*
     * Helper method. Finds value with key=key in the tree.
     * If the current node is null, then throw NoSuchElementException.
     * If the current node's key is greater than the key,
     * then recursively go to the left subtree.
     * If the current node's key is less than the key,
     * then recursively go to the right subtree.
     * if the current node's key is equal to the key,
     * return value of the current node.
     *
     * Time complexity: 0(N) where N is the size of the tree.
     *
     * @param node the current node.
     * @param key the key of the value.
     * @return the value of the key.
     * @throw NoSuchElementException in case the key was not found in the tree.
     */
    private V findValue(Node node, K key){
        if(node == null)
            throw new NoSuchElementException("No value with key = " + key + "was found.");
        if(node.key == key)
            return node.value;
        else if(node.key.compareTo(key) > 0)
            return findValue(node.left, key);
        else
            return findValue(node.right, key);
    }

    /*
     * Finds value with key=key in the tree.
     * Calls helper method private V findValue(Node node, K key).
     *
     * Time complexity: 0(N) where N is the size of the tree.
     *
     * @param key the key of the value.
     * @return the value of the key.
     * @throw NoSuchElementException in case the key was not found in the tree.
     */
    public V get(K key){
        return findValue(root, key);
    }

    /*
     * Finds node with no left subtree.
     *
     * Time complexity: 0(N) where N is the size of the tree.
     *
     * @param the current node.
     * @return node with no left child.
     */
    private Node findNodeWithNoLeftChild(Node node){
        return (node.left == null ? node : findNodeWithNoLeftChild(node.left));
    }

    /*
     * Helper method. Deletes the node with the key=key.
     * If the current node is null, then throw NoSuchElementException.
     * If the current node's key is equal to the key.
     * Case 1: none or one child.
     * Substitute the current node with its only child.
     * Or substitute with null if no child.
     * Case 2: 2 children.
     * Find first empty left child in the right subtree.
     * Substitute first empty left child in the right subtree
     * with the current node's left subtree.
     * Substitute the current node with its right child.
     *
     * Time complexity: 0(N) where N is the size of the tree.
     *
     * @param the current node.
     * @param the parent node.
     * @param the key.
     * @return post order reference to child of the parent node.
     */
    private Node deleteNode(Node node, Node parent, K key){
        if(node == null)
            throw new NoSuchElementException("No value with key = " + key + "was found.");
        if(node.key == key) {
            if(node.left == null){
                return node.right;
            }
            else if(node.right == null){
                return node.left;
            }
            else{
                Node newParent = findNodeWithNoLeftChild(node.right);
                newParent.left = node.left;
                return node.right;
            }
        }
        else if(node.key.compareTo(key) > 0)
            node.left = deleteNode(node.left, node, key);
        else
            node.right = deleteNode(node.right, node, key);
        return node;
    }

    /*
     * Helper method. Deletes the node with the key=key.
     * If the current node is null, then throw NoSuchElementException.
     * If the current node's key is equal to the key.
     * Case 1: none or one child.
     * Substitute the current node with its only child.
     * Or substitute with null if no child.
     * Case 2: 2 children.
     * Find first empty left child in the right subtree.
     * Substitute first empty left child in the right subtree
     * with the current node's left subtree.
     * Substitute the current node with its right child.
     *
     * Time complexity: 0(N) where N is the size of the tree.
     *
     * @param the key.
     */
    public void delete(K key){
        deleteNode(root, null, key);
        size--;
    }
    /*
     * Helper method. Gets key-value pairs using in order traversal.
     *
     * Time complexity: 0(N) where N is the size of the tree.
     *
     * @param node the current node.
     * @param pairs the list of key-value paris.
     */
    private void getKeyValuePairsInOrderTraversal(Node node, List<KeyValuePair> pairs){
        if(node == null)
            return;
        getKeyValuePairsInOrderTraversal(node.left, pairs);
        pairs.add(new KeyValuePair(node.key, node.value));
        getKeyValuePairsInOrderTraversal(node.right, pairs);
    }

    /*
     * Helper method. Gets key-value pairs traversing the tree.
     *
     * Time complexity: 0(N) where N is the size of the tree.
     *
     * @param node the current node.
     * @param pairs the list of key-value paris.
     */
    private List<KeyValuePair> getKeyValuePairs(){
        ArrayList<KeyValuePair> pairs = new ArrayList<>();
        getKeyValuePairsInOrderTraversal(root, pairs);
        return pairs;
    }

    /*
     * Gets key-value pairs.
     *
     * Time complexity: 0(N) where N is the size of the tree.
     *
     * @param node the current node.
     * @param pairs the list of key-value paris.
     */
    @Override
    public Iterator<BST<K, V>.KeyValuePair> iterator() {
        return new MyIterator(getKeyValuePairs());
    }

    public int size() {
        return size;
    }
}
