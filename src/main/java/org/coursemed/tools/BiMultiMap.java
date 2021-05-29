package org.coursemed.tools;

import java.util.*;

public class BiMultiMap<K, V> {
    private Map<K, List<V>> firstToSecond = new HashMap<>();
    private Map<V, List<K>> secondToFirst = new HashMap<>();

    public void put(K k, V v) {
        if (!firstToSecond.containsKey(k)) {
            firstToSecond.put(k, new ArrayList<>());
        }

        firstToSecond.get(k).add(v);


        if (!secondToFirst.containsKey(v)) {
            secondToFirst.put(v, new ArrayList<>());
        }

        secondToFirst.get(v).add(k);
    }

    public List<V> getSecondList(K k) {
        return firstToSecond.get(k);
    }

    public List<K> getFirstList(V v) {
        return secondToFirst.get(v);
    }
}
