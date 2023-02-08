import java.util.Arrays;

public class MyHashMap<K, V> {

    private static class Entry<K, V> {

         final K key;
         V value;
        private Entry<K,V> next;

        Entry(K key, V value, Entry<K, V> next) {

            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int size = 0;
    private final int MAX_SIZE = 16;
    private final Entry<K, V>[] table;

    public MyHashMap () {
        this.table = new Entry[MAX_SIZE];
    }


    public void put(K key, V value) {
        int i = hash(key);
        for (Entry<K, V> x = table[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        table[i] = new Entry<>(key, value, table[i]);
        size++;
    }


    public void remove(K key) {
        int i = hash(key);
        int count = 0;

        for (Entry<K, V> x = table[i]; x != null; x = x.next) {
            ++count;
            if (key.equals(x.key) && x.next == null && count == 1) {
                table[i] = null;
                size--;
                return;
            }
            if (key.equals(x.key) && x.next != null && count == 1) {
                x = x.next;
                table[i] = x;
                size--;
                return;
            }
            assert x.next != null;
            if (key.equals(x.next.key) && x.next.next != null) {
                x.next = x.next.next;
                size--;
                return;
            }

            if (key.equals(x.next.key) && x.next.next == null) {
                x.next = null;
                size--;
                return;
            }
        }
    }


    public void clear() {
        if (size > 0) {
            size = 0;
            Arrays.fill(table, null);
        }
    }


    public int size() {
        return size;
    }


    public Object get(K key) {
        int i = hash(key);
        for (Entry<K, V> x = table[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % MAX_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Entry<K, V> kvEntry : table) {
            Entry<K, V> node = kvEntry;
            while (node != null) {
                sb.append(node.key).append(" -> ").append(node.value).append("; ");
                node = node.next;
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        System.out.println(map.size());
        map.put("Abrams", 1);
        map.put("Abrams", 31);
        map.put("Challenger", 14);
        map.put("Leopard", 88);

        //  Put & size test
        System.out.println(map);
        System.out.println(map.size());

        // Get test
        System.out.println(map.get("Challenger"));

        //  Remove test
        map.remove("Challenger");
        System.out.println(map);
        System.out.println(map.size());

        //  Clear test
        map.clear();
        System.out.println(map.size());

        //  Stretching test
        for (int i = 0; i < 234; i++) {
            map.put("test + " + i, i);
        }
        System.out.println("map.size() = " + map.size());
    }
}
