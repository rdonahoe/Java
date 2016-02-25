package ctci_arraysstrings;
import java.util.*;
import java.util.HashSet;
import java.util.Hashtable;
import java.io.*;

/*
 * @author Ryan Donahoe
 */
public class CTCI_ArraysStrings {
    
    public static String[] strs = {
            "accccccccdda",         // true
            "ffffffgggghh",     // false
            "jj",               // false
            "abc",              // true
            "abbbbb"            // true
        };
    
    public static String[] strs2 = {
            "tihs",             // true
            "w0uld th1s w0rk",  // false
            "Bran Donahoe",     // false
            "Jam Hilpret",      // true
            "String"            // true
        };

    public static void main(String[] args) {
    /* 
    1.1 Implement an algorithm to determine if a string has all unique 
    characters. What if you cannot use additional data structures?

    UPDATE: a better solution for this is to assign a boolean to each 
    character in an array and then update and check it as the string 
    is traversed.
    */
//        String[] str1 = {
//            "tihs",             // true
//            "w0uld th1s w0rk",  // false
//            "Bran Donahoe",     // false
//            "Jam Hilpret",      // true
//            "String"            // true
//        };
//        for (String s : str1) {
//            System.out.print(isUniq(s));
//            System.out.print(", ");
//            System.out.println(isUniq2(s));
//        }
        

    /*
    1.2 Implement a function void reverse(char* str) in C or C++ which 
    reverses a nullterminated string.

    I did this in java just because that's what I am practicing. Although 
    I think this can be done more effeciently in C
    */
//        String[] str2 = {
//            "tihs",             // true
//            "w0uld th1s w0rk",  // false
//            "Bran Donahoe",     // false
//            "Jam Hilpret",      // true
//            "String"            // true
//        };
//        for (String s : str2) {
//            System.out.print(s);
//            System.out.print("|");
//            System.out.println(reverseString(s));
//        }


    /*
    1.3 Given two strings, write a method to decide if one is a permutation 
    of the other.
    */
//        String[] str3_1 = {
//            "this",             // true
//            "wloud this krow",  // true
//            "Ryan Donahoe",     // false
//            "Jam Hilpret",      // true
//            "String "           // false
//        };
//        String[] str3_2 = {
//            "tihs",            
//            "would this work",  
//            "Bran Donahoe",     
//            "Jim Halpret",     
//            "String"            
//        };
//        
//        for (int i = 0; i < strs.length; i++) {
//            System.out.println(isPerm(str3_1[i], str3_2[i]));
//        }

    
    /*
    1.4 Write a method to replace all spaces in a string with '%20'. You may 
    assume that the string has sufficient space at the end of the string to hold 
    the additional characters, and that you are given the "true" length of the 
    string. (Note: if implementing in Java, please use a character array so that 
    you can perform this operation in place.)
    
    skipped testing this one because creating input strings would be tedious
    spaceChange(str);
    */
        
        
    /*
    1.5 Implement a method to perform basic string compression using the counts of
    repeated characters. For example, the string aabcccccaaa would become
    a2blc5a3. If the "compressed" string would not become smaller than the 
    original string, your method should return the original string.
    */  
//        String[] str5 = {
//            "accccccccdda",         // true
//            "ffffffgggghh",     // false
//            "jj",               // false
//            "abc",              // true
//            "abbbbb"            // true
//        };
//        for (String s : str5) {
//            System.out.println(compressString(s));
//        }
        

    /*
    1.6 Given an image represented by an NxN matrix, where each pixel in 
    the image is 4 bytes, write a method to rotate the image by 90 degrees. 
    Can you do this in place?
    */
//        int mat6[][] = 
//        {
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
//            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}
//        };
//        turnNinety(mat6, 15);
//        
//        for(int[] i : mat6) {
//            for(int j : i) {
//                System.out.print(j);
//                System.out.print(' ');
//            }
//            System.out.println();
//        }
    }
    
    // 1.1
    public static boolean isUniq(String s) {
        // Hash Set solution is O(n) run, O(n) space
        HashSet ht = new HashSet();
        for (int i = 0; i < s.length(); i++) {
            ht.add(s.charAt(i));
        }
        if (ht.size() == s.length())
            return true;
        
        return false;
    }
    
    public static boolean isUniq2(String s) {
        // Brute force method without data structures is O(n^2) run, O(1) space
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            for (int j = 0; j < s.length(); j++) {
                if (j == i)
                    continue;
                if (c == s.charAt(j))
                    return false;
            }
        }
        return true;
    }
    
    // 1.2
    public static String reverseString(String s) {
        // this solution is done in O(n) run, O(n) space
        char[] arr = s.toCharArray();
        char temp;
        int i = 0, j = arr.length - 1;
        
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
        
        return new String(arr);
    }
    
    // 1.3
    public static boolean isPerm(String s1, String s2) {
        // this solution is O(n) run, O(1) space
        if (s1.length() != s2.length())
            return false;
        
        int[] check = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            check[s1.charAt(i)] += 1;
            check[s2.charAt(i)] -= 1;
        }
        
        for (int i = 0; i < s1.length(); i++) {
            if (check[s1.charAt(i)] != 0)
                return false;
        }
        
        return true;
    }
    
    // 1.4
    public static String spaceChange(String str) {
        // this solution is O(n+m) run, O(n) space
        char[] arr = str.toCharArray();
        int count = 0, outlength;
        
        for(char c : arr) {
            if (c == ' ')
                count++;
        }
        
        outlength = count*2 + arr.length - 1;
        
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != ' ') {
                arr[outlength--] = arr[i];
            }
            else {
                arr[outlength--] = '0';
                arr[outlength--] = '2';
                arr[outlength--] = '%';
            }
        }
        
        return new String(arr);
    }
    
    // 1.5
    public static String compressString(String str) {
        // this solution is O(n) run, O(n) space
        StringBuilder build = new StringBuilder();
        char cur, nxt;
        int count;
        
        cur = str.charAt(0);
        count = 1;
        for (int i = 1; i < str.length(); i++) {
            nxt = str.charAt(i);
            if(cur != nxt) {
                build.append(cur);
                build.append(count);
                cur = nxt; count = 1;
            }
            else {
                count++;
            }
        }
        build.append(cur);
        build.append(count);
        
        if (build.length() >= str.length())
            return str;
        else
            return build.toString();
    }

    // 1.6
    public static void turnNinety(int[][] m, int s) {
        // it has to touch every item in the array so it is O(n^2) run, space O(1)
        int temp, end;
        
        // outside loop tracks what layer of the matrix it is changing
        for(int i = 0; i < s/2; i++) {
            end = (s - i) - 1; // the end of the current layer
            
            // i variable is current start of the layer
            for (int j = i, off = 0; j < end; j++, off++) {
                
                // rotate the current layer one point at a time
                temp = m[i][j];
                m[i][j] = m[end - off][i];
                m[end - off][i] = m[end][end - off];
                m[end][end - off] = m[j][end];
                m[j][end] = temp;
            }
        }
    }
}
