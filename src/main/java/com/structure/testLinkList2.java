/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structure;

public class testLinkList2<E> extends LinkList<E>{

    @Override
    boolean CompareElement(E elem1, E elem2) {
        testLinkList2_Person p1 = (testLinkList2_Person)elem1;
        testLinkList2_Person p2 = (testLinkList2_Person)elem2;
        
        if(p1.getname() == p2.getname())
            return true;
        else
            return false;
    }
    
}
