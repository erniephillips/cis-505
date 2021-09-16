/*
    Author: Ernest Phillips III
    Date: 09/14/2021
    Purpose: GenericQueue: accepts any object type, creates a list of that item and allows manipulation of list and returns size
*/
import java.util.LinkedList;

public class GenericQueue<T> {
  private LinkedList<T> list = new LinkedList<>(); //must declare new instance of list or will get a NullPointerException

  //assignment later asks for a while loop to show the list of objects
  //we need a method to return the list which can either be set here where it makes the most since, 
  //or directly in the ProductDB, the getProducts if else would need to return each item creation as a list, but it is not yet in a list there, so the list return should happen here.
  //nevermind, I can return a GenericQueue of the instantiate Product from the ProductDB class.
  // public LinkedList<T> getList() { 
  //   return this.list;
  // }

  public void enqueue(T item){ //add parameter generic type item to generic list
    list.addFirst(item);
  }

  public T dequeue(){ //remove the first item from generic list
    return list.removeFirst(); //return the first removed item
  }

  public int size(){ //return the list size
    return list.size();
  }

  
}
