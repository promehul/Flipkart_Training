
package learnJava;

import java.util.ArrayList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;

// Generic Class
class Entry<K, V> {
    private K key;
    private V value;
    
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() { return key; }
    public V getValue() { return value; }
}

// Compiler Will Generate Following
//  For Above Generic Class
// Every K and V is replaced by Object Type(Raw Type)

class Entry { 
    private Object key; 
    private Object value;
    public Entry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() { return key; }
    public Object getValue() { return value; } 

    // Bridge Methods or Wrapper Methods
    public Object getKey() { (String) getKey(); }
    public Object getValue() { return (Integer) getValue(); } 
}

  
// If a type variable has bounds, 
// it is replaced with the first bound.
// Suppose we declare the Entry class as

    public class Entry<K extends Comparable<? super K> & Serializable,
                      V extends Serializable>

// Type Eraser: Then it is erased to a class

class Entry {
    private Comparable key; 
    private Serializable value; ...
}

Entry<String, Integer> entry = ...;
String key = entry.getKey();
String key = (String) entry.getKey();


 // Wrapper Method: bridge methods 

//___________________________________________________________________

class ArrayUtil {
    // Generic Function
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> T[] swap(int i, int j, T... values) {
        T temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        return values;
    }
}

//___________________________________________________________________

class Closeables {
    // Generic Type Bounds
    public static <T extends AutoCloseable> void closeAll(ArrayList<T> elems) throws Exception {
        for (T elem : elems) elem.close();
    }
}

//___________________________________________________________________
// import java.util.function.Predicate;

class ArrayUtilOnceMore {
    public static <T> void printAll(T[] elements, Predicate<? super T> filter) {
        for (T e : elements) 
            if (filter.test(e))
                System.out.println(e.toString());
    }
}

//___________________________________________________________________
// import java.util.ArrayList;
// import java.util.function.Predicate;

class Employee {
    private String name;
    private double salary;
        
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;    
    }
    
    public final String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
}

class Manager extends Employee {
    private double bonus;
    
    public Manager(String name, double salary) {
        super(name, salary);
        bonus = 0;
    }
    
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    
    public double getSalary() { // Overrides superclass method
        return super.getSalary() + bonus;
    }
} 

// Java Framework Predicate Definition
// interface Predicate<T> {
//     boolean test(T value);
// }

class Employees {
    // ArrayList<Employee>
    //        Can Store Only Employee Type Objects
    
    // ArrayList<? extends Employee>
    //        Can Store Only Employee Type Objects
    //        As Well As Objects of Subtype of Employee

    public static void printNames(ArrayList<? extends Employee> staff) {
        for (int i = 0; i < staff.size(); i++) {
            Employee e = staff.get(i);
            System.out.println(e.getName());
        }
    }
    
    // Employee[] Will Also Help In Storing All Kinds of Employee
    public static void printAll1(Employee[] staff, Predicate<Employee> filter) {

        // Java Framework Predicate Definition
        // interface Predicate<T> {
        //     boolean test(T value);
        // }

        // Lambda Expression
        // e -> e.getSalary() > 100000
        // Compiler Will Generate Following Code
            // class EmployeeTemp implements Predicate<Employee> {
            //     boolean test(Employee e) {
            //         return e.getSalary() > 100000;
            //     }
            // }


        // Filter Employees Based On : e -> e.getSalary() > 100000
        for (Employee e : staff) 
            if (filter.test(e))
                System.out.println(e.getName());
    }

    public static void printAll2(Employee[] staff, Predicate<? super Employee> filter) {

        // Java Framework Predicate Definition
        // interface Predicate<T> {
        //     boolean test(T value);
        // }

        for (Employee e : staff) 
            if (filter.test(e))
                System.out.println(e.getName());
    }    
}

//___________________________________________________________________


// public static <T> boolean hasNulls(ArrayList<T> elements) {
//        for (Object e : elements) {
//        }   if (e == null) return true;
//        return false;
// }

// //Unbounded Wildcard
// public static boolean hasNulls(ArrayList<?> elements) {
//        for (Object e : elements) {
//        }   if (e == null) return true;
//        return false;
// }

// import java.util.List;

class Lists {
    public static boolean hasNulls(List<?> elements) {
        for (Object e : elements) {
            if (e == null) return true;
        }
        return false;
    }

    public static void swap(List<?> elements, int i, int j) {
        swapHelper(elements, i, j);
    }
    
    private static <T> void swapHelper(List<T> elements, int i, int j) {
        T temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }
}

//___________________________________________________________________
//___________________________________________________________________


public class Generics {
    public static void playWithArrayUtil() {
        String[] friends = { "Peter", "Paul", "Mary" };
        
        for( String friend: friends) System.out.println(friend);
        ArrayUtil.swap(friends, 0, 1);
        for( String friend: friends) System.out.println(friend);

        // Uncomment to see error message
        // Double[] result = Arrays.swap(0, 1, 1.5, 2, 3);
    }

    public static void playWithClosable() throws Exception {
        PrintStream p1 = new PrintStream("/tmp/1");
        PrintStream p2 = new PrintStream("/tmp/2");
        ArrayList<PrintStream> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        Closeables.closeAll(ps);        
        
        // ArrayList<String> strings = new ArrayList<>();
        // strings.add("Ding");
        // strings.add("Donng");
        // Closeables.closeAll(strings);        
    }

    public static void playWithEmployees() {
        Employee[] employees = {
          new Employee("Fred", 50000),
          new Employee("Wilma", 60000),
          new Employee("Gabaar Singh", 5000000),
          new Employee("Sambha", 6000000),
          new Employee("Thakur", 6000000)
        };
        
        Employees.printAll1(employees, e -> e.getSalary() > 100000);
        Employees.printAll2(employees, e -> e.getSalary() > 100000);
        
        Predicate<Object> evenLength = e -> e.toString().length() % 2 == 0; 
        // Employees.printAll1(employees, evenLength);
        Employees.printAll2(employees, evenLength);
    }

    public static void main(String[] args) {
    	
    	System.out.println("\nFunction: playWithArrayUtil");
    	playWithArrayUtil();

    	System.out.println("\nFunction: playWithClosable");
            try { playWithClosable(); } 
            catch(Exception ex) {  }

    	System.out.println("\nFunction: playWithEmployees");
        playWithEmployees();

  //   	System.out.println("\nFunction: ");
  //   	System.out.println("\nFunction: ");
  //   	System.out.println("\nFunction: ");
  //   	System.out.println("\nFunction: ");
    }
}

