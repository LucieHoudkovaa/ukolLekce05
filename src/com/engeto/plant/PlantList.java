package com.engeto.plant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PlantList {
    List<Plant> plants = new ArrayList<>();

    public List<Plant> getPlants() {
        return plants;
    }

    public void loadFile(String fileLocation) throws PlantException {
        File file = new File(fileLocation);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new PlantException("File not found: " + fileLocation);
        }
        try {
            while (scanner.hasNextLine()) {
                String plantName = "";
                String plantDescription = "";
                int wateringFrequency = 0;
                LocalDate datePlanted = LocalDate.now();
                LocalDate dateNextWatering = LocalDate.now();
                String record = scanner.nextLine();
                String[] splits = record.split("\t");
                for (int i = 0; i < splits.length; i++) {
                    switch(i) {
                        case(0):
                            plantName = splits[i];
                            break;
                        case(1):
                            plantDescription = splits[i];
                            break;
                        case(2):
                            wateringFrequency = Integer.parseInt(splits[i]);
                            break;
                        case(3):
                            datePlanted = LocalDate.parse(splits[i]);
                            break;
                        case(4):
                            dateNextWatering = LocalDate.parse(splits[i]);
                            break;
                    }
                }
                Plant plant = new Plant(plantName, plantDescription, datePlanted, dateNextWatering, wateringFrequency);
                plants.add(plant);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Datum nelze načíst: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Frekvence zálivky je špatně zapsaná: " + e.getMessage());
        }
    }

    public void saveToFile(String fileLocation) throws IOException {
        String output = "";
        for(Plant plant: plants) {
            output = output + plant.getName() + "\t" + plant.getNotes() + "\t" + plant.getFrequencyOfWatering()
                    + "\t" + plant.getPlanted() + "\t" + plant.getWatering() + "\n";
        }
        FileWriter myWriter = new FileWriter(fileLocation);
        myWriter.write(output);
        myWriter.close();
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public Plant getPlant(int index) {
        return plants.get(index);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public void removePlant(int index) {
        plants.remove(index);
    }

    @Override
    public String toString() {
        return plants.toString();

    }
}
