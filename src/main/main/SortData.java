package main.main;

import java.util.List;

public class SortData {

    public Object[] sortA(List<String> a, List<Integer> b, int direct){

        String a1;
        int b1;

        for (int i = 0; i < a.size()-1; i++) {
            for (int j = i; j < a.size(); j++) {

                if (a.get(i).compareTo(a.get(j)) > 0 && direct > 0){
                    b1 = b.get(i);
                    b.set(i, b.get(j));
                    b.set(j, b1);

                    a1 = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, a1);
                }
                else if (a.get(i).compareTo(a.get(j)) < 0 && direct < 0){
                    b1 = b.get(i);
                    b.set(i, b.get(j));
                    b.set(j, b1);

                    a1 = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, a1);
                }
            }
        }


        return new Object[]{a, b};
    }
    public Object[] sortB(List<String> a, List<Integer> b, int direct){

        String a1;
        int b1;

        for (int i = 0; i < b.size()-1; i++) {
            for (int j = i; j < b.size(); j++) {
                if (b.get(i)>b.get(j) && direct > 0){
                    b1 = b.get(i);
                    b.set(i, b.get(j));
                    b.set(j, b1);

                    a1 = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, a1);
                } else if (b.get(i)<b.get(j) && direct < 0) {
                    b1 = b.get(i);
                    b.set(i, b.get(j));
                    b.set(j, b1);

                    a1 = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, a1);
                }
            }
        }



        return new Object[]{a, b};
    }

}
