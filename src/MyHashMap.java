import java.util.Arrays;

public class MyHashMap<K, V> {
    private int size = 0;
    private final Node<K, V>[] nodes;

    public MyHashMap() {
        int INITIAL_CAPACITY = 16;
        nodes = new Node[INITIAL_CAPACITY];
    }

    public MyHashMap(int size, Node<K, V>[] nodes) {
        this.size = size;
        this.nodes = nodes;
    }

    public void put(K key, V value) {
        int index = key.hashCode() % nodes.length;
        Node<K, V> node = nodes[index];

        if (node == null) {
            node = new Node<>(key, value, null);
            nodes[index] = node;
            size++;
        } else {
            while (node != null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                node = node.next;
            }
            node = new Node<>(key, value, nodes[index]);
            nodes[index] = node;
            size++;
        }
    }

    public V get(K key) {
        int index = key.hashCode() % nodes.length;
        Node<K, V> node = nodes[index];

        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }

        return null;
    }

    public void remove(K key) {
        int index = key.hashCode() % nodes.length;
        Node<K, V> node = nodes[index];
        Node<K,V> previous = null;

        while (node != null) {
            if (node.key.equals(key)) {
                if (previous == null) {
                    nodes[index] = node.next;
                } else {
                    previous.next = node.next;
                }
                size--;
                return;
            }
            previous = node;
            node = node.next;
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(nodes, null);
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<K, V> node : nodes) {
            while (node != null) {
                sb.append(node.key).append(" -> ").append(node.value).append("; ");
                node = node.next;
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key + " -> " + value;
        }

    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        System.out.println("Size: " + map.size);
        map.put(1, "Abrams");
        map.put(2, "Leopard 1");
        map.put(3, "T-90");
        map.put(2, "Leopard 2");
        map.put(4, "Challenger");
        System.out.println(map);

        System.out.println("Size: " + map.size());
        System.out.println(map.get(3));
        map.remove(3);
        System.out.println("Remove element 3");
        System.out.println("Size: " + map.size);
        System.out.println(map);
        map.clear();
        System.out.println("Size: " + map.size);

        for (int i = 0; i < 242; i++) {
            map.put(i, "i");
//            System.out.println(map);
        }
        System.out.println("Size: " + map.size());
    }
}

