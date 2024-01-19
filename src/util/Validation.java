package util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Created By Ravindu Prathibha
 * @created 1/19/2024 - 3:56 PM
 * @project Validation
 */
public class Validation {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");


        for (Object o : arrayList) {
            System.out.println(o);
        }

        HashMap hashMap = new HashMap();
        hashMap.put("IJSE","Institute Of Software Engineering");
        hashMap.put("SE","Software Engineer");

        Object ijse = hashMap.get("IJSE");
        System.out.println(ijse);

        for (Object o : hashMap.keySet()) {
            System.out.println(o);
        }

        for (Object key : hashMap.keySet()) {
            Object values = hashMap.get(key);
            System.out.println(values);
        }

        for (Object key : hashMap.keySet()) {
            Object values = hashMap.get(key);
            System.out.println(key+" : "+values);
        }
    }


}
