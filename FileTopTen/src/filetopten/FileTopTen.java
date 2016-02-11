/*
 * reads in a text file and prints out what the top 10 used characters were
 * program runs O(n) runtime where n is the number of chars in the file, and
 * O(1) space because the program only reads in one char at a time
 */
package filetopten;
import java.io.*;
import java.util.*;

/*
 * @author Ryan Donahoe
 */
public class FileTopTen {

    public static void main(String[] args) throws IOException {
        int[] chars = new int[256];
        
        try (FileReader input = new FileReader("test.txt")) {
            
            int c;
            while((c = input.read()) != -1) {
                chars[c]++;
            }
            
            printTopTen(chars);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
    public static void printTopTen(int[] arr) {
        int[] tt = new int[10];
        int max = 0, index = 0;
        
        // O(10 * 256) which means O(1) runtime
        // O(1) space
        for (int i = 0; i < tt.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] > max) {
                   max = arr[j];
                   index = j;
                }
            }
            arr[index] = 0;
            max = 0;
            tt[i] = index;
        }
        
        String res = String.format("%c, %c, %c, %c, %c, %c, %c, %c, %c, %c", 
                tt[0], tt[1], tt[2], tt[3], tt[4], tt[5], tt[6], tt[7], tt[8], tt[9] );
//        for (int t : tt) {
//            System.out.print((char)t);
//            System.out.print(",");
//        }
        System.out.println(res);
    }
    
}
