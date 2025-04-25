package org.example.manager;

import org.example.models.City;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

public class IdManager {
    private static final AtomicLong id_generate = new AtomicLong();
    public static void init(Collection<City> cities) {
        long maxId = cities.stream()
                .mapToLong(City::getId)
                .max()
                .orElse(0);
        id_generate.set(maxId);
    }
    public static long next_id(){
        return id_generate.incrementAndGet();
    }
}
