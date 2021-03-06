package com.conglomo.datastructures.trees.binarytrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

public class BinTree {
    private final BinNode root;

    public BinTree(Comparable root) {
        this.root = new BinNodeImpl(root);
    }

    public void insert(Comparable element) {
        insert(root, new BinNodeImpl(element));
    }

    private void insert(BinNode root, BinNode newNode) {
        if (newNode.compareTo(root) < 0) {
            if (root.left() == null) {
                root.left(newNode);
                return;
            }
            insert(root.left(), newNode);
        } else {
            if (root.right() == null) {
                root.right(newNode);
                return;
            }
            insert(root.right(), newNode);
        }
    }

    public <T> void traverse(TraversalOrder order, T accum, BiFunction<BinNode, T, ?> func) {
        switch (order) {
            case INORDER:
                inOrder(root, accum, func);
                break;
            case PREORDER:
                preOrder(root, accum, func);
                break;
            case POSTORDER:
                postOrder(root, accum, func);
        }
    }

    public <T> void preOrder(BinNode root, T accum, BiFunction<BinNode, T, ?> func) {
        if (root == null) {
            return;
        }
        visit(root, accum, func);
        preOrder(root.left(), accum, func);
        preOrder(root.right(), accum, func);
    }

    public <T> void inOrder(BinNode root, T accum, BiFunction<BinNode, T, ?> func) {
        if (root == null) {
            return;
        }
        inOrder(root.left(), accum, func);
        visit(root, accum, func);
        inOrder(root.right(), accum, func);
    }

    public <T> void levelOrder() {
        levelOrder(Collections.singletonList(root), null, null);
    }

    public <T> void levelOrder(List<BinNode> nodes, T accum, BiFunction<BinNode, T, ?> func) {
        if (nodes.isEmpty()) {
            return;
        }
        List<BinNode> binNodes = new ArrayList<>();
        for (BinNode node : nodes) {
            if (node != null) {
                System.out.print(node.element() + " ");
                binNodes.add(node.left());
                binNodes.add(node.right());
            }
        }
        System.out.println();
        levelOrder(binNodes, null, null);
    }

    public <T> void postOrder(BinNode root, T accum, BiFunction<BinNode, T, ?> func) {
        if (root == null) {
            return;
        }
        postOrder(root.left(), accum, func);
        postOrder(root.right(), accum, func);
        visit(root, accum, func);
    }

    public <T> Object visit(BinNode node, T accum, BiFunction<BinNode, T, ?> func) {
        return func.apply(node, accum);
    }

    public static void main(String[] args) {
        BinTree tree = new BinTree(10);
        tree.insert(2);
        tree.insert(20);
        tree.insert(4);
        tree.insert(16);
        tree.insert(31);
        tree.insert(6);
//        System.out.println(tree.toString());
//        System.out.println(tree.size());
        tree.levelOrder();
        
    }

    public int size() {
        AtomicInteger size = new AtomicInteger(0);
        traverse(TraversalOrder.INORDER, size, (node, acc) -> {
            acc.incrementAndGet();
            return acc;
        });
        return size.get();
    }

    @Override
    public String toString() {
        StringBuilder accum = new StringBuilder("");
        traverse(TraversalOrder.INORDER, accum, (node, acc) -> acc.append(" -> ").append(node.element()));
        return accum.toString();
    }
}
