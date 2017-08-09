package com.conglomo.datastructures.trees.binarytrees;

import java.util.Comparator;
import java.util.Objects;

class BinNodeImpl implements BinNode {
    private Comparable element;
    BinNode left;
    BinNode right;

    BinNodeImpl(Comparable cargo) {
        element = cargo;
    }

    @Override
    public Comparable element() {
        return element;
    }

    @Override
    public void setElement(Comparable element) {
        this.element = element;
    }

    @Override
    public BinNode left() {
        return left;
    }

    @Override
    public BinNode right() {
        return right;
    }

    @Override
    public void left(BinNode left) {
        this.left = left;
    }

    @Override
    public void right(BinNode right) {
        this.right = right;
    }

    @Override
    public boolean isLeaf() {
        return right() == null && left() == null;
    }


    @Override
    public int compareTo(BinNode o) {
        return Objects.compare(element, o.element(), Comparator.naturalOrder());
    }
}
