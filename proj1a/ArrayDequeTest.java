public class ArrayDequeTest {
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        boolean passed = checkEmpty(true, ad1.isEmpty());

        ad1.addFirst(1);
        passed = checkSize(1, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.addLast(2);
        passed = checkSize(2, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.addFirst(3);
        passed = checkSize(3, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        System.out.println("Printing out deque: ");
        ad1.printDeque();

        ad1.removeFirst();
        passed = checkSize(2, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        ad1.removeLast();
        passed = checkSize(1, ad1.size()) && passed;
        passed = checkEmpty(false, ad1.isEmpty()) && passed;

        System.out.println("Printing out deque: ");
        ad1.printDeque();

        ad1.removeLast();
        passed = checkSize(0, ad1.size()) && passed;
        passed = checkEmpty(true, ad1.isEmpty()) && passed;

        System.out.println("Printing out deque: ");
        ad1.printDeque();

        System.out.println();
        printTestStatus(passed);
    }
}
