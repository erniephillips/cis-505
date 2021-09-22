/*
    Author: Ernest Phillips III
    Date: 09/21/2021
    Purpose: Create a list of composers in memory with implemented functions for manipulation of list
*/

import java.util.ArrayList;
import java.util.List;

public class MemComposerDao implements ComposerDao {
  private List<Composer> composers = new ArrayList<Composer>(); //list is an interface and cannot be instantiated; therefore, must ref ArrayList

  public MemComposerDao() {
    composers.add(new Composer(){{ setId(1); setName("Nirvana"); setGenre("Grunge");}});
    composers.add(new Composer(){{ setId(2); setName("Neil Young"); setGenre("Classic Rock");}});
    composers.add(new Composer(){{ setId(3); setName("The Rolling Stones"); setGenre("Classic Rock");}});
    composers.add(new Composer(){{ setId(4); setName("Brooks & Dunn"); setGenre("Country");}});
    composers.add(new Composer(){{ setId(5); setName("Seas of Years"); setGenre("Post-Rock");}});   
  }

  //overridden methods from GenericDAO. Being that this is an interface, ALL methods must be implemented hence key term "implements" after class name
  @Override
  public List<Composer> findAll() {
    return this.composers; //return all
  }

  @Override
  public Composer findBy(Integer key) {
    //run a stream on the current list using lambda to exploit the id property and match to passed in key returning first result, or a null value if not found
    return composers.stream().filter(x -> x.getId() == key).findFirst().orElse(null); //return specific composer by the passed key
  }

  @Override
  public void insert(Composer entity) {
    composers.add(entity); //add a composer to the list
  }
  
}
