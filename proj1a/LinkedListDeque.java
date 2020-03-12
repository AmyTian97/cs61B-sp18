public class LinkedListDeque<T> {
    private class ListNode {
        public T item;
        public ListNode next;
        public ListNode prev;

        public ListNode(T i, ListNode n, ListNode p) {
            item = i;
            next = n;
            prev = p;
        }

        public T getRecHelper(int index) {
            if (index == 0) {
                return item;
            } else if (next==null){
                return null;
            } else {
                return this.next.getRecHelper(index - 1);
            }
        }
    }

    private ListNode sentinel;
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T x) {
        if (size == 0) {
            sentinel.next = new ListNode(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            ListNode p = sentinel.next;
            sentinel.next = new ListNode(x, sentinel.next, sentinel);
            p.prev = sentinel.next;
        }
        size = size + 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T x) {
        if (size == 0) {
            sentinel.next = new ListNode(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            ListNode p = sentinel.prev;
            sentinel.prev = new ListNode(x, sentinel, sentinel.prev);
            p.next = sentinel.prev;
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
        ListNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print("\n");
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            ListNode rm = sentinel.next;
            T x = rm.item;
            sentinel.next = rm.next;
            rm.next.prev = sentinel;
            size = size - 1;
            rm = null;
            return x;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            ListNode rm = sentinel.prev;
            T x = rm.item;
            sentinel.prev = rm.prev;
            rm.prev.next = sentinel;
            size = size - 1;
            rm = null;
            return x;
        }
    }

    /** Gets the item at the given index, where 0 is the front.
     * If no such item exists, returns null. */
    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            ListNode p = sentinel.next;
            int i = 0;
            while (i < index) {
                p = p.next;
                i = i + 1;
            }
            return p.item;
        }
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        return sentinel.next.getRecHelper(index);
    }

}
