package org.example.manager;

import lombok.Getter;
import org.example.models.City;

import java.io.File;
import java.util.ArrayDeque;

public class CollectionManager {
    public CollectionManager(File file, ArrayDeque collection){
        this.file = file;
        this.collection = collection;
    }
    private final File file;
    @Getter
    ArrayDeque<City> collection;

    Console console = new Console();

}
