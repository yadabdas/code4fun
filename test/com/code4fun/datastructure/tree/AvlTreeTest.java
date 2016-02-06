package com.code4fun.datastructure.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yadab on 2/6/2016.
 */
public class AvlTreeTest {
    private AvlTree avlTree;

    @Before
    public void setup(){
        avlTree = new AvlTree();
    }

    @Test
    public void testSize() throws Exception {
        for(int i=0;i<10;i++){
            avlTree.insert(i);
        }
        Assert.assertEquals(avlTree.getSize(), 10);
    }

    @Test
    public void testGetBalancedFactor() throws Exception {
        for(int i=0;i<10;i++){
            avlTree.insert(i);
        }
        Assert.assertTrue(Math.abs(avlTree.getBalancedFactor()) <= 1);
    }
}