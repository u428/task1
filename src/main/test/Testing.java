package main.test;

import main.main.Main;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Testing {

    @Test
    public void tests(){
        Main mains = new Main();
        Map<String, Integer> map = new HashMap<>();
        Object[] readsCVS = mains.readCSVFile();
        mains.printLists(mains.sortingLists(readsCVS, 1, -1));
        mains.printLists(mains.sortingLists(readsCVS, -1, -1));


    }

}
