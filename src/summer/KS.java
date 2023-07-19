package summer;

public class KS {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode tial=head;
        for (int i =1;i<=5;i++){
            ListNode t = new ListNode(i);
            tial .next=t;
            tial=t;
        }

        head = fun(head,2,4);
        tial=head.next;
        while (tial!=null){
            System.out.println(tial.val);
            tial = tial.next;
        }
    }
    static ListNode fun(ListNode head,int m, int n){
        int i=0;
        ListNode start = head;
        ListNode end = head;

        ListNode pre=head;
        ListNode cur = pre.next;
        while (i<n){
            if (i>=m){
                ListNode t=cur.next;
                cur.next = pre;
                pre = cur;
                cur = t;
            }
            else {
                start = pre;
                end = cur;
                pre = cur;
                cur = cur.next;

//                System.out.println(start.val);
            }
            i++;
        }
        start.next = pre;
        end.next=cur;
        return head;
    }
}

class ListNode {
   int val;
   ListNode next;
   ListNode(int val){
       this.val=val;
       this.next=null;
   }
}
