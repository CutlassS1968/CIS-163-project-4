import java.io.Serializable;

public class MySingleWithOutTailLinkedList implements Serializable {
    private Node top;
    public int size;


    public MySingleWithOutTailLinkedList() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public void add(CampSite s) {

       /*
       Requirement for step this step: When you write the add method, you are required to sort
       by Tenters first (ordered by estimatedCheckOut) and by RV second (ordered by
       estimatedCheckOut).  For this step, you need not worry about two estimatedCheckOut
       dates being equals.  (See the final step regarding a change in this requirement).
        (Suggestion, once your code is working for this step, back it up, and proceed on.)
        */
    }

    public CampSite remove(int index) {

        return null;
    }

    public CampSite get(int index) {
        return null;

    }

    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    @Override
    public String toString() {
        return "MySingleWithOutTailLinkedList{" +
                "top=" + top +
                ", size=" + size +
                '}';
    }
}
