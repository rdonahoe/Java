package ctci_linkedlist;
import java.io.*;
import java.util.*;

/*
 * @author Ryan Donahoe
 */

public class CTCI_LinkedList {

    public static void main(String[] args) {
    /*
    2.1 Write code to remove duplicates from an unsorted linked list.
    FOLLOW UP
    How would you solve this problem if a temporary buffer is not allowed?
    */
//        int[] arr = {1,2,3,2,5,3,2,7,7,2,9};
//        Node list = new Node(arr);
//        list = removeDuplicate(list);
//        list.printList(list);


    /*
    2.2 Implement an algorithm to find the kth to last element of a 
    singly linked list.
    */
        int[] arr = {1,2,3,2,5,3,2,7,7,2,9};
        Node list = new Node(arr);
        System.out.println(kthToLast(list, 1).data);
        
    }
    
    // 2.1
    public static Node removeDuplicate(Node start) {
        // this solution is O(n) run
        Hashtable ht = new Hashtable();
        Node cur;
        
        if (start.next == null) 
            return start;
        
        ht.put(start.data, 0);
        cur = start;
        while (cur.next != null) {
            if (ht.containsKey(cur.next.data)) {
                cur.next = cur.next.next;
                continue;
            }
            ht.put(cur.next.data, 0);
            cur = cur.next;
        }
        return start; 
    }
    
    // 2.2
    public static Node kthToLast(Node start, int k) {
        Node end = start;
        Node cur = start;
        int length = 1;
        
        while (end.next != null) {
            end = end.next;
            length++;
        }
        
        for(int i = 0; i < length - k; i++) {
            cur = cur.next;
        }
        
        return cur;
    }
    
}
