//Stephen Mc Glynn
//Advanced Algorithms and Data Structures
//Hash Table using Divide and Conquer

import java.util.Arrays;

public class HashTableDivideAndConquer {
    private String[] table;
    private int capacity;
    private int size;

    // Constructor to initialize the hash table with a given capacity
    public HashTableDivideAndConquer(int capacity) {
        this.capacity = capacity;
        this.table = new String[capacity];
        this.size = 0;
    }

    // Simple hash function to map a string key to an index
    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    // Insert a key into the hash table
    public void insert(String key) {
        if ((double) size / capacity >= 0.75) {
            resize();
        }

        int index = hash(key);

        while (table[index] != null) {
            if (table[index].equals(key)) {
                return; // avoid duplicates
            }
            index = (index + 1) % capacity;
        }

        table[index] = key;
        size++;
    }

    // Search for a key in the hash table
    public boolean search(String key) {
        int index = hash(key);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].equals(key)) {
                return true;
            }
            index = (index + 1) % capacity;

            if (index == startIndex) {
                break;
            }
        }
        return false;
    }

    // Delete a key from the hash table
    public void delete(String key) {
        int index = hash(key);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].equals(key)) {
                table[index] = null;
                size--;
                rehash();
                return;
            }
            index = (index + 1) % capacity;

            if (index == startIndex) {
                return;
            }
        }
    }

    // Divide and Conquer Resize: Rehash the table by dividing the task into smaller
    // chunks
    private void resize() {
        String[] oldTable = table;
        capacity = capacity * 2;
        table = new String[capacity];
        size = 0;

        for (String key : oldTable) {
            if (key != null) {
                insert(key);
            }
        }
    }

    // Rehash remaining elements to fill gaps after a deletion (Divide and Conquer
    // Approach)
    private void rehash() {
        String[] oldTable = table;
        table = new String[capacity];
        int oldSize = size;
        size = 0;

        for (String key : oldTable) {
            if (key != null) {
                insert(key);
            }
        }
        size = oldSize;
    }

    // Print the hash table for debugging
    public void printTable() {
        System.out.println(Arrays.toString(table));
    }

    public static void main(String[] args) {
        System.out.println("starts");
        HashTableDivideAndConquer unihashTable = new HashTableDivideAndConquer(20);
        // Insert keys
        unihashTable.insert("ATU Letterkenny");
        unihashTable.insert("ATU Killybegs");
        unihashTable.insert("ATU Sligo");
        unihashTable.insert("ATU Galway Mayo");
        unihashTable.insert("ATU Killybegs"); // add 15 more sample university campuses
        // Print the hash table
        unihashTable.printTable();
        // Search for a key
        System.out.println("Is 'ATU Sligo' in the table? " + unihashTable.search("ATU Sligo")); // true
        System.out.println("Is 'ATU Dundalk' in the table? " + unihashTable.search("ATU Dundalk"));
        // false
        // Delete a key
        unihashTable.delete("ATU Galway Mayo");
        unihashTable.printTable();
    }
}
