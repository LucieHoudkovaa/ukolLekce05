package com.engeto.plant;

import java.util.ArrayList;
import java.util.List;

public class PlantListManager {
    private List<PlantList> plantLists = new ArrayList<>();

    public void addList(PlantList list) {
        plantLists.add(list);
    }
}
