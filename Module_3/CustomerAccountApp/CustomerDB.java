/*
    Author: Ernest Phillips III
    Date: 09/04/2021
    Purpose: Create a static method to return stubbed data, similating as if it were a DB call
*/

public class CustomerDB {
  public static Customer getCustomer(Integer id){ //set method with ret type of Customer object
    if(id == 1007){ //match cases and return made up object depending on case
      return new Customer("Ernie", "ABC Street", "ABCCity", "New Jersey");
    }else if(id == 1008){
      return new Customer("Katie", "DEF Street", "DEFCity", "New Jersey");
    }else if(id == 1009){
      return new Customer("Emily", "HIJ Street", "HIJCity", "New Jersey");
    }else{
      return new Customer("Kelsey", "KLM Street", "KLMCity", "New Jersey");
    }
  }
}
