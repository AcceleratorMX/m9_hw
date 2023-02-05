import java.util.Arrays;
import java.util.StringJoiner;

public class MyStack<E> {
    private static int DEFAULT_SIZE = 10;
    private int size;
    private Object[] array;


    public MyStack() {
        this.array = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    public MyStack(int size) {
        this.size = 0;
        array = new Object[size];
    }

    public void push(E value){
        if(size == array.length){
            int newSize = (array.length * 3)/2 + 1;
            Object[] tempData;
            tempData = Arrays.copyOf(array, newSize);
            array = tempData;
        }
        array[size]=value;
        size = size + 1;
    }

    public void remove(int index){
        int newSize = size - 1;
        if (newSize > index){
            System.arraycopy(array, index + 1, array, index, newSize - index);
        } else {
            array[index] = null;
        }
        array[newSize] = null;

        size = newSize;
    }
    private Object get(int index){
        return array[index];
    }

    public void clear(){
        for (int i =0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object peek() {
        for (Object o : array) {
            return o;
        }
        return null;
    }

    public Object pop() {
        Object el = null;
        for (Object o : array) {
            el = o;
            break;
        }
        remove(0);
        return el;
    }


    @Override
    public String toString() {
        StringJoiner res = new StringJoiner(",");

        for (Object element:array) {
            if(element!=null) {
                res.add(element.toString());
            }
        }

        return res+"";
    }

    // Test
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        stack.push(11);

        //  Test push & size
        System.out.println(stack.size());

        //  Test remove
        stack.remove(1);
        System.out.println("stack = " + stack);
        System.out.println("stack = " + stack.size());

        //  Test get
        System.out.println("stack.get = " + stack.get(1));

        //  Test peek
        System.out.println(stack.peek());

        //  Test pop
        System.out.println(stack.pop());
        System.out.println("stack = " + stack);
        System.out.println("stack.size = " + stack.size());

        //  Test clear
        stack.clear();
        System.out.println(stack.size());

    }
}
