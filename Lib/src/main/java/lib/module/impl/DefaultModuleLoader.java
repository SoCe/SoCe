package lib.module.impl;

import lib.logger.LoggerInstance;
import lib.module.IModule;
import lib.module.IModuleLoader;
import lib.module.filter.JARFileFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Created by Justin on 19.11.2014.
 */
public class DefaultModuleLoader implements IModuleLoader {

    protected String modulePath = "./modules";
    protected List<IModule> moduleList = new ArrayList<IModule>();

    public DefaultModuleLoader (String modulePath) {
        this.modulePath = modulePath;
    }

    @Override
    public void loadModules() {
        //http://jaxenter.com/tips-for-writing-pluggable-java-ee-applications-105281.html

        File file = new File(this.modulePath);

        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            this.moduleList = DefaultModuleLoader.loadModules(file);
        } catch (IOException e) {
            LoggerInstance.getLogger().error("DefaultModuleLoader: IOException in method loadModules() " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LoggerInstance.getLogger().error("DefaultModuleLoader: IOException in method loadModules() " + e.getLocalizedMessage());
        }
    }

    @Override
    public void startModules() {
        //
    }

    @Override
    public void stopModules() {
        //
    }

    @Override
    public List<IModule> getModules() {
        return null;
    }

    public static List<IModule> loadModules(File plugDir) throws IOException {

        File[] plugJars = plugDir.listFiles(new JARFileFilter());
        ClassLoader cl = new URLClassLoader(DefaultModuleLoader.fileArrayToURLArray(plugJars));
        List<Class<IModule>> plugClasses = DefaultModuleLoader.extractClassesFromJARs(plugJars, cl);
        return DefaultModuleLoader.createPluggableObjects(plugClasses);
    }

    private static URL[] fileArrayToURLArray(File[] files) throws MalformedURLException {

        URL[] urls = new URL[files.length];
        for (int i = 0; i < files.length; i++) {
            urls[i] = files[i].toURI().toURL();
        }
        return urls;
    }

    private static List<Class<IModule>> extractClassesFromJARs(File[] jars, ClassLoader cl) throws IOException {

        List<Class<IModule>> classes = new ArrayList<Class<IModule>>();
        for (File jar : jars) {
            classes.addAll(DefaultModuleLoader.extractClassesFromJAR(jar, cl));
        }
        return classes;
    }

    @SuppressWarnings("unchecked")
    private static List<Class<IModule>> extractClassesFromJAR(File jar, ClassLoader cl) throws IOException {

        List<Class<IModule>> classes = new ArrayList<Class<IModule>>();
        JarInputStream jaris = new JarInputStream(new FileInputStream(jar));
        JarEntry ent = null;
        while ((ent = jaris.getNextJarEntry()) != null) {
            if (ent.getName().toLowerCase().endsWith(".class")) {
                try {
                    Class<?> cls = cl.loadClass(ent.getName().substring(0, ent.getName().length() - 6).replace('/', '.'));
                    if (DefaultModuleLoader.isModuleClass(cls)) {
                        classes.add((Class<IModule>)cls);
                    }
                }
                catch (ClassNotFoundException e) {
                    System.err.println("Can't load Class " + ent.getName());
                    e.printStackTrace();
                }
            }
        }
        jaris.close();
        return classes;
    }

    private static boolean isModuleClass(Class<?> cls) {

        for (Class<?> i : cls.getInterfaces()) {
            if (i.equals(IModule.class)) {
                return true;
            }
        }
        return false;
    }

    private static List<IModule> createPluggableObjects(List<Class<IModule>> pluggables) {

        List<IModule> plugs = new ArrayList<IModule>(pluggables.size());
        for (Class<IModule> plug : pluggables) {
            try {
                plugs.add(plug.newInstance());
            }
            catch (InstantiationException e) {
                System.err.println("Can't instantiate plugin: " + plug.getName());
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                System.err.println("IllegalAccess for plugin: " + plug.getName());
                e.printStackTrace();
            }
        }
        return plugs;
    }
}
