# ADS_assignment_3
## HashTable
The hash table implementation uses chaining method for collision resolution, which grants average time complexity of 1, with the worst case being O(N).
### Methods
#### public MyHashTable()
Constructor with default number of buckets.

#### public MyHashTable(int M)
Constructor with custom number of buckets.

#### void put(K key, V value)
Puts the key-value pair in the hash table.

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
The binary search tree is a tree, in which every vertex has a key and a value.<br>
Every vertex follows a rule that left subtree must contain keys that are less than the key,<br>
and that right subtree must contain keys that are greater than the key.

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


