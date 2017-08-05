package com.conglomo.datastructures.lists;

public class LListDriver {

    public static void main(String[] args) {
        LList lList = new LList(null);
        System.out.println(lList);
        lList.add(1);
        System.out.println(lList.contains(1));
        System.out.println(lList);
        lList.add(2);
        System.out.println(lList);
        lList.remove(1);
        System.out.println(lList);
        lList.add(3);
        System.out.println(lList);
        lList.remove(3);
        System.out.println(lList);
        lList.remove(2);
        System.out.println(lList);
        System.out.println(lList.remove(1));
        System.out.println(lList);
    }
}
