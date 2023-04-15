package main.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main mains = new Main();
        Map<String, Integer> map = new HashMap<>();
        Object[] readsCVS = mains.readCSVFile();
        mains.printLists(readsCVS);
        mains.printLists(mains.sortingLists(readsCVS, 1, -1));

        ConnectJDBC connectJDBC = new ConnectJDBC();
//        connectJDBC.createTableJDBC();
//        connectJDBC.insertIntoJDBC(connectJDBC.preparingInsert(readsCVS));
        connectJDBC.updateDataJDBC(new DataJDBC(5, "name", 123));
        connectJDBC.selectFromJDBC();

    }

    public Object[] sortingLists(Object[] forSorting, int type, int direct){
        List<String> printString = (List<String>) forSorting[0];
        List<Integer> printInteger = (List<Integer>) forSorting[1];
        SortData sortData = new SortData();

        if (type > 0){
            return sortData.sortA(printString, printInteger, direct);
        }
        else {
            return sortData.sortB(printString, printInteger, direct);
        }
    }

    public void printLists(Object[] print){
        List<String> sortedString = (List<String>) print[0];
        List<Integer> sortedInteger = (List<Integer>) print[1];

        for (int i = 0; i < sortedString.size(); i++) {
            System.out.println(sortedString.get(i)+" " + sortedInteger.get(i));
        }
        System.out.println();
    }

    public Map<String, Integer> makeMap(Object[] print){
        List<String> mapString = (List<String>) print[0];
        List<Integer> mapInteger = (List<Integer>) print[1];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < mapString.size(); i++) {
            map.put(mapString.get(i), mapInteger.get(i));
        }
        return map;
    }

    public Object[] readCSVFile() {
        String[] items = null;
        String line = "";
        String splitBy = ",";
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/qurba/OneDrive/Рабочий стол/task.csv"));

            while ((line = br.readLine()) != null){
                items = line.split(splitBy);
                stringList.add(items[0]);
                intList.add(Integer.parseInt(items[1]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Object[]{stringList, intList};
    }

}