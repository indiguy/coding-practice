/**
 * 
 */
package com.pritam.coding.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Least Recently Used(LRU) cache that evicts the entry which is not used
 * recently when the cache size is full.
 * <p>
 * <code>
 * 	LRUCache<K, V> cache = new LRUCache(CAPACITY);<BR>
 *  cache.put(key, value);<BR>
 *  V val = cache.get(k); <BR>
 * </code>
 * </p>
 * 
 * @author Pritam Biswas
 *
 */
public class LRUCache<K, V> {

	/**
	 * Each instance of {@link Entry} holds a key-value pair
	 * 
	 * @author Pritam Biswas
	 *
	 * @param <K>
	 *            The key
	 * @param <V>
	 *            The value
	 */
	private static final class Entry<K, V> {
		private final K key;
		private final V value;
		private Entry<K, V> next;
		private Entry<K, V> previous;

		/**
		 * @param key
		 */
		Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * @return the key
		 */
		public K getKey() {
			return key;
		}

		/**
		 * @return the value
		 */
		public V getValue() {
			return value;
		}

		/**
		 * @return the next
		 */
		public Entry<K, V> getNext() {
			return next;
		}

		/**
		 * @param next
		 *            the next to set
		 */
		public void setNext(Entry<K, V> next) {
			this.next = next;
		}

		/**
		 * @return the previous
		 */
		public Entry<K, V> getPrevious() {
			return previous;
		}

		/**
		 * @param previous
		 *            the previous to set
		 */
		public void setPrevious(Entry<K, V> previous) {
			this.previous = previous;
		}

		@Override
		public String toString() {
			return new StringBuilder().append("key: " + key.toString()).append(", value: " + value.toString())
					.toString();
		}
	}

	/**
	 * A specially designed queue of {@link Entry} where a frequently used entry
	 * reinserted at the end of the queue. Thus head of the queue represent the
	 * least recently used entry. Frequently used entries are kept towards the tail.
	 * 
	 * @author Pritam Biswas
	 *
	 * @param <K>
	 * @param <V>
	 */
	private static final class EntryQueue<K, V> {
		private Entry<K, V> head;
		private Entry<K, V> tail;

		/**
		 * Add the given {@link Entry} at the end of the queue.
		 * 
		 * @param entry
		 */
		public boolean add(Entry<K, V> entry) {
			if (head == null) {
				head = entry;
				tail = head;
			} else if (tail != entry) {
				if (entry.getPrevious() != null) {
					entry.getPrevious().setNext(entry.getNext());
				}
				entry.setPrevious(tail);
				entry.setNext(null);
				tail.setNext(entry);
				tail = entry;
			}
			return true;
		}

		/**
		 * Remove the head of the queue and return the {@link Entry}
		 * 
		 * @return The head of the queue
		 */
		public Entry<K, V> remove() {
			if (head == null) {
				return null;
			}
			Entry<K, V> headElement = new Entry<>(head.getKey(), head.getValue());
			Entry<K, V> nextHead = head.getNext();
			head.setNext(null);
			nextHead.setPrevious(null);
			head = nextHead;
			return headElement;
		}

		/**
		 * Remove the given {@link Entry} from cache
		 * 
		 * @param entry
		 * @return true if removed, false otherwise
		 */
		public boolean remove(Entry<K, V> entry) {
			if (entry.getPrevious() != null) {
				entry.getPrevious().setNext(entry.getNext());
			} else {
				head = entry.getNext();
			}
			if (entry.getNext() != null) {
				entry.getNext().setPrevious(entry.getPrevious());
			} else {
				tail = entry.getPrevious();
			}
			entry.setNext(null);
			entry.setPrevious(null);

			return true;
		}

	}

	private final Map<K, Entry<K, V>> internalCache;
	private final EntryQueue<K, V> entryQueue;
	private final int capacity;

	/**
	 * Construct a new cache with given capacity
	 * 
	 * @param capacity
	 *            The capacity of cache
	 */
	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.internalCache = new HashMap<>(capacity);
		this.entryQueue = new EntryQueue<>();
	}

	/**
	 * Put the given value against the given key into the cache
	 * 
	 * @param key
	 * @param value
	 * @return The inserted value
	 */
	public synchronized V put(K key, V value) {
		if (capacity == internalCache.size()) {
			// cache is full, evict the least recently used entry
			Entry<K, V> removed = entryQueue.remove();
			internalCache.remove(removed.getKey());
		}
		Entry<K, V> newEntry = new Entry<>(key, value);
		entryQueue.add(newEntry);
		internalCache.put(key, newEntry);
		return newEntry.getValue();
	}

	/**
	 * Get the associated value against the given key, returns null if no
	 * association found
	 * 
	 * @param key
	 * @return The value associated with given key
	 */
	public V get(K key) {
		Entry<K, V> entry = internalCache.get(key);
		if (entry == null) {
			return null;
		}
		synchronized (this) {
			// update queue to mark it as frequently used
			entryQueue.add(entry);
		}
		return entry.getValue();
	}

	/**
	 * Remove the entry associated with the given key
	 * 
	 * @param key
	 * @return The removed value
	 */
	public synchronized V remove(K key) {
		Entry<K, V> toBeRemoved = internalCache.get(key);
		if (toBeRemoved == null) {
			return null;
		}
		if (entryQueue.remove(toBeRemoved)) {
			return internalCache.remove(key).getValue();
		}
		return null;
	}

}
