import java.io.Serializable;

public class MySingleWithOutTailLinkedList implements Serializable {
  private Node top;
  public int size;


  public MySingleWithOutTailLinkedList() {
    top = null;
    size = 0;
  }

  public int size() {
    int total = 0;

    Node temp = top;
    while (temp != null) {
      total++;
      temp = temp.getNext();
    }
    return total;
  }


  public void clear() {
    top = null;
    size = 0;
  }

  public void add(CampSite data) {
    Node temp = top;

    // Case 0: if the list is empty
    if (size == 0) {
      top = new Node(data, null);
      ++size;
    }

    // Case 1: Normal list
    else {
      // Loop till at the last node
      for (int i = 0; i < size - 1; ++i) {
        temp = temp.getNext();
      }

      temp.setNext(new Node(data, null));
      ++size;
    }

    // Sort the list after adding
    sortList();

//    display();


    //TODO: figure out how to sort the LinkedList according to the guidelines
    //TODO: Adding a tent doesn't add a tent and adding an RV adds two RV's
    // This is not an issue with sortList(). when you add enough campsites eventually one
    // the first one you added shows up in the GUI. When entries are duplicated, they are
    // not being duplicated in the list, but in the GUI.
    // the issue seems to be with the fileredListCampSites and it's size. listCampSites is
    // correct in order and in size, while fileredListCampSites is at size 9 when it should be 1.
    // This could possibly be an issue with the get method.
    }

       /*
       Requirement for this step: When you write the add method, you are required to sort
       by Tenters first (ordered by estimatedCheckOut) and by RV second (ordered by
       estimatedCheckOut).  For this step, you need not worry about two estimatedCheckOut
       dates being equals.  (See the final step regarding a change in this requirement).
        (Suggestion, once your code is working for this step, back it up, and proceed on.)
        */


  public void sortList() {
    Node current = top;
    Node index;
    CampSite temp;

    // If the list is empty
    if (top.getNext() == null) {
      return;
    }

    // First sort by estimatedCheckOut
    while (current.getNext() != null) {
      index = current.getNext();

      while (index.getNext() != null) {
        if (current.getData().getEstimatedCheckOut().compareTo(index.getData().getEstimatedCheckOut()) <= 0) {
          temp = current.getData();
          current.setData(index.getData());
          index.setData(temp);
        }
        index = index.getNext();
      }
      current = current.getNext();
    }

      current = top;

    // Then sort by CampSite type
    while (current != null) {
      index = current.getNext();

      while (index != null) {
        if (current.getData() instanceof TentOnly) {
          temp = current.getData();
          current.setData(index.getData());
          index.setData(temp);
        }
        index = index.getNext();
      }
      current = current.getNext();
    }
    // TODO: Change sort method, tenters are not being sorted by est check out properly
  }

  public CampSite remove(int index) {
    // case 0, no list
    if (top == null)
      return null;

    // case 1, index is neg
    if (index < 0)
      return null;

    // case 2, index is too large
    if (index >= size)
      return null;

    //  ------ index is valid
    Node temp1;
    Node temp2;

    // case 3
    if (index == 0) {
      temp1 = top;
      top = top.getNext();
      --size;
      return temp1.getData();
    }

    // case 4 Multi items in the list
    temp1 = top;
    for (int i = 0; i < index; i++)
      temp1 = temp1.getNext();
    temp2 = temp1;
    temp1.setNext(temp1.getNext().getNext());
    --size;
    return temp2.getNext().getData();

  }

  public CampSite get(int index) {

    // ----- Index is invalid -----
    // List is empty
    if (top == null) {
      return null;
    }

    // Index is below 0
    if (index < 0) {
      return null;
    }

    // Index is bigger than list
    if (index >= size) {
      return null;
    }

    // ----- Index is valid -----
    // Index is top
    if (index == 0) {
      return top.getData();
    }

    // Search the list and return the CampSite at index
    Node temp = top;
    for (int i = 0; i < index; i++) {
      temp = temp.getNext();
    }

    return temp.getData();

  }

  public void display() {
    System.out.println(" ----- New Add ----- ");
    Node temp = top;
    while (temp != null) {
      System.out.println(temp.getData().getGuestName());
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
