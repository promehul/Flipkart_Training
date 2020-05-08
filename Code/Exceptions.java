
//___________________________________________________________________

package ch05.sec01;

public class ThrowDemo {
    public static int randInt(int low, int high) {
        if (low > high)
            throw new IllegalArgumentException(
                "low should be <= high but low is "
                + low + " and high is " + high);

        return low + (int)(Math.random() * (high - low + 1));
    }
    
    public static void main(String[] args) {
        System.out.println("Generating a random number...");
        int r = randInt(10, 20);
        System.out.println("r = " + r);
        System.out.println("And another...");
        r = randInt(10, 5);
        System.out.println("r = " + r);
    }
}

//___________________________________________________________________

package ch05.sec01;

import java.io.IOException;

public class FileFormatException extends IOException {
    public FileFormatException() {}
    public FileFormatException(String message) {
        super(message);
    }
}

//___________________________________________________________________

package ch05.sec01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class TryWithResourcesDemo {
    public static void print(Scanner in, PrintWriter out) {
        try (in; out) { // Effectively final variables
            while (in.hasNext())
                out.println(in.next().toLowerCase());            
        }
    }
    
    public static void main(String[] args) throws IOException {
        List<String> lines = List.of("Mary had a little lamb. Its fleece was white as snow.".split(" "));
        
        try (PrintWriter out = new PrintWriter("/tmp/output1.txt") ) {
            for (String line : lines) {
                out.println(line.toLowerCase());
            }
        }
        try (Scanner in = new Scanner(Paths.get("/usr/share/dict/words"));
                PrintWriter out = new PrintWriter("/tmp/output2.txt")) {
            while (in.hasNext())
                out.println(in.next().toLowerCase());
        }
        
        PrintWriter out3 = new PrintWriter("/tmp/output3.txt");
        try (out3) {
            for (String line : lines) {
                out3.println(line.toLowerCase());
            }
        }                
    }
}

//___________________________________________________________________


package ch05.sec01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryFinallyDemo {
    public static Lock myLock = new ReentrantLock();
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException{
        Thread[] threads = new Thread[2];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    for (int k = 1; k <= 1000; k++) {
                        myLock.lock();
                        try {
                            counter++;
                            Thread.sleep(1);
                        } finally {
                            myLock.unlock();
                        }
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
        }
        for (Thread t : threads) t.start();
        
        // Try this: (1) Uncomment the line below
        // threads[0].interrupt();
        // Run the program. What happens? Why?
        // (2) Now comment out the lines try {, } finally {, }
        // Run the program. What happens? Why?
        
        for (Thread t : threads) t.join();
        System.out.println(counter);
    }
}

//___________________________________________________________________


package ch05.sec01;

import java.util.concurrent.RejectedExecutionException;

public class FinallyNotCompletingNormallyDemo {
    public static int parseInt(String number) {
        try {
            int n = Integer.parseInt(number);
            return n;
        } catch (NumberFormatException ex) {
            return 0;            
        } finally {
            return -1; // This value is actually returned
        }
    }
    
    public static double parseDouble(String number) {
        try {
            double x = Double.parseDouble(number);
        } finally {
            throw new IllegalStateException(); // Masks NumberFormatException
        }
    }

    
    public static void main(String[] args) {
        System.out.println(parseInt("Fred"));
        System.out.println(parseInt("123"));
        try {
            System.out.println(parseDouble("Fred"));            
        } catch(Exception ex) {
            System.out.println("Caught " + ex.getClass().getName());
        }

        try {
            System.out.println(parseDouble("12.3"));            
        } catch(Exception ex) {
            System.out.println("Caught " + ex.getClass().getName());
        }
    }
}

//___________________________________________________________________


package ch05.sec01;

public class DefaultUncaughtExceptionHandlerDemo {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((thread, ex) -> {
            System.err.println(ex.getMessage());
            System.err.println("Goodbye, cruel world!");
        });
        System.out.println(1 / 0);
    }
}

//___________________________________________________________________

package ch05.sec01;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StackTraceDemo {
    public static void bad() {
        System.out.println(1 / 0);
    }
    
    public static void goodOrBad(boolean ok) {
        if (ok) good(); else bad();
    }
    
    public static void good() {
        StackWalker walker = StackWalker.getInstance();
        walker.forEach(frame -> System.err.println("Frame: " + frame));        
    }
    
    public static void main(String[] args) {
        try {
            goodOrBad(false);
        } catch (Exception ex) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ex.printStackTrace(new PrintStream(out));
            String description = out.toString();
            System.out.println("The stack trace in a string: " + description.replaceAll("\\s+",  " "));
        }
        goodOrBad(true);
    }
}

//___________________________________________________________________
//___________________________________________________________________

// Normally, assertions are disabled. Be sure to run with
// assertions enabled to see the assertion error.

package ch05.sec02;

import java.util.Arrays;
import java.util.Scanner;

public class AssertionDemo {
    public static double[] solveQuadraticEquation(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        
        assert discriminant >= 0;
        
        assert a != 0 : "a == 0; not a quadradic equation";


        double discrRoot = Math.sqrt(discriminant);
        return new double[] { 
                (-b - discrRoot) / (2 * a),
                (-b + discrRoot) / (2 * a)
        };
    }
    
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Enter a b c (e.g. 0 2 1)");
            double a = in.nextDouble();
            double b = in.nextDouble();
            double c = in.nextDouble();
            System.out.println(Arrays.toString(solveQuadraticEquation(a, b, c)));
        }
    }
}

//___________________________________________________________________

//___________________________________________________________________

package ch05.sec03;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingDemo {
    public static void main(String[] args) {
        Logger.getGlobal().info("Starting program");

        Logger.getGlobal().setLevel(Level.OFF);
        
        Logger.getGlobal().info("Global logger turned off");
        Logger logger = Logger.getLogger("com.horstmann.corejava");
        logger.fine("Global logger turned off");
        
        logger.setLevel(Level.FINE);
        logger.fine("Set logger to fine");
        logger.setUseParentHandlers(false);
        Handler handler = new ConsoleHandler();
        
        handler.setLevel(Level.FINE);        
        logger.addHandler(handler);
        logger.fine("Configured handler");
    }
}
