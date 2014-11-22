package lib.queue;

/**
 * Created by Justin on 22.11.2014.
 */
public interface ISoCeQueue<T> extends Iterable<T> {
    public T next ();
    public void addEntry (T object);
    public T getEntry (int i);
}
