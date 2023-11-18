package com.engeto.plant;

import java.time.LocalDate;
import java.util.Objects;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public Plant(String name, LocalDate planted, int frequencyOfWatering) {
        this(name, " ", planted, LocalDate.now(), frequencyOfWatering);
    }
    public Plant(String name) {
        this(name, " ", LocalDate.now(), LocalDate.now(), 7);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if (planted != null) {
            if (watering.toEpochDay() - planted.toEpochDay() < 0) {
                throw new PlantException("Datum poslední zálivky nesmí být starší než datum zasazení: " +watering);
            }
        }

        this.watering = watering;
    }

    public int getFrequencyOfWatering() {

        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Zálivka nesmí být 0 nebo záporné číslo: " + frequencyOfWatering);
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public LocalDate dalsiDoporucenaZalivka () {
        return watering.plusDays(frequencyOfWatering);

    }

    public String getWateringInfo() {
        return name + " Datum poslední zálivky: " + watering +
                " Datum dopoučené další zálivky: " + dalsiDoporucenaZalivka();
    }

    @Override
    public String toString() {
        return name + " " +
                notes + " " +
                planted +
                " " + watering +
                " " + frequencyOfWatering;
    }

}
