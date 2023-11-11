Go to https://codebunk.com/

Past to following code:

```java
import java.util.*;
import java.util.stream.*;

public class Main {
  public static void main(String[] args) {
    List<Customer> customers = new ArrayList<>();
    customers.add(new Customer("B", 23));
    customers.add(new Customer("A", 21));
    customers.add(new Customer("F", 24));
    customers.add(new Customer("D", 25));
    customers.add(new Customer("C", null));
    customers.add(null);
    customers.add(new Customer(null, 22));
    // TODO: write the code to sort customers by age
    System.out.println(customers);
  }
}
class Customer {
    private String name;
    private Integer age;
    Customer(String n, Integer a) {
         name = n;
         age = a;
    }
    public String toString() {
            return "\n[name=" + name + ", age=" + age + "]\n";
    }
}
```