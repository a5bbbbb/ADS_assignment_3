# ADS_assignment_3
## Execution
<ol>
<li>
10000 random strings are inserted into the hash table.<br>
</li>
<li>
Sizes of the buckets of the hash table printed.<br>
</li>
<li>
Used buckets, average size per a non-empty bucket,<br>
number of above average sized buckets, maximum in a bucket,<br>
number of unique keys, elapsed time, are displayed.<br>
</li>
<li>
10 random number keys with indexes are inserted into the BST.
</li>
<li>
A list of key-value pairs displayed through for-each loop, the list is composed by in-order traversal of the tree.
</li>
<li>
Final tree size and elapsed time are printed.
</li>
</ol>


## Main
The main method of the Main class calls testMyHashTable() and testBST().

### testMyHashTable
<ul>
<li>MyTestingClass calculates custom hashes for strings put in the hash table.<br></li>
<li>The method inserts 10000 random strings into the hash table.<br></li>
<li>The size of every bucket printed in the console.<br></li>
<li>The time elapsed is measured and displayed in the console.<br></li>
</ul>

### testBST
<ul><li>The size field and its getter are implemented. The tree size is displayed in the console. <br></li>
<li> A list of key-value pairs of the tree composed using in-order traversal for iterator method is implemented. <br></li>
<li> Both the key and the value are accessible during the iteration using KeyValuePair inner class. <br></li>
</ul>


## HashTable
The hash table implementation uses separate chaining method for collision resolution, which grants amortized time complexity of 1, with the worst case being O(N).
Sedgewick, R., & Wayne, K. (2011). Algorithms. Addison-wesley professional.
### Methods
#### public MyHashTable()
Constructor with default number of buckets.

#### public MyHashTable(int M)
Constructor with custom number of buckets.

#### void put(K key, V value)
Puts the key-value pair in the hash table.<br>
If the load of the table exceeds the load factor(0.7), 
then the table is resized to a prime number at least twice larger than the current size.

#### V get(K key)
Finds the value by the key.

#### V remove(K key)
Removes the key-value pair from the table.

#### boolean contains(K key)
Checks if the value with the key is in the table.

#### K getKey(V value)
Finds the key with the value.

#### ArrayList\<Integer\> getBucketSizes()
Returns sizes of buckets of the table.

## BinarySearchTree
The binary search tree is a binary tree where every vertex has a key and a value.<br>
Every vertex satisfies the restriction that the left subtree must contain keys that are less than the key,
and that the right subtree must contain keys that are greater than the key.<br>
Sedgewick, R., & Wayne, K. (2011). Algorithms. Addison-wesley professional.

### Methods
#### void put(K key, V value)
Puts a node with the key and the value in the tree.

#### V get(K key)
Returns the value corresponding to the key provided.

#### void delete(K key)
Deletes the key-value pair.

#### Iterator\<BST\<K, V\>.KeyValuePair\> iterator()
Allows iteration over a list of key-value pairs of the tree composed using in-order traversal.

#### int size()
Returns the size of the tree.

## KeyValuePair
A class for accessing key-value pair.

#### KeyValuePair(K key, V value)
Represents key-value pair.

#### getKey()
Returns the key of the pair.

#### getValue()
Returns the value of the pair.
