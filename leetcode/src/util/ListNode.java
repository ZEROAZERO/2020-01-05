package util;

/**
 * 链表节点
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }
}

class Train { //每一节车厢
    int no; //车厢编号
    Train next; //与其他车厢关联
    Train(int no) {
        this.no = no;
    }
}
class Main {
    public static void main(String[] args) {
        //声明一节车厢，编号为1
        Train t1 = new Train(1);
        //声明另一节车厢，编号为2
        Train t2 = new Train(2);
        //我们可以通过t1, t2访问 1号和 2号车厢
        System.out.println("t1 addr:"+t1+" t1.no:"+t1.no+" t1.next:"+t1.next);
        System.out.println("t2 addr:"+t2+" t2.no:"+t2.no+" t2.next:"+t2.next);
        //这两节车厢怎么连接起来呢？通过next，但是next存放的是什么呢？
        t1.next = t2;//这时关联了
        System.out.println(t1.next+" "+t2);//这时我们能看到，t1.next==t2，所以知道next中存放的是下一个地址
    }
}