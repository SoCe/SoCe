package lib.module.filter;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Justin on 19.11.2014.
 */
public class JARFileFilter implements FileFilter {
    @Override
    public boolean accept(File file) {
        return file.getName().toLowerCase().endsWith(".jar");
    }
}
