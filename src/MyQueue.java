public class MyQueue<T> {

    private static final int DEFAULT_SIZE = 10;
    private Object[] array;
    private int size;

    public MyQueue() {
        array = new Object[DEFAULT_SIZE];
        size = 0;
    }

    public MyQueue(int size){
        this.array = new Object[size];
        this.size = 0;
    }

    public void add(T value) {
        if (size + 1 >= array.length) {
            Object[] newArray = new Object[array.length * 2];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[size++] = value;
    }

    public void clear() {
        array = new Object[10];
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object peek() {
        if (size > 0) {
            return array[0];
        }
        return null;
    }

    public Object poll() {
        if (size > 0) {
            Object ret = array[0];
            for (int i = 0; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
            return ret;
        }
        return null;
    }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();

        myQueue.add(1);
        myQueue.add(2);
        myQueue.add(3);
        myQueue.add(4);

        //Test add &size
        System.out.println(myQueue.size());

        //  Test peek
        System.out.println(myQueue.peek());


        // Test clear
        myQueue.clear();
        System.out.println(myQueue.size());

        // Test poll
        myQueue.add(1);
        myQueue.add(2);
        System.out.println(myQueue.poll());
    }
}

