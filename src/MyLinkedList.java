public  class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T value) {
        Node<T> newNode = new Node<T>(value);
        if(head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        size++;
    }

    public void remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<T> currentNode = head;
            for(int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            if(currentNode.getPrevious() == null) {
                head = currentNode.getNext();
                head.setPrevious(null);
            } else if (currentNode.getNext() == null) {
                tail = currentNode.getPrevious();
                tail.setNext(null);
            } else {
                Node<T> prev = currentNode.getPrevious();
                Node<T> next = currentNode.getNext();
                prev.setNext(next);
                next.setPrevious(prev);
            }
            size--;
        }
    }

    public T get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<T> currentNode = head;
            for(int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            return currentNode.getValue();
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        //  Test add & size
        System.out.println(list.size());
        //  Test get
        System.out.println(list.get(2));

        //  Test remove
        list.remove(1);
        System.out.println(list.get(0));
        System.out.println(list.size());

        //  Test clear
        list.clear();
        System.out.println(list.size());
    }
}

class Node<T> {
    private T value;
    private Node<T> previous;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
        this.previous = null;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
    @Override
    public String toString() {
        return value.toString();
    }
}
