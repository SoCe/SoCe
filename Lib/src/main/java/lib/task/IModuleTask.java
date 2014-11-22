package lib.task;

/**
 * Created by Justin on 22.11.2014.
 */
public interface IModuleTask extends Runnable, Comparable<IModuleTask> {
    public void setModuleTaskData (String key, Object object);
    public Object getModuleTaskData (String key);
    public void setPriority (int priority);
    public int getPriority ();
}
