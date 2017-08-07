package com.conglomo.datastructures.trees.binarytrees;

import java.util.function.Function;

public interface BinNode extends Comparable<BinNode> {
    Comparable element();

    void setElement(Comparable element);

    BinNode left();

    BinNode right();

    void left(BinNode element);

    void right(BinNode element);

    boolean isLeaf();

}
