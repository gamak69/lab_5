package org.example.utils.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.manager.IdManager;
import org.example.models.*;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class CsvDataStorage implements DataStorage<City> {
    private static final CsvMapper mapper = new CsvMapper();
    private static final CsvSchema schema = CsvSchema.builder()
            .addColumn("id")
            .addColumn("name")
            .addColumn("x")
            .addColumn("y")
            .addColumn("creationDate")
            .addColumn("area")
            .addColumn("population")
            .addColumn("metersAboveSeaLevel")
            .addColumn("establishmentDate")
            .addColumn("government")
            .addColumn("standardOfLiving")
            .addColumn("governor")
            .build()
            .withHeader();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    static {
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void save(Collection<City> data, String filePath) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();
        for (City city : data) {
            Map<String, String> row = new LinkedHashMap<>();
            row.put("id", String.valueOf(city.getId()));
            row.put("name", city.getName());
            row.put("x", String.valueOf(city.getCoordinates().getX()));
            row.put("y", String.valueOf(city.getCoordinates().getY()));
            row.put("creationDate", String.valueOf(city.getCreationDate()));
            row.put("area", String.valueOf(city.getArea()));
            row.put("population", String.valueOf(city.getPopulation()));
            row.put("metersAboveSeaLevel", String.valueOf(city.getMetersAboveSeaLevel()));
            row.put("establishmentDate", String.valueOf(sdf.format(city.getEstablishmentDate())));
            row.put("government", String.valueOf(city.getGovernment()));
            row.put("standardOfLiving", String.valueOf(city.getStandardOfLiving()));
            row.put("governor", String.valueOf(city.getGovernor().getHeight()));

            rows.add(row);
        }

        mapper.writerFor(List.class)
                .with(schema)
                .writeValue(new File(filePath), rows);
    }

    @Override
    public ArrayDeque<City> load(String filePath) throws IOException {
        MappingIterator<Map<String, String>> it = mapper.readerFor(Map.class)
                .with(schema)
                .readValues(new File(filePath));
        ArrayDeque<City> cities = new ArrayDeque<>();
        while (it.hasNext()) {
            Map<String, String> row = it.next();
            City city = mapRowToCity(row);
            cities.add(city);
        }
        IdManager.init(cities);
        return cities;
    }

    private City mapRowToCity(Map<String, String> row) throws IOException {
        City city = new City();
        List<String> errors = new ArrayList<>();

        // ID
        try {
            if (Long.parseLong(row.get("id")) < 0){
                errors.add("ID должно быть > 0");
            }
            city.setId(Long.parseLong(row.get("id")));
        } catch (NumberFormatException | NullPointerException e) {
            errors.add("Некорректный ID: " + row.get("id"));
        }

        // Name
        if (row.get("name") == null || row.get("name").isEmpty()) {
            errors.add("Отсутствует название города");
        } else {
            city.setName(row.get("name"));
        }

        // Coordinates
        Long x = null;
        Double y = null;

        try {
            x = Long.parseLong(row.get("x"));
        } catch (NumberFormatException | NullPointerException e) {
            errors.add("Некорректная координата X: " + row.get("x"));
        }

        try {
            y = Double.parseDouble(row.get("y"));
            if (y <= -659) {
                errors.add("Координата Y должна быть > -659");
            }
        } catch (NumberFormatException | NullPointerException e) {
            errors.add("Некорректная координата Y: " + row.get("y"));
        }

        if (x != null && y != null) {
            try {
                city.setCoordinates(new Coordinates(x, y));
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }

        // Creation Date
        try {
            city.setCreationDate(LocalDateTime.parse(row.get("creationDate")));
        } catch (DateTimeParseException | NullPointerException e) {
            errors.add("Некорректная дата создания: " + row.get("creationDate"));
        }

        // Area
        try {
            long area = Long.parseLong(row.get("area"));
            if (area <= 0) {
                errors.add("Площадь должна быть > 0");
            }
            city.setArea(area);
        } catch (NumberFormatException | NullPointerException e) {
            errors.add("Некорректная площадь: " + row.get("area"));
        }

        // Population
        try {
            long population = Long.parseLong(row.get("population"));
            if (population <= 0) {
                errors.add("Население должно быть > 0");
            }
            city.setPopulation(population);
        } catch (NumberFormatException | NullPointerException e) {
            errors.add("Некорректное население: " + row.get("population"));
        }

        // Meters Above Sea Level
        try {
            city.setMetersAboveSeaLevel(Float.parseFloat(row.get("metersAboveSeaLevel")));
        } catch (NumberFormatException | NullPointerException e) {
            errors.add("Некорректная высота над уровнем моря: " + row.get("metersAboveSeaLevel"));
        }

        // Establishment Date
        try {
            if (row.get("establishmentDate") != null || !row.get("establishmentDate").isEmpty()) {
                Date date = sdf.parse(row.get("establishmentDate"));
                city.setEstablishmentDate(date);
            }
        } catch (ParseException | NullPointerException | IllegalArgumentException e) {
            errors.add("Некорректная дата основания: " + row.get("establishmentDate"));
        }

        // Government
        try {
            if (row.get("government") != null || !row.get("government").isEmpty()) {
                city.setGovernment(Government.valueOf(row.get("government")));
            }
        } catch (IllegalArgumentException e) {
            errors.add("Недопустимое значение Government: " + row.get("government"));
        }

        // Standard of Living
        try {
            if (row.get("standardOfLiving") != null || !row.get("standardOfLiving").isEmpty()) {
                city.setStandardOfLiving(StandardOfLiving.valueOf(row.get("standardOfLiving")));
            }
        } catch (IllegalArgumentException e) {
            errors.add("Недопустимое значение StandardOfLiving: " + row.get("standardOfLiving"));
        }

        // Governor
        try {
            if (row.get("governor") != null || !row.get("governor").isEmpty()) {
                double height = Double.parseDouble(row.get("governor"));
                if (height <= 0d) {
                    errors.add("Рост губернатора должен быть > 0");
                }
                city.setGovernor(new Human(height));
            }
        } catch (NumberFormatException | NullPointerException e) {
            errors.add("Некорректный рост губернатора: " + row.get("governor"));
        }

        if (!errors.isEmpty()) {
            throw new IOException(String.join("; ", errors));
        }

        return city;
    }
}
