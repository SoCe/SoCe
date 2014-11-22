package lib.task.impl;

import lib.task.IModuleTask;

import java.util.HashMap;

/**
 * Created by Justin on 22.11.2014.
 */
public abstract class ModuleTask implements IModuleTask {

    protected HashMap<String,Object> moduleTaskData = new HashMap<String,Object>();
    protected int priority = 5;

    @Override
    public void setModuleTaskData(String key, Object object) {
        this.moduleTaskData.put(key, object);
    }

    @Override
    public Object getModuleTaskData(String key) {
        if (this.moduleTaskData.containsKey(key)) {
            return this.moduleTaskData.get(key);
        } else {
            return null;
        }
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public abstract void run();

    @Override
    public int compareTo(IModuleTask o) {
        if (o.getPriority() < this.getPriority()) {
            return -1;
        } else {
            return 1;
        }
    }
}
