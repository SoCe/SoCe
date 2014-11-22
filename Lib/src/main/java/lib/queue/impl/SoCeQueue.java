package lib.queue.impl;

import lib.queue.ISoCeQueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Justin on 22.11.2014.
 */
public class SoCeQueue<T> implements ISoCeQueue<T> {

    protected List<T> queue = new ArrayList<T>();

    @Override
    public T next() {
        //First In, first out
        if (this.queue.size() > 0) {
            T entry = this.queue.get(0);
            this.queue.remove(entry);

            return entry;
        } else {
            return null;
        }
    }

    @Override
    public void addEntry(T object) {
        this.queue.add(object);
    }

    @Override
    public T getEntry(int i) {
        if (this.queue.size() >= i) {
            //get entry from queue
            T entry = this.queue.get(i);

            //remove item from queue
            this.queue.remove(entry);

            return entry;
        } else {
            return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.queue.iterator();
    }
}
