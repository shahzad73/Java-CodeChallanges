/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structure;


//Found following error when mlst was declared in another package.
//Inherited abstract methods are not accessible and could not be implemented
//on internet the say this is error in Java or NetBeans
public class testLinkList1<E> extends LinkList<E>
{
    boolean CompareElement(E elem1, E elem2) {
        String s1 = (String)elem1;
        String s2 = (String)elem2;
        
        if(s1 == s2)
            return true;
        else
            return false;
    }
}

