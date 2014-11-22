package lib.queue;

/**
 * Created by Justin on 22.11.2014.
 */
public interface ISoCePriorityQueue<T extends Comparable<T>> extends ISoCeQueue<T> {
    public void sortQueue ();
}
