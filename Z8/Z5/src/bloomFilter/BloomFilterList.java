package bloomfilter;

import list.List;
import java.util.Iterator;

public class BloomFilterList<E> implements List<E> {

    private List<E> list;
    private BloomFilter<E> bloomFilter;

    public BloomFilterList(List<E> list, BloomFilter<E> bloomFilter){
        this.list = list;
        this.bloomFilter = bloomFilter;
    }

    @Override
    public void add(E element) throws IndexOutOfBoundsException {
        list.add(element);
        bloomFilter.add(element);
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        list.remove(index);
        bloomFilter.empty();
        for(E item : list)
            bloomFilter.add(item);
        return null;
    }

    @Override
    public boolean contains(E element) {
        if(!bloomFilter.contains(element))
            return false;
        return list.contains(element);
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        return list.get(index);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
