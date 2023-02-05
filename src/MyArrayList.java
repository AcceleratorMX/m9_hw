import java.util.Arrays;
import java.util.StringJoiner;

public class MyArrayList<E> {

    private static final int DEFAULT_SIZE = 10;
    private Object[] data;
    private int size;

    public MyArrayList() {
        this.data = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    public void add(E value){
        if(size == data.length){
            int newSize = (data.length * 3)/2 + 1;
            Object[] tempData;
            tempData = Arrays.copyOf(data, newSize);
            data = tempData;
        }
        data[size]=value;
        size = size + 1;
    }

    public void remove(int index){
        int newSize = size - 1;
        if (newSize > index){
            System.arraycopy(data, index + 1, data, index, newSize - index);
        } else {
            data[index] = null;
        }
        data[newSize] = null;

        size = newSize;
    }

    public void clear() {
        this.data = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public Object get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return this.data[index];
    }

    @Override
    public String toString() {
        StringJoiner res = new StringJoiner(",");

        for (Object element:data) {
            if(element!=null) {
                res.add(element.toString());
            }
        }

        return res+"";
    }


    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");

        //  Test add & size
        System.out.println(list.size());

        //  Test get
        System.out.println(list.get(1));

        //  Test remove
        list.remove(1);
        System.out.println(list.size());
        System.out.println("list: " + list);

        //  Test clear
        list.clear();
        System.out.println(list.size());
    }
}
