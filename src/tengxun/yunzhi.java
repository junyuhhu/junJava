package tengxun;

public class yunzhi {

   static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    static ListNode add(ListNode node1, ListNode node2){

       ListNode du = new ListNode(0);
       ListNode cur = du;

       int tem = 0;

       while (node1 !=null||node2!=null){
           int sum = tem;

           if(node1!=null){
               sum = node1.val+sum;
               node1 = node1.next;
           }

           if(node2!=null){
               sum = node2.val+sum;
               node2 = node2.next;
           }

           tem = sum/10;
           cur.next = new ListNode(sum%10);
           cur = cur.next;

           if (tem >0){
               cur.next = new ListNode(tem);

           }

       }



       return du.next  ;
    }
    public static void main(String[] args)  {

        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(4);

        node4.next = node5;
        node5.next = node6;
        node6.next = null;

        ListNode res = add(node1,node4);

        while (res!=null){
            System.out.println(res.val);
            res = res.next;
        }
    }

}



