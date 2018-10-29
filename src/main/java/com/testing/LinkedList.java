package com.testing;

import com.structure.LinkList;
import com.structure.testLinkList1;
import com.structure.testLinkList2;
import com.structure.testLinkList2_Person;

public class LinkedList {

    public static void main(String[] args)
    {
        testLinkList1<String> lst = new testLinkList1<String>();
        
        lst.PushOnTop("Hello");
        lst.PushOnTop("Word");
        lst.PushOnTop("Allah");
        String sss = lst.FindElementInList("Word");
        System.out.println("We found element - " + sss);
        
        sss = lst.PopFromTop();
        while (sss != null)
        {
            System.out.println("POP - " + sss);
            sss = lst.PopFromTop();
        }
        
        
        testLinkList2<testLinkList2_Person> lst2 = new testLinkList2<testLinkList2_Person>();
        testLinkList2_Person ppp;
        
        lst2.PushOnTop(new testLinkList2_Person(1, "aaa"));
        lst2.PushOnTop(new testLinkList2_Person(11, "bbb"));
        lst2.PushOnTop(new testLinkList2_Person(22, "shahzad"));
        lst2.PushOnTop(new testLinkList2_Person(55, "aslam"));
        
        ppp = new testLinkList2_Person(0, "shahzad");
        
        testLinkList2_Person p2 = lst2.FindElementInList(ppp);
        
        System.out.println("Found shahzad object " + p2.getid());
        
        ppp = lst2.PopFromTop();
        while (ppp != null)
        {
            System.out.println("POP - " + ppp.getid() + " " + ppp.getname() );
            ppp = lst2.PopFromTop();
        }
        
    }
     
}


