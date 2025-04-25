package org.example.utils.files;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collection;


public interface DataStorage<T> {
    void save(Collection<T> data, String filePath) throws IOException;
    ArrayDeque<T> load(String filePath) throws IOException;
}
