/*
    Author: Ernest Phillips III
    Date: 09/21/2021
    Purpose: Generic interface that can be implemented by other interfaces or classes passing generic objects or primitive type variables
*/

import java.util.List;

public interface GenericDao<E, K> {
  List<E> findAll(); //find all records
  E findBy(K key); //find a record by it's specific key (likely a string or int, but not limited to this)
  void insert(E entity);//insert a new record of said entity
}
