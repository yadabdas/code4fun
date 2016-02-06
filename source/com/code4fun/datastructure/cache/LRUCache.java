package com.code4fun.datastructure.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    public static final int MAX_SIZE = 100; // Default max size for the cache to grow.
    private Entry<K, V> head;
    private HashMap<K, Entry> internalCache;
    private int maxSize;

    public LRUCache() {
        this.head = new Entry(null, null);
        head.before = head.after = head;
        internalCache = new HashMap<K, Entry>();
        maxSize = MAX_SIZE;
    }

    public LRUCache(int maxSize) {
        this.head = new Entry(null, null);
        head.before = head.after = head;
        internalCache = new HashMap<K, Entry>();
        this.maxSize = maxSize;
    }

    /**
     * Gets value associated with the specified key from cache.
     *
     * @param key
     * @return value or null if not found
     */
    public V get(Object key) {
        Entry<K, V> e = internalCache.get(key);
        if (e == null)
            return null;
        e.recordAccess(this);
        return e.value;
    }

    /**
     * Associates key and value in the cache, null value is not allowed.
     * If cache size has grown more than the allowed capacity the eldest member will be evicted.
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        if (value == null) {
            throw (new IllegalArgumentException("Null Value association is not helpful in caching"));
        }
        if (shouldRemoveEldestEntry()) {
            Entry eldest = head.after;
            removeEntry(eldest);
        }
        Entry entry = new Entry(key, value);
        if (!internalCache.containsKey(key)) {
            entry.addBefore(head);
        }
        internalCache.put(key, entry);
        return value;
    }

    private void removeEntry(Entry eldest) {
        // since we do not allow null value association so this is safe.
        if (internalCache.remove(eldest.key) != null) {
            eldest.recordRemoval(this);
        }
    }

    /*
    Returns true if this map should remove its eldest entry.
    if maxSize is not set during initialization the method will always return false.
     */
    private boolean shouldRemoveEldestEntry() {
        if (internalCache.size() > maxSize) {
            return true;
        }
        return false;
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        private Entry<K, V> before, after;
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        private void addBefore(Entry<K, V> existingEntry) {
            after = existingEntry;
            before = existingEntry.before;
            before.after = this;
            after.before = this;
        }

        void recordAccess(LRUCache<K, V> cache) {
            remove();
            addBefore(cache.head);
        }

        final void recordRemoval(LRUCache<K, V> m) {
            remove();
        }

        private void remove() {
            before.after = after;
            after.before = before;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry e = (Map.Entry) o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }

        @Override
        public String toString() {
            return "Entry key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LRUCache{" +
                "internalCache=" + internalCache +
                ", head=" + head +
                ", maxSize=" + maxSize +
                '}';
    }
}
