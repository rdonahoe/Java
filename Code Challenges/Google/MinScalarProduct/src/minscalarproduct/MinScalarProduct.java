/*
 A challenge from Google's 2008 Codejam 1A

 Part A) Minimum Scalar Product
 The program reads in a file containing two vectors. It then finds th scalar 
 product of the two vectors which where changed in order to make the scalar as 
 small as possible.
 */
package minscalarproduct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 * @author Ryan Donahoe
 */
public class MinScalarProduct {

    public static void main(String[] args) {
        
        try(BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")))) {
            
            int cases, n;
            long scalar;
            int[] v1;
            int[] v2;
            cases = Integer.parseInt(reader.readLine());
            
            for(int i = 0; i < cases; i++) {
                writer.print(String.format("Case #%d: ", i+1));
   
                scalar = 0;
                n = Integer.parseInt(reader.readLine());
                v1 = toIntArray(reader.readLine(), n);
                v2 = toIntArray(reader.readLine(), n);
                
                for(int start = 0, end = n - 1; start < n; start++, end--) {
                    scalar += v1[start]*v2[end];
                }
                writer.println(scalar);  
            }
        }
        catch(Throwable e) {
            e.printStackTrace();
        }
            
    }
    
    public static int[] toIntArray(String line, int n) {
        int[] arr = new int[n];
        String[] sn = line.split(" ");
        
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(sn[i]);
        }
        
        Arrays.sort(arr);
        return arr;
    }
    
}
