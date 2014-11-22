package lib.task.impl;

import lib.task.IModuleTask;

import java.util.HashMap;

/**
 * Created by Justin on 22.11.2014.
 */
public class ModuleTask implements IModuleTask {

    protected HashMap<String,Object> moduleTaskData = new HashMap<String,Object>();

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
}
