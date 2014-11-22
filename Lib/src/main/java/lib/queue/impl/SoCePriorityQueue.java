package lib.queue.impl;

import lib.queue.ISoCePriorityQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Justin on 22.11.2014.
 */
public class SoCePriorityQueue<T extends Comparable<T>> implements ISoCePriorityQueue<T> {

    protected List<T> queue = new ArrayList<T>();

    @Override
    public void sortQueue() {
        Collections.sort(this.queue);
    }

    @Override
    public T next() {
        //sort queue
        this.sortQueue();

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
