package lazy.initialization.examples;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author a.taganov
 * based on https://gist.github.com/pal/110024/8a845866d3aad6865a4d2cad2b3eff112b61b1d5
 */
@SuppressWarnings("unchecked")
public class ClasspathInspector {
    static boolean DEBUG = false;

    public static List<Class> getAllKnownClassesForPackage(String packagePrefix) {
        List<Class> classFiles = new ArrayList<Class>();
        List<File> classLocations = getClassLocationsForCurrentClasspath();
        for (File file : classLocations) {
            classFiles.addAll(getClassesFromPath(file, packagePrefix));
        }
        return classFiles;
    }

    public static List<Class> getMatchingClassesForPackage(Class interfaceOrSuperclass, String packagePrefix) {
        List<Class> matchingClasses = new ArrayList<Class>();
        List<Class> classes = getAllKnownClassesForPackage(packagePrefix);
        log("checking %s classes", classes.size());
        for (Class clazz : classes) {
            if (interfaceOrSuperclass.isAssignableFrom(clazz)) {
                matchingClasses.add(clazz);
                log("class %s is assignable from %s", interfaceOrSuperclass, clazz);
            }
        }
        return matchingClasses;
    }

    private static Collection<? extends Class> getClassesFromPath(File path, String packagePrefix) {
        if (path.isDirectory()) {
            return getClassesFromDirectory(path, packagePrefix);
        } else {
            return getClassesFromJarFile(path, packagePrefix);
        }
    }

    private static String fromFileToClassName(final String fileName) {
        return fileName.substring(0, fileName.length() - 6).replaceAll("/|\\\\", "\\.");
    }

    private static List<Class> getClassesFromJarFile(File path, String packagePrefix) {
        List<Class> classes = new ArrayList<Class>();
        log("getClassesFromJarFile: Getting classes for " + path);

        try {
            if (path.canRead()) {
                JarFile jar = new JarFile(path);
                Enumeration<JarEntry> en = jar.entries();
                while (en.hasMoreElements()) {
                    JarEntry entry = en.nextElement();
                    if (entry.getName().endsWith("class") && entry.getName().startsWith(packagePrefix)) {
                        String className = fromFileToClassName(entry.getName());
                        log("\tgetClassesFromJarFile: found " + className);
                        Class claz = Class.forName(className);
                        classes.add(claz);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read classes from jar file: " + path, e);
        }

        return classes;
    }

    private static List<Class> getClassesFromDirectory(File path, String packagePrefix) {
        List<Class> classes = new ArrayList<Class>();
        log("getClassesFromDirectory: Getting classes for " + path);

        // get jar files from top-level directory
        List<File> jarFiles = listFiles(path, new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jar");
            }
        }, false);
        for (File file : jarFiles) {
            classes.addAll(getClassesFromJarFile(file, packagePrefix));
        }

        // get all class-files
        List<File> classFiles = listFiles(path, new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }
        }, true);

        // List<URL> urlList = new ArrayList<URL>();
        // List<String> classNameList = new ArrayList<String>();
        int substringBeginIndex = path.getAbsolutePath().length() + 1;
        for (File classfile : classFiles) {
            String className = classfile.getAbsolutePath().substring(substringBeginIndex);
            className = fromFileToClassName(className);
            log("Found class %s in path %s: ", className, path);
            try {
                classes.add(Class.forName(className));
            } catch (Throwable e) {
                log("Couldn't create class %s. %s: ", className, e);
            }

        }

        return classes;
    }

    private static List<File> listFiles(File directory, FilenameFilter filter, boolean recurse) {
        List<File> files = new ArrayList<File>();
        File[] entries = directory.listFiles();

        // Go over entries
        for (File entry : entries) {
            // If there is no filter or the filter accepts the
            // file / directory, add it to the list
            if (filter == null || filter.accept(directory, entry.getName())) {
                files.add(entry);
            }

            // If the file is a directory and the recurse flag
            // is set, recurse into the directory
            if (recurse && entry.isDirectory()) {
                files.addAll(listFiles(entry, filter, recurse));
            }
        }

        // Return collection of files
        return files;
    }

    public static List<File> getClassLocationsForCurrentClasspath() {
        List<File> urls = new ArrayList<File>();
        String javaClassPath = System.getProperty("java.class.path");
        if (javaClassPath != null) {
            for (String path : javaClassPath.split(File.pathSeparator)) {
                urls.add(new File(path));
            }
        }
        return urls;
    }

    private static void log(String pattern, final Object... args) {
        if (DEBUG)
            System.out.printf(pattern + "\n", args);
    }
}
