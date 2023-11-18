import com.engeto.plant.Plant;
import com.engeto.plant.PlantException;
import com.engeto.plant.PlantList;
import com.engeto.plant.PlantListManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;

public class Main {

    public static String KVETINY2_UMISTENI = "resources/kvetiny2.txt";
    public static void main(String[] args) throws PlantException, IOException {
        PlantListManager manager = new PlantListManager();
        PlantList list = new PlantList();

        //debug
        list.loadFile("resources/kvetiny.txt");
        System.out.println("Seznam květin: " + list.toString());

        list.addPlant(new Plant("Test"));
        list.saveToFile(KVETINY2_UMISTENI);
        PlantList list2 = new PlantList();
        list2.loadFile(KVETINY2_UMISTENI);
        list2.getPlants().sort(new Comparator<Plant>() {
            @Override
            public int compare(Plant p1, Plant p2) {
                String s1 = p1.getName();
                String s2 = p2.getName();
                return s1.toLowerCase().compareTo(s2.toLowerCase());
            }
        });
        System.out.println("Seřazení podle názvu: " + list2.toString());

        list2.getPlants().sort(new Comparator<Plant>() {
            @Override
            public int compare(Plant p1, Plant p2) {
                LocalDate s1 = p1.getWatering();
                LocalDate s2 = p2.getWatering();
                return s1.compareTo(s2);
            }
        });
        System.out.println("Seřazení podle data poslední zálivky: "+ list2.toString());

        manager.addList(list);




        System.out.println("-----------------------------------");
        PlantList listFail = new PlantList();
        listFail.loadFile("resources/kvetiny-spatne-datum.txt");
        System.out.println(listFail.toString());


        System.out.println("-----------------------------------");
        PlantList listFail2 = new PlantList();
        listFail2.loadFile("resources/kvetiny-spatne-frekvence.txt");
        System.out.println(listFail2.toString());
    }
}