package com.concurrency.arraytask;

import java.util.*;

public class SynchronizedArrayList implements List {

    private ArrayList arrayList;

    public SynchronizedArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    public int size() {
        synchronized (arrayList) {
            return arrayList.size();
        }
    }

    public boolean isEmpty() {
        synchronized (arrayList) {
            return arrayList.isEmpty();
        }
    }

    public boolean contains(Object o) {
        synchronized (arrayList) {
            return arrayList.contains(o);
        }
    }

    public Iterator iterator() {
        synchronized (arrayList) {
            return arrayList.iterator();
        }
    }

    public Object[] toArray() {
        synchronized (arrayList) {
            return arrayList.toArray();
        }
    }

    public boolean add(Object o) {
        synchronized (arrayList) {
            return arrayList.add(o);
        }
    }

    public boolean remove(Object o) {
        synchronized (arrayList) {
            return arrayList.remove(o);
        }
    }

    public boolean addAll(Collection c) {
        synchronized (arrayList) {
            return arrayList.addAll(c);
        }
    }

    public boolean addAll(int index, Collection c) {
        synchronized (arrayList) {
            return arrayList.addAll(index, c);
        }
    }

    public void clear() {
        synchronized (arrayList) {
            arrayList.clear();
        }
    }

    public Object get(int index) {
        synchronized (arrayList) {
            return arrayList.get(index);
        }
    }

    public Object set(int index, Object element) {
        synchronized (arrayList) {
            return arrayList.set(index, element);
        }
    }

    public void add(int index, Object element) {
        synchronized (arrayList) {
            arrayList.add(index, element);
        }
    }

    public Object remove(int index) {
        synchronized (arrayList) {
            return arrayList.remove(index);
        }
    }

    public int indexOf(Object o) {
        synchronized (arrayList) {
            return arrayList.indexOf(o);
        }
    }

    public int lastIndexOf(Object o) {
        synchronized (arrayList) {
            return arrayList.lastIndexOf(o);
        }
    }

    public ListIterator listIterator() {
        synchronized (arrayList) {
            return arrayList.listIterator();
        }
    }

    public ListIterator listIterator(int index) {
        synchronized (arrayList) {
            return arrayList.listIterator(index);
        }
    }

    public List subList(int fromIndex, int toIndex) {
        synchronized (arrayList) {
            return arrayList.subList(fromIndex, toIndex);
        }
    }

    public boolean retainAll(Collection c) {
        synchronized (arrayList) {
            return arrayList.retainAll(c);
        }
    }

    public boolean removeAll(Collection c) {
        synchronized (arrayList) {
            return arrayList.removeAll(c);
        }
    }

    public boolean containsAll(Collection c) {
        synchronized (arrayList) {
            return arrayList.containsAll(c);
        }
    }

    public Object[] toArray(Object[] a) {
        synchronized (arrayList) {
            return arrayList.toArray(a);
        }
    }
}
