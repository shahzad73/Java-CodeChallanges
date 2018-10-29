/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structure;


// A templated Java singly linked list
public class LinkListElement<T> 
{
    private LinkListElement<T> next;
    private T data;

    public LinkListElement( T value ) { data = value; }
    public LinkListElement<T> next() { return next; }
    public T value() { return data; }
    public void setNext( LinkListElement<T> elem ) { next = elem; }
    public void setValue( T value ) { data = value; }
}
