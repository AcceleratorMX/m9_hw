public class MyHashMap<K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private Node<K, V> head;
    private int size;

    // constructor
    public MyHashMap() {
        head = null;
        size = 0;
    }

    public void put(K key, V value) {
        if (head == null) {
            head = new Node<K, V>(key, value);
            size++;
            return;
        }
        Node<K, V> currentNode = head;
        while (currentNode.next != null) {
            if (currentNode.getKey().equals(key)) {
                currentNode.value = value;
                return;
            }
            currentNode = currentNode.next;
        }
        if (currentNode.getKey().equals(key)) {
            currentNode.value = value;
        } else {
            currentNode.next = new Node<K, V>(key, value);
            size++;
        }
    }

    public void remove(K key) {
        Node<K, V> currentNode = head;
        Node<K, V> previousNode = null;

        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                if (previousNode != null)
                    previousNode.next = currentNode.next;
                else
                    head = currentNode.next;
                size--;
                return;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
    }

    public V get(K key) {
        Node<K, V> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getKey().equals(key))
                return currentNode.getValue();
            currentNode = currentNode.next;
        }
        return null;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }


    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("Leopard 1", 14);
        map.put("Leopard 2", 88);
        map.put("Challenger", 12);

        // Test put & size
        System.out.println(map.size());

        //  Test get
        System.out.println(map.get("Leopard 1"));

        //  Test remove
        map.remove("Leopard 1");
        System.out.println(map.size());

        //  Test clear
        map.clear();
        System.out.println(map.size());

    }
}