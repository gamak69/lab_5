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
        city.setId(Long.parseLong(row.get("id")));
        city.setName(row.get("name"));

        Coordinates coords = new Coordinates(
                Long.parseLong(row.get("x")),
                Double.parseDouble(row.get("y"))
        );

        city.setCoordinates(coords);
        city.setCreationDate(LocalDateTime.parse(row.get("creationDate"))); // TODO здесь хуйня может быть
        city.setArea(Long.valueOf(row.get("area")));
        city.setPopulation(Long.valueOf(row.get("population")));
        city.setMetersAboveSeaLevel(Float.valueOf(row.get("metersAboveSeaLevel")));

        //Если establishmentDate может быть null или пустым — обработай это
        try {
            Date establishmentDate = sdf.parse(row.get("establishmentDate"));
            city.setEstablishmentDate(establishmentDate);
        } catch (ParseException e) {
            throw new IOException("Failed to parse establishmentDate: " + row.get("establishmentDate"), e);
        }

        // Enum'ы government и standardOfLiving — проверяй на null или пустую строку
        String governmentStr = row.get("government");
        if (governmentStr != null && !governmentStr.isEmpty()) {
            city.setGovernment(Government.valueOf(governmentStr));
        } else {
            city.setGovernment(null);
        }

        String standardOfLivingStr = row.get("standardOfLiving");
        if (standardOfLivingStr != null && !standardOfLivingStr.isEmpty()) {
            city.setStandardOfLiving(StandardOfLiving.valueOf(standardOfLivingStr));
        } else {
            city.setStandardOfLiving(null);
        }

        city.setGovernor(new Human(Double.valueOf(row.get("governor"))));

        return city;
    }
}
