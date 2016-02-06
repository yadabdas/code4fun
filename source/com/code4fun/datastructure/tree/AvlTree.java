package com.code4fun.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by yadab on 9/12/2015.
 * https://en.wikipedia.org/wiki/AVL_tree
 */
public class AvlTree {
    private int size;
    private Node root;
    public int getSize() {
        return size;
    }
    public void insert(int data)
    {
        root = this.insert(data, root);
    }
    public int getBalancedFactor(){
        return root.balanceFactor();
    }
    private Node insert(int data, Node node){
        if(node == null){
            node = new Node();
            node.height = 0;
            node.data = data;
            size +=1;
            return node;
        }
        // do nothing, it is redundant insert
        if(node.data == data){
            return node;
        }
        // usual bst insert
        if(data > node.data){
            node.right = insert(data,node.right);
        }else {
            node.left = insert(data, node.left);
        }
        // update height after insertion
        node.updateHeight();
        // balance
        // Case : left heavy
        if(node.balanceFactor()> 1){
            if(node.left.data < data){
                //perform left rotate then rotate right
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }else {
                // perform right rotate
                node = rotateRight(node);
            }
        }
        // Case : right heavy
        if(node.balanceFactor() < -1){
            if(node.right.data > data){
                // perform right rotation and then left rotation
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }else {
                // perform left rotation
                node = rotateLeft(node);
            }
        }
        return node;
    }

    private int getWidth(Node node){
        if(node ==null){
            return 0;
        }
        return 1 + getWidth(node.right) + getWidth(node.left);
    }

    private Node rotateRight( Node node){
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        node.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    private Node rotateLeft( Node node )
    {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        node.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }
    private static class Node{
        int data;
        int height;
        Node left;
        Node right;
        @Override
        public String toString() {
            return String.valueOf(data)+"(BF="+balanceFactor()+")";
        }
        public int balanceFactor(){
            return getHeight(left) - getHeight(right);
        }
        private int getHeight(Node node){
            if(node == null) return -1;
            return node.height;
        }
        public void updateHeight() {
            height = Math.max( getHeight(left), getHeight(right) ) + 1;
        }
    }
}

