package ctci_linkedlist;

/*
 * @author Ryan Donahoe
 */
 public class Node {
     Node next = null;
     int data;

     public Node(int d) {
         data = d;
     }
    
     public Node(int[] arr) {
         Node cur = this;
         data = arr[0];
        
         for (int i = 1; i < arr.length; i++) {
             cur.next = new Node(arr[i]);
             cur = cur.next;
         }
     }

     public void appendToTail(int d) {
         Node end = new Node(d);
         Node n = this;
         while (n.next != null) {
            n = n.next;
         }
         n.next = end;
     }
    
     public Node deleteNode(Node head, int d) {
         Node n = head;

         if (n.data == d) {
            return head.next; /* moved head */
         }

         while (n.next != null) {
             if (n.next.data == d) {
                n.next = n.next.next;
                return head; /* head didn't change */
             }
             n = n.next;
         }
         return head;
     }
     
     public void printList(Node start) {
         Node cur = start;
         while(cur != null) {
             System.out.println(cur.data);
             cur = cur.next;
         }
     }
        
 }
