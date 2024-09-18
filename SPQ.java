// Programming Assignment 3
// Smart Priority Queue implementation using a min or max heap
// This class implements a smart priority queue (SPQ) that can be toggled between a min-heap and a max-heap.
// It utilizes a inner class Entry to store key-value pairs.

/**
 * SPQ class
 * This class implements a smart priority queue (SPQ) that can be toggled between a min-heap and a max-heap.
 */
public class SPQ {

    // Attributes
    private Entry[] heap;
    private int size;
    private boolean isMinHeap;


    /**
     * Entry class
     */
    public static class Entry {

        // Attributes
        private int key;
        private int value;

        /**
         * Constructor for Entry
         * @param key
         * @param value
         */
        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        /**
         * getter method for key
         * @return key
         */
        public int getKey() {
            return key;
        }

        /**
         * getter method for value
         * @return value
         */
        public int getValue() {
            return value;
        }

        /**
         * setter method for key
         * @param key
         */
        public void setKey(int key) {
            this.key = key;
        }

        /**
         * setter method for value
         * @param value
         */
        public void setValue(int value) {
            this.value = value;
        }

        @Override
        /**
         * equals method for Entry
         * @param obj
         * @return boolean
         */
        public boolean equals(Object obj) {
            if (this == obj) { // if the object is compared with itself then
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) { // if the object is compared with null or different class
                return false;
            }
            Entry entry = (Entry) obj;
            return key == entry.key && value == entry.value;  // if the key and value are the same
        }

    }

    /**
     * Constructor for SPQ
     * Initializes the heap with a default size of 10
     * Initializes the size to 0
     */
    public SPQ() {
        heap = new Entry[10];
        size = 0;
        isMinHeap = true;
    }

    /**
     * method to toggle/switch between min and max heap
     * This method rebuilds the heap after toggling
     */
    public void toggle() {
        isMinHeap = !isMinHeap; // switch the boolean so that the buildHeap method will build the correct heap
        buildHeap(); // call the buildHeap method to rebuild the heap
    }

    /**
     * method to remove the top element from the heap
     * @return Entry
     * If the heap is empty, it returns null
     */
    public Entry removeTop() {
        if (isEmpty()) {
            return null; // if the heap is empty, return null
        }
        Entry top = heap[0]; // store the top element
        heap[0] = heap[size - 1]; // replace the top element with the last element
        size--; // decrement the size
        buildHeap(); // rebuild the heap
        return top; // return the top element
    }

    /**
     * method to insert a key-value pair into the heap
     * @param k
     * @param v
     * @return Entry
     * If the heap is full, it resizes the heap
     */
    public Entry insert(int k, int v) {
        if (size == heap.length) {
            resizeHeap(); // if the heap is full, resize the heap
        }
        Entry entry = new Entry(k, v); // create a new entry
        heap[size] = entry; // insert the entry at the end of the heap
        size++; // increment the size
        buildHeap(); // rebuild the heap
        return entry; // return the entry
    }

    /**
     * method to return the top element of the heap
     * @return Entry
     * If the heap is empty, it returns null
     */
    public Entry top() {
        return isEmpty() ? null : heap[0]; // if the heap is empty, return null, otherwise return the top element
    }

    /**
     * method to remove an element from the heap
     * @param e
     * @return Entry
     * If the element is not found, it returns null
     */
    public Entry remove(Entry e) {
        for (int i = 0; i < size; i++) { // here its a simple for loop that will iterate through the heap
            if (heap[i].equals(e)) { // if the element is found, remove it, rebuild the heap and return the removed element
                Entry removed = heap[i];
                heap[i] = heap[size - 1];
                size--;
                buildHeap();
                return removed;
            }
        }
        return null; // else return null
    }

    /**
     * method to replace the key of an element in the heap
     * @param e
     * @param k
     * @return int
     * If the element is not found, it returns -1
     */
    public int replaceKey(Entry e, int k) {
        for (int i = 0; i < size; i++) { // here its a simple for loop that will iterate through the heap
            if (heap[i].equals(e)) { // if the element is found, replace the key, rebuild the heap and return the old key
                int oldKey = heap[i].getKey();
                heap[i].setKey(k);
                buildHeap();
                return oldKey;
            }
        }
        return -1; // else return -1
    }

    /**
     * method to replace the value of an element in the heap
     * @param e
     * @param v
     * @return int
     * If the element is not found, it returns -1
     */
    public int replaceValue(Entry e, int v) { // this method is similar to the replaceKey method
        for (int i = 0; i < size; i++) { // here its a simple for loop that will iterate through the heap
            if (heap[i].equals(e)) { // if the element is found, replace the value, rebuild the heap and return the old value
                int oldValue = heap[i].getValue();
                heap[i].setValue(v);
                buildHeap();
                return oldValue;
            }
        }
        return -1; // else return -1
    }

    /**
     * method to return the state of the heap
     * @return String
     * If the heap is a min-heap, it returns "Min"
     */
    public String state() {
        return isMinHeap ? "Min" : "Max"; // if the heap is a min-heap, return "Min", otherwise return "Max"
    }

    /**
     * method to check if the heap is empty
     * @return boolean
     * If the size is 0, it returns true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * method to return the size of the heap
     * @return int size
     */
    public int size() {
        return size;
    }

    /**
     * method to resize the heap
     */
    public void resizeHeap() {
        Entry[] newHeap = new Entry[heap.length * 2]; // create a new heap with double the size
        System.arraycopy(heap, 0, newHeap, 0, heap.length); // copy the elements from the old heap to the new heap
        heap = newHeap; // assign the new heap to the old heap
    }

    /**
     * method to heapify the heap
     * @param i
     * This method is used to maintain the heap property
     */
    private void heapify(int i) { // this is a helper method that is used to maintain the heap property

        // these are the indices of the left and right children of the current node
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i;

        if (left < size && compare(heap[left], heap[smallest]) < 0) {
            smallest = left; // if the left child is smaller than the current node, set the smallest to the left child
        }

        if (right < size && compare(heap[right], heap[smallest]) < 0) {
            smallest = right; // if the right child is smaller than the current node, set the smallest to the right child
        }

        if (smallest != i) { // if the smallest is not the current node, swap the current node with the smallest child and call heapify recursively
            Entry temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            heapify(smallest);
        }
    }

    /**
     * method to build the heap
     */
    private void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i); // this is a simple for loop that will iterate through the heap and call the heapify method
            // it will use the helper method to maintain the heap property
        }
    }

    /**
     * method to compare two elements in the heap
     */
    private int compare(Entry a, Entry b) {
        return isMinHeap ? a.getKey() - b.getKey() : b.getKey() - a.getKey(); // this is a helper method that is used to compare two elements
    }
    


}
