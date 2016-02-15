/*
 A challenge from Google's Africa 2010 Qualification Round 

 Part A) Store Credit
 The program reads in a file containing a certain store credit, number of item 
 prices, and an array of items. It then finds the two store items that add up
 to the credit exactly.
 */

package storecredit;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

/*
 * @author Ryan Donahoe
 */
public class StoreCredit {

    public static void main(String[] args) {
        
        try(BufferedReader stream = new BufferedReader(new FileReader(args[0])); PrintWriter writer = new PrintWriter("output.txt")) {
            
            int cases = 0, credit = 0, itemCount = 0, item = 0;
            HashMap diff = new HashMap();
            
            // read in number of test cases
            cases = Integer.parseInt(stream.readLine());
            
            for(int i = 0; i < cases; i++) {
                // read amount of credit and number of items
                credit = Integer.parseInt(stream.readLine());
                itemCount = Integer.parseInt(stream.readLine());
                String[] temp = stream.readLine().split(" ");
                
                // walk through array checking if two items sum to the credit.
                for(int j = 0; j < itemCount; j++)
                {   
                    item = Integer.parseInt(temp[j]);
                    // check if current item price is in the differences hashmap
                    if(diff.containsKey(item)) {
                        writer.println(String.format("Case #%d: %d %d", i + 1, diff.get(item), j + 1));
                        diff.clear();
                        break;
                    }
                    // else make the difference between the credit and the 
                    // current price a key for the current index in the hash
                    diff.put(credit - item, j + 1);
                }
            }
        }
        catch(Throwable e) {
            e.printStackTrace();
        } 
    }
    
}