public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /** Resizes the array */
    private void resize(int capacity) {
        T[] ad = (T[]) new Object[capacity];
        if (nextFirst < nextLast) {
            System.arraycopy(items, 0, ad, 0, size - nextFirst);
            System.arraycopy(items, nextLast + 1, ad, capacity - nextFirst, size - nextLast);
            items = ad;
            nextLast = capacity - size + nextLast;
        } else {
            System.arraycopy(items, 0, ad, 0, size);
            items = ad;
            nextFirst = capacity - 1;
            nextLast = size;
        }
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size = size + 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size = size + 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        int n = 0;
        int i = (nextFirst + 1) % items.length;
        while (n != size) {
            System.out.print(items[i] + " ");
            i = (i + 1) % items.length;
            n += 1;
        }
        System.out.print("\n");
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T x = items[(nextFirst + 1) % items.length];
            items[(nextFirst + 1) % items.length] = null;
            nextFirst = (nextFirst + 1) % items.length;
            size = size - 1;
            if (items.length >= 16 && size < 0.25 * items.length) {
                resize(size * 2);
            }
            return x;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T x = items[(nextLast + items.length - 1) % items.length];
            items[(nextLast + items.length - 1) % items.length] = null;
            nextLast = (nextLast + items.length - 1) % items.length;
            size = size - 1;
            if (items.length >= 16 && size < 0.25 * items.length) {
                resize(size * 2);
            }
            return x;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (nextFirst < nextLast) {
            if (index < nextFirst || index > nextLast) {
                return items[index];
            } else {
                return null;
            }
        }
        else if (nextFirst > nextLast) {
            if (index > nextFirst || index < nextLast) {
                return items[index];
            } else {
                return null;
            }
        } else {
            if (index != nextFirst) {
                return items[index];
            } else {
                return null;
            }
        }
    }




}
