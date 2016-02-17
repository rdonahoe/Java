/*
 A challenge from Google's Africa 2010 Qualification Round 

 Part C) T9Encoder
 The program reads in a file containing a certain string. It then encodes the 
 string using T9, the method of typing in older phones. 
 */
package t9encoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
 * @author Ryan Donahoe
 */
public class T9Encoder {
    
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")))) {
            
            // array of letters which contain which key contains the letter 
            // and how many presses of the key is needed for the specific character
            // the class does not contain the character because it isn't needed
            Letter[] letters = {
                // abc
                new Letter(2,1), new Letter(2,2), new Letter(2,3),
                // def
                new Letter(3,1), new Letter(3,2), new Letter(3,3),
                // ghi
                new Letter(4,1), new Letter(4,2), new Letter(4,3),
                // jkl
                new Letter(5,1), new Letter(5,2), new Letter(5,3),
                // mno
                new Letter(6,1), new Letter(6,2), new Letter(6,3),
                // pqrs
                new Letter(7,1), new Letter(7,2), new Letter(7,3), new Letter(7,4),
                // tuv
                new Letter(8,1), new Letter(8,2), new Letter(8,3),
                // wxyz
                new Letter(9,1), new Letter(9,2), new Letter(9,3), new Letter(9,4)
            };
            int key, previousKey = 100, repeat, 
                    cases = Integer.parseInt(reader.readLine());
            
            for (int i = 0; i < cases; i++) {
                writer.print(String.format("Case #%d: ", i+1));
                
                // read in line and transfer char array
                char[] line = reader.readLine().toCharArray();
                for (char c : line) {
                    if(c == ' ') {
                        writer.print(0);
                        previousKey = 100;
                    }
                    else {
                        // use char's ascii value as index (a == 97 - 97 = 0, b == 98 - 97 = 1)
                        key = letters[c - 97].key;
                        repeat = letters[c - 97].repeat;
                        
                        // if we are using the same key as before, insert pause
                        if(key == previousKey) {
                            writer.print(' ');
                        }
                        
                        // set current key as previous and then print current key
                        // enough to get needed character
                        previousKey = key;
                        for(int j = 0; j < repeat; j++) {
                            writer.print(key);
                        }
                    }
                }
                writer.println(); 
                previousKey = 100; // 100 is random number that is not used key
            }
        }
        catch(Throwable e) {
            e.printStackTrace();
        }
    }
    
}
