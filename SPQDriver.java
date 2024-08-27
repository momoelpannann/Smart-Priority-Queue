/*
 * Driver class for the SPQ class. Demonstrates the functionality of the SPQ class by inserting 20 entries into the SPQ,
 */
public class SPQDriver {
    public static void main(String[] args) {
        // Create a new SPQ instance
        SPQ spq = new SPQ();

        // Insert 20 entries into the SPQ
        for (int i = 0; i < 20; i++) {
            SPQ.Entry entry = spq.insert(i, i * 10);
            System.out.println("Inserted entry with key " + entry.getKey() + " and value " + entry.getValue());
        }

        // Display the current state and size of the SPQ
        System.out.println("SPQ is currently a " + spq.state() + " heap with size " + spq.size());

        // Toggle the SPQ
        spq.toggle();
        System.out.println("Toggled SPQ. It is now a " + spq.state() + " heap");

        // Remove the top entry and display it
        SPQ.Entry top = spq.removeTop();
        System.out.println("Removed top entry with key " + top.getKey() + " and value " + top.getValue());

        // Display the new top entry without removing it
        top = spq.top();
        System.out.println("New top entry has key " + top.getKey() + " and value " + top.getValue());

        // Replace the key of the top entry
        int oldKey = spq.replaceKey(top, 99);
        System.out.println("Replaced key of top entry. Old key was " + oldKey + ", new key is " + top.getKey());

        // Replace the value of the top entry
        int oldValue = spq.replaceValue(top, 999);
        System.out.println("Replaced value of top entry. Old value was " + oldValue + ", new value is " + top.getValue());

        // Remove a specific entry
        SPQ.Entry removed = spq.remove(top);
        System.out.println("Removed entry with key " + removed.getKey() + " and value " + removed.getValue());

        // Display the current state and size of the SPQ
        System.out.println("SPQ is currently a " + spq.state() + " heap with size " + spq.size());

        // Toggle the SPQ back to its original state
        spq.toggle();
        System.out.println("Toggled SPQ. It is now a " + spq.state() + " heap");

        // Empty the SPQ
        while (!spq.isEmpty()) {
            top = spq.removeTop();
            System.out.println("Removed entry with key " + top.getKey() + " and value " + top.getValue());
        }

        // Display the current state and size of the SPQ
        System.out.println("SPQ is currently a " + spq.state() + " heap with size " + spq.size());
    }
}