/*
 A challenge from Google's Africa 2010 Qualification Round 

 Part B) Reverse Words
 The program reads in a file containing a certain string. It splits the words
 by spaces and reverses the order.
 */
package reversewords;
import java.io.*;
import java.util.*;

/*
 * @author Ryan Donahoe
 */
public class ReverseWords {

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader(args[0])); PrintWriter writer = new PrintWriter("output.txt")) {
            int cases = Integer.parseInt(reader.readLine());

            
            for(int i = 0; i < cases; i++) {
               // split incoming lines from file into words using space delimeter
               String[] words = reader.readLine().split(" ");
               
               // print words in reverse order to output file
               writer.print(String.format("Case #%d: ", i + 1));
               for(int j = words.length - 1; j >= 0; j--) {
                   writer.print(words[j]);
                   if(j != 0) {
                       writer.print(" ");
                   }
               }
               writer.println();
            }
        }
        catch(Throwable e) {
            e.printStackTrace();
        }
    }
    
}
