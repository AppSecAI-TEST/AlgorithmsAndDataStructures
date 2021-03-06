package com.conglomo.datastructures.trees.binarytrees;

import java.util.Arrays;
import java.util.function.Function;

public class ArrayBackedBinTree {

    private Comparable[] data = new Comparable[3];

    public ArrayBackedBinTree(Comparable root) {
        data[0] = root;
    }

    public static void main(String[] args) {
        ArrayBackedBinTree tree = new ArrayBackedBinTree(10);
        tree.insert(2);
        tree.insert(20);
        tree.insert(4);
//        tree.traverse(null, null);
        tree.levelOrder(0);
    }

    public void insert(Comparable element) {
        insert(0, element);
    }

    private void insert(int idx, Comparable element) {
        if (idx >= data.length) {
            data = Arrays.copyOf(data, data.length << 1);
        }
        if (data[idx] == null) {
            data[idx] = element;
            return;
        }
        if (element.compareTo(data[idx]) < 0) {
            insert(2 * idx + 1, element);
        } else {
            insert(2 * idx + 2, element);
        }
    }

    public void traverse(TraversalOrder order, Function<Comparable, ?> func) {
        inOrder(0);
    }

    private void inOrder(int idx) {
        if (idx >= data.length || data[idx] == null) {
            return;
        }
        inOrder(2 * idx + 1);
        visit(data, idx);
        inOrder(2 * idx + 2);
    }

   private void levelOrder(int... idx) {
       int level = (int) (Math.log(idx.length) / Math.log(2));
       int[] ints = new int[(int) Math.pow(2, level + 1)];
       for (int i = 0, j = 0; j < ints.length / 2; i = i + 2, j++) {
           if (idx[j] >= data.length) {
               return;
           }
           System.out.println(data[idx[j]]);
           ints[i] = 2 * (level + j) + 1;
           ints[i + 1] = 2 * (level + j) + 2;
       }
       levelOrder(ints);
    }

    private void visit(Comparable[] data, int idx) {
        System.out.println(data[idx]);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
