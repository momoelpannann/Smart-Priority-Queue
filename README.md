Smart Priority Queue (SPQ) - Java Implementation

Description

This Java project implements a Smart Priority Queue (SPQ) that can be toggled between a min-heap and a max-heap.
The SPQ manages key-value pairs stored as entries and supports various operations such as inserting elements, removing the top element, and toggling between heap states. 
The internal structure of the queue adapts based on the current heap type, ensuring that elements are ordered correctly whether the queue operates as a min-heap or a max-heap.

Features

	•	Dynamic Heap Management: Switch between min-heap and max-heap configurations seamlessly.
	•	Flexible Entry Management: Supports insertion, removal, and replacement of keys and values within the queue.
	•	Efficient Heap Operations: Includes standard heap operations like heapify and buildHeap to maintain heap properties.
	•	Scalable Storage: Automatically resizes the underlying array to accommodate more entries as needed.

Usage

The SPQ class can be used in applications requiring dynamic priority management, where the nature of the heap (min or max) can change based on context or user preference.
This flexibility makes it suitable for algorithms and data structures that rely on priority queues with varying criteria.
