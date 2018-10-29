/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structure;


public abstract class LinkList<E>
{
    LinkListElement<E> head = null;
    int count = 0;
    
    
    public void PushOnTop(E data ){
        LinkListElement<E> l = new LinkListElement<E>( data );
        l.setNext( head );
        head = l;
        count++;
    }

    public E PopFromTop()
    {
        E elem;
        if(head != null)
        {
            elem = head.value();
            head = head.next();
            count--;
            return elem;
        }
        else
            return null;
    }    
    
    public LinkListElement<E> GetHeadElement() { return head; }
    
    
    public E FindElementInList(E elem)
    {
        LinkListElement<E> e = null;
        
        LinkListElement<E> current = head;
        while(current != null)
        {
            if (CompareElement(elem, current.value()) == true )
                return current.value();
            
            current = current.next();
        }
        
        return null;
    }
        
    abstract boolean CompareElement(E elem1, E elem2);
    
    

}


