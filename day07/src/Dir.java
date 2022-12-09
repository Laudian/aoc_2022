import java.util.*;

public class Dir {
    private final Map<String, File> files = new HashMap<>();
    private final Map<String, Dir> dirs = new HashMap<>();
    private static Dir currentDir = null;
    private final Dir parent;
    private Integer size = null;
    public Dir() {
        if (currentDir == null) {
            currentDir = this;
        }
        parent = currentDir;
    }
    public void addDir(String pName) {
        Dir newDir = new Dir();
        currentDir.dirs.put(pName, newDir);
    }
    public void addFile(String pName, int pSize) {
        File newFile = new File(pSize);
        currentDir.files.put(pName, newFile);
    }
    public void changeDir(String pName) {
        if (pName.equals("..")) {
            currentDir = currentDir.parent;
        } else {
            currentDir = currentDir.dirs.get(pName);
        }
    }
    public int getSize() {
        if (size != null) {
            return size;
        }
        size = 0;
        for (File file: files.values()) {
            size += file.size;
        }
        for (Dir dir: dirs.values()) {
            size += dir.getSize();
        }
        return size;
    }
    public void part1() {
        int size = 0;
        Iterator<Dir> dirIterator = getIterator();
        while (dirIterator.hasNext()) {
            Dir dir = dirIterator.next();
            int dirsize = dir.getSize();
            if (dirsize < 100000) {
                size += dirsize;
            }
        }
        System.out.println("Part 1: " + size);
    }
    
    public void part2() {
        int totalSpace = 70000000;
        int neededSpace = 30000000;
        int usedSpace = getSize();
        int targetSize = neededSpace - (totalSpace - usedSpace);
        int smallest = Integer.MAX_VALUE;

        Iterator<Dir> dirIterator = getIterator();
        while (dirIterator.hasNext()) {
            Dir dir = dirIterator.next();
            int size = dir.getSize();
            if (size > targetSize && size < smallest) {
                smallest = size;
            }
        }
        System.out.println("Part 2: " + smallest);
    }
    
    public Iterator<Dir> getIterator() {
        return new Iterator<>() {
            private final List<Dir> itlist = new ArrayList<>(dirs.values());
            private int pos = 0;
            @Override
            public boolean hasNext() {
                return pos < itlist.size();
            }

            @Override
            public Dir next() {
                Dir dir = itlist.get(pos++);
                itlist.addAll(dir.dirs.values());
                return dir;
            }
        };
    }
    
    private static class File {
        private final int size;

        public File(int pSize) {
            size = pSize;
        }
    }
}
