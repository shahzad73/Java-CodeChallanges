/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

public class linkedlist {
     
    LinedListNode head = null;
    
    public void append(String val) {
        LinedListNode lastNode = getLastNode();
        if (lastNode == null) {
            head = new LinedListNode(val);
        } else {
            lastNode.next = new LinedListNode(val);
        }
    }

    public void replace(String val, int position) {      //position is 1 based that means first item is at position 1
        LinedListNode newNode = new LinedListNode(val);

        if(head == null)
            return;
        
        int CurrentNodeNo = 1;
        LinedListNode prevNode = null;
        LinedListNode currNode = head;
        while (CurrentNodeNo != position) {
            prevNode = currNode;
            currNode = currNode.next;
            CurrentNodeNo++;
        }
        newNode.next = currNode.next;
        if(CurrentNodeNo == 1)
        {
            newNode.next = head.next;
            head = newNode;
        }
        else   
        {
            newNode.next = currNode.next; 
            prevNode.next = newNode;
        }
    }

    
    public void removeNode(int position)
    {
        if(head == null)
            return;
        
        if(position == 1)
        {
            head = head.next;      //if head needs to be removed
            return;
        }
        
        int CurrentNodeNo = 1;
        LinedListNode prevNode = null;
        LinedListNode currNode = head;
        while (CurrentNodeNo != position) {
            prevNode = currNode;
            currNode = currNode.next;
            CurrentNodeNo++;
        }
        prevNode.next = currNode.next;
    }
    
    
    public void print() {
        System.out.println("");
        if(head == null){
            System.out.print("EMPTY");
            return;
        }
        LinedListNode tmpNode = head;
        while (tmpNode != null) {
            System.out.println(tmpNode.value);
            tmpNode = tmpNode.next;
        }
    }

    
    private LinedListNode getLastNode() {
        if (head == null) {
            return null;
        }
        LinedListNode tmpNode = head;
        while (tmpNode.next != null) {
            tmpNode = tmpNode.next;
        }
        return tmpNode;
    }
    
}



class LinedListNode {
    public LinedListNode next;
    String value;
    public LinedListNode(String val) {
        value = val;
        next = null;
    }
}






