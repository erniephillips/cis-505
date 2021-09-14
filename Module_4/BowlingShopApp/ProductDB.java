/*
    Author: Ernest Phillips III
    Date: 09/14/2021
    Purpose: ProductDB: acts like a DB class, creates static object lists of the inherited class Product
*/
public class ProductDB<T> {
  GenericQueue<Product> products;
  public static GenericQueue<Product> getProducts(String code) {
    if (code.toLowerCase().equals("b")) { //code is calling for ball
      // GenericQueue<Product> balls = new GenericQueue<Product>(){{
      //   new Ball(){{ setCode("B100"); setDescription("Black Widow 2.0"); setPrice(144.95); setColor("Black and red"); }};
      //   new Ball(){{ setCode("B101"); setDescription("Black Widow 1.0"); setPrice(104.95); setColor("Black"); }};
      //   new Ball(){{ setCode("B102"); setDescription("Green Hornet"); setPrice(99.95); setColor("Black and green"); }};
      //   new Ball(){{ setCode("B103"); setDescription("Silver Deer"); setPrice(184.95); setColor("Purple and silver"); }};
      //   new Ball(){{ setCode("B104"); setDescription("Blue Bolt"); setPrice(129.95); setColor("Blue"); }};
      // }};
      Ball ball1 = new Ball(){{ setCode("B100"); setDescription("Black Widow 2.0"); setPrice(144.95); setColor("Black and red"); }};
      Ball ball2 = new Ball(){{ setCode("B101"); setDescription("Black Widow 1.0"); setPrice(104.95); setColor("Black"); }};
      Ball ball3 = new Ball(){{ setCode("B102"); setDescription("Green Hornet"); setPrice(99.95); setColor("Black and green"); }};
      Ball ball4 = new Ball(){{ setCode("B103"); setDescription("Silver Deer"); setPrice(184.95); setColor("Purple and silver"); }};
      Ball ball5 = new Ball(){{ setCode("B104"); setDescription("Blue Bolt"); setPrice(129.95); setColor("Blue"); }};
      GenericQueue<Product> balls = new GenericQueue<>();
      balls.enqueue(ball1);
      balls.enqueue(ball2);
      balls.enqueue(ball3);
      balls.enqueue(ball4);
      balls.enqueue(ball5);
      return balls;
    } else if (code.toLowerCase().equals("s")) { //code is calling for new shoes
      Shoe shoe1 = new Shoe(){{ setCode("S100"); setDescription("Size eight and a half shoes"); setPrice(11.95); setSize(8.5); }};
      Shoe shoe2 = new Shoe(){{ setCode("S101"); setDescription("Size nine shoes"); setPrice(11.95); setSize(9); }};
      Shoe shoe3 = new Shoe(){{ setCode("S102"); setDescription("Size ten shoes"); setPrice(11.95); setSize(10); }};
      Shoe shoe4 = new Shoe(){{ setCode("S103"); setDescription("Size eleven shoes"); setPrice(11.95); setSize(11); }};
      Shoe shoe5 = new Shoe(){{ setCode("S104"); setDescription("Size twelve shoes"); setPrice(11.95); setSize(12); }};
      GenericQueue<Product> shoes = new GenericQueue<>();
      shoes.enqueue(shoe1);
      shoes.enqueue(shoe2);
      shoes.enqueue(shoe3);
      shoes.enqueue(shoe4);
      shoes.enqueue(shoe5);
      return shoes;
    } else if (code.toLowerCase().equals("a")) { //code is calling for new bags
      Bag bag1 = new Bag(){{ setCode("A97"); setDescription("Small bag, holds one bowling ball"); setPrice(8.95); setType("1"); }};
      Bag bag2 = new Bag(){{ setCode("A98"); setDescription("Medium bag, holds twp bowling balls"); setPrice(8.95); setType("2"); }};
      Bag bag3 = new Bag(){{ setCode("A99"); setDescription("Large bag, holds three bowling balls"); setPrice(8.95); setType("3"); }};
      GenericQueue<Product> bags = new GenericQueue<>();
      bags.enqueue(bag1);
      bags.enqueue(bag2);
      bags.enqueue(bag3);
      return bags;
    }
    return new GenericQueue<>(); //using type inference, meaning we do not neet to explicity state <Product> since it's implied in the variable declaration
  }
}
