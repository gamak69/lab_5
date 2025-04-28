import org.example.manager.CollectionManager;
import org.example.models.*;
import org.example.utils.files.CsvDataStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class SukaTest {
    private CollectionManager collectionManager;
    private City city;
    private City createTestCity(Long id, String name) {
        return new City(
                id,
                name,
                new Coordinates(55L, 37.6),
                LocalDateTime.now(),
                1000L,
                1_000_000L,
                150.5f,
                new Date(),
                Government.REPUBLIC,
                StandardOfLiving.VERY_HIGH,
                new Human(180.0)
        );
    }
    private City getById(CollectionManager manager, Long id) throws Exception {
        Method method = CollectionManager.class.getDeclaredMethod("getID", Long.class);
        method.setAccessible(true);
        return (City) method.invoke(manager, id);
    }

    @BeforeEach
    void setUp() {
        collectionManager = CollectionManager.getInstance();
        collectionManager.clear();
    }
    @Test
    void testAddElement() {
        City city = createTestCity(1L, "Москва");
        assertTrue(collectionManager.add(city));
        assertEquals(1, collectionManager.getCollection().size());
    }

    @Test
    void testGetMinElement() {
        collectionManager.add(createTestCity(1L, "Бугага"));
        collectionManager.add(createTestCity(2L, "Артем"));

        City min = collectionManager.getMin();
        assertEquals("Артем", min.getName());
    }

    @Test
    void testAddIfMin() {
        City city1 = createTestCity(1L, "Москва");
        assertTrue(collectionManager.addIfMin(city1));
        assertEquals(1, collectionManager.getCollection().size());

        City city2 = createTestCity(2L, "Абакан");
        assertTrue(collectionManager.addIfMin(city2));
        assertEquals(2, collectionManager.getCollection().size());
        assertEquals("Абакан", collectionManager.getMin().getName());

        City city3 = createTestCity(3L, "Ярославль");
        assertFalse(collectionManager.addIfMin(city3));
        assertEquals(2, collectionManager.getCollection().size());
    }
    @Test
    void testRemoveElement() {
        City city = createTestCity(1L, "Екатеринбург");
        collectionManager.add(city);

        assertTrue(collectionManager.removeById(1L));
        assertTrue(collectionManager.getCollection().isEmpty());
    }

    @Test
    void testUpdateElement() throws Exception {
        City city = createTestCity(1L, "Пизда");
        collectionManager.add(city);

        City updated = createTestCity(1L, "Пизда обновленная");
        assertTrue(collectionManager.update(1L, updated));
        City result = getById(collectionManager,1L);
        assertEquals("Пизда обновленная", result.getName());
    }

    @Test
    void testClearCollection() {
        collectionManager.add(createTestCity(1L, "цгшкмиуккмишукц"));
        collectionManager.add(createTestCity(2L, "тсмиалдкуатк"));

        collectionManager.clear();
        assertTrue(collectionManager.getCollection().isEmpty());
    }

    @Test
    void testMassiveInsertion() {
        int count = 10_000;
        for (long i = 0; i < count; i++) {
            collectionManager.add(createTestCity(i, "Город" + i));
        }
        assertEquals(count, collectionManager.getCollection().size());
    }

    @Test
    void testUpdateNonExistentElement() {
        City city = createTestCity(999L, "Ghost");
        assertFalse(collectionManager.update(1L, city));
    }

    @Test
    void testMinimalValidValues() throws Exception {
        City city = new City(
                1L,
                "A",
                new Coordinates(1L, -658.9),
                LocalDateTime.now(),
                1L,
                1L,
                Float.MIN_VALUE,
                new Date(),
                Government.ANARCHY,
                StandardOfLiving.NIGHTMARE,
                new Human(Double.MIN_VALUE)
        );

        assertDoesNotThrow(() -> collectionManager.add(city));
    }

    @Test
    void testUniqueIdAfterLoad() throws Exception {
        CsvDataStorage storage = new CsvDataStorage();
        collectionManager.add(createTestCity(1L, "MoscowNeverSleeps"));
        storage.save(collectionManager.getCollection(), "test.csv");

        ArrayDeque<City> loaded = storage.load("test.csv");
        Set<Long> ids = loaded.stream()
                .map(City::getId)
                .collect(Collectors.toSet());

        assertEquals(loaded.size(), ids.size());
    }

}
