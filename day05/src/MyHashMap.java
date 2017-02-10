import java.util.*;

/**
 * Implementation of a HashMap using a collection of MyLinearMap and
 * resizing when there are too many or too few entries.
 *
 * @param <K>
 * @param <V>
 * @author downey
 */
public class MyHashMap<K, V> implements Map<K, V> {

    // average number of entries per map before we grow the map
    private static final double ALPHA = 1.0;
    // average number of entries per map before we shrink the map
    private static final double BETA = .25;

    // resizing factor: (new size) = (old size) * (resize factor)
    private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

    private static final int MIN_MAPS = 16;

    // list of maps
    protected List<MyLinearMap<K, V>> maps;
    private int size = 0;

    public MyHashMap() {
        makeMaps(MIN_MAPS);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Initialize maps
     */
    protected void makeMaps(int hey) {
        maps = new ArrayList<MyLinearMap<K, V>>(hey);
        for (int i = 0; i < hey; i++) {
            maps.add(new MyLinearMap<K, V>());
        }
    }

    protected MyLinearMap<K, V> chooseMap(Object key) {
        if (key == null) {
            int index = 0;
            return maps.get(index);
        } else {
            int temp = key.hashCode();
            int index = temp % maps.size();
            if (index<0){
                index +=maps.size();
            }
            return maps.get(index);
        }
    }

    @Override
    public boolean containsKey(Object key) {
        MyLinearMap<K, V> map = chooseMap(key);
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < maps.size(); i++) {
            MyLinearMap<K, V> map = maps.get(i);
            if (map.containsValue(value)) {
                return true;
            }
        }

        return false;
    }

    protected void rehash(double growthFactor) {
        List<MyLinearMap<K, V>> orig = maps;
        int newSize = (int) (orig.size() * growthFactor);
        makeMaps(newSize);
        size = 0;
        for (int i = 0; i < orig.size(); i++) {
            MyLinearMap<K, V> map = orig.get(i);
            for (K key : map.keySet()) {
                V value = map.get(key);
                put(key, value);
            }
        }
    }

    @Override
    public V get(Object key) {
        MyLinearMap<K, V> m = chooseMap(key);
        return m.get(key);
    }

    @Override
    public V put(K key, V value) {
        MyLinearMap<K, V> map = chooseMap(key);
        size -= map.size();
        V oldV = map.put(key, value);
        size += map.size();
        if (size > maps.size() * ALPHA) {
            rehash(GROWTH_FACTOR);
        }
        return oldV;
    }

    @Override
    public V remove(Object key) {
        MyLinearMap<K, V> map = chooseMap(key);
        //System.out.println(map.size());
        size -= map.size();
        V oldV = map.remove(key);
        size += map.size();
        if (size < maps.size() * BETA) {
            rehash(SHRINK_FACTOR);
        }
        return oldV;

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        for (int i = 0; i < maps.size(); i++) {
            maps.get(i).clear();
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (MyLinearMap<K, V> map : maps) {
            set.addAll(map.keySet());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> ll = new LinkedList<>();
        for (MyLinearMap<K, V> map : maps) {
            ll.addAll(map.values());
        }
        return ll;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (MyLinearMap<K, V> map : maps) {
            set.addAll(map.getEntries());
        }
        return set;
    }
}
