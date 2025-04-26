package org.example.manager;

import lombok.Getter;
import org.example.models.City;
import org.example.utils.files.AppConfig;
import org.example.utils.files.CsvDataStorage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
@Getter
public class CollectionManager implements Iterable<City>{
    public static CollectionManager instance;
    private CollectionManager() {}
    public static CollectionManager getInstance(){
        try {
            if (instance == null) {
                instance = new CollectionManager();
                AppConfig config = new AppConfig("lab_5");
                instance.filePath = config.getFilePath();
                instance.lastInitTime = LocalDateTime.now();
                CsvDataStorage csvDataStorage = new CsvDataStorage();
                instance.collection = csvDataStorage.load(instance.filePath);
            }
        }
        catch (IOException e){
            System.err.println("Хуй то там: " + e.getMessage());
            System.exit(1);
        }
        return instance;
    }
    private String filePath;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private ArrayDeque<City> collection = new ArrayDeque<>();

    public boolean add(City city){
        return collection.add(city);
    }

    public boolean update(Long id, City city){
        City curCity = getID(id);
        if (curCity == null){
            return false;
        }

        if (!collection.remove(curCity)){
            return false;
        }
        city.setId(id);
        return collection.add(city);
    }

    public boolean removeById(Long id){
        return collection.removeIf(x -> x.getId() == id);
    }


    private City getID(Long id){
        for (City city : collection){
            if(city.getId() == id){
                return city;
            }
        }
        return null;
    }


    public void clear(){
        collection.clear();
    }

    public City getMin(){
        return Collections.min(collection);
    }

    public void save() throws IOException{
        CsvDataStorage csvDataStorage = new CsvDataStorage();
        csvDataStorage.save(collection, filePath);
        lastSaveTime = LocalDateTime.now();
    }

    @Override
    public Iterator<City> iterator() {
        return collection.iterator();
    }
}
