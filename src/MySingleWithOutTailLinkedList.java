import java.io.Serializable;

/**********************************************************************************************
 *
 * MySingleWithOutTailLinkedList contains the framework for Database List operations including
 * adding to the list, removing at a given index, retrieving a CampSite at a given index, and
 * sorting based on CampSite type and the estimated check out date
 *
 * @author Evan Johns
 * @version 04/12/2020
 *
 **********************************************************************************************/
public class MySingleWithOutTailLinkedList implements Serializable {

  /** current top of the LinkedList */
  private Node top;

  /** current size of the LinkedList */
  public int size;

  /*********************************************************************************************
   *
   * Instantiates ListModel's instance variables
   *
   *********************************************************************************************/
  public MySingleWithOutTailLinkedList() {
    top = null;
    size = 0;
  }

  /*********************************************************************************************
   * Loops through the LinkedList to count it's current size
   *
   * @return returns the current size of the LinkedList
   *********************************************************************************************/
  public int size() {
    int total = 0;

    Node temp = top;
    while (temp != null) {
      total++;
      temp = temp.getNext();
    }
    return total;
  }

  /*********************************************************************************************
   *
   * Clears the list of all nodes
   *
   *********************************************************************************************/
  public void clear() {
    top = null;
    size = 0;
  }

  /*********************************************************************************************
   * Adds a CampSite to the LinkedList and then sorts it
   *
   * @param data The CampSite that is being added to the bottom of the LinkedList
   *********************************************************************************************/
  public void add(CampSite data) {
    Node temp = top;

    // Case 0: if the list is empty
    if (size == 0) {
      top = new Node(data, null);
      ++size;
    }

    // Case 1: Normal list
    else {
      for (int i = 0; i < size - 1; ++i) {
        temp = temp.getNext();
      }
      temp.setNext(new Node(data, null));
      ++size;
    }

    // Sort the list after adding
    sortList();

  }

  // TODO: Split LinkedList into two ArrayLists (tenters and RV's), then sort the two by checkOut
  /*********************************************************************************************
   * Sorts the list, first according to each CampSite's check out date. Then sorts
   * the list according to each CampSite's type. This method is called every time
   * a CampSite is added to the List
   *********************************************************************************************/
  public void sortList() {
    Node current = top;
    Node index;
    CampSite temp;

    // Case 0: If the list is empty
    if (top.getNext() == null) {
      return;
    }

    // Case 1: List is not empty
    // First sort by estimatedCheckOut
    while (current.getNext() != null) {
      index = current.getNext();
      while (index.getNext() != null) {
        // If the EstimatedCheckOut's are equal, then sort by GuestName
        if (current.getData().getEstimatedCheckOut().compareTo(index.getData()
            .getEstimatedCheckOut()) == 0) {
          if (current.getData().getGuestName().compareTo(index.getData().getGuestName()) <= 0) {
            temp = current.getData();
            current.setData(index.getData());
            index.setData(temp);
          }
        }
        // If the EstimatedCheckOut's are not equal, then sort accordingly
        if (current.getData().getEstimatedCheckOut().compareTo(index.getData()
            .getEstimatedCheckOut()) < 0) {
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
  }
  /*********************************************************************************************
   * Removes a CampSite at a given index
   *
   * @param index the index of the CampSite that is being removed
   * @return returns the CampSite that was removed
   *********************************************************************************************/
  public CampSite remove(int index) {
    // ----- index is invalid -----
    // Case 0: no list
    if (top == null) {
      return null;
    }

    // Case 1: index is negative
    if (index < 0) {
      return null;
    }

    // Case 2: index is too large
    if (index >= size) {
      return null;
    }

    //  ----- index is valid -----
    Node temp1;
    Node temp2;

    // Case 3: index is the top of the list
    if (index == 0) {
      temp1 = top;
      top = top.getNext();
      --size;
      return temp1.getData();
    }

    // Case 4: index is the bottom of the list
    if (index == size - 1) {
      temp1 = top;
      for (int i = 0; i < index - 1; i++) {
        temp1 = temp1.getNext();
      }
      temp2 = temp1.getNext();
      temp1.setNext(null);
      return temp2.getData();
    }

    // Case 5: All other valid index's
    temp1 = top;
    for (int i = 0; i < index; i++) {
      temp1 = temp1.getNext();
    }
    temp2 = temp1.getNext();
    temp1.setNext(temp1.getNext().getNext());
    --size;
    return temp2.getData();

  }

  /*********************************************************************************************
   * Retrieves a CampSite at a given index
   *
   * @param index the index of the CampSite that is being removed
   * @return returns the CampSite at the index
   *********************************************************************************************/
  public CampSite get(int index) {

    // ----- Index is invalid -----
    // Case 0: List is empty
    if (top == null) {
      return null;
    }

    // Case 1: Index is below 0
    if (index < 0) {
      return null;
    }

    // Case 2: Index is bigger than list
    if (index >= size) {
      return null;
    }

    // ----- Index is valid -----
    // Case 3: Index is top
    if (index == 0) {
      return top.getData();
    }

    // Case 4: Search the list and return the CampSite at index
    Node temp = top;
    for (int i = 0; i < index; i++) {
      temp = temp.getNext();
    }
    return temp.getData();
  }

  /*********************************************************************************************
   *
   * Loops through the current LinkedList and displays every guest name in order
   *
   *********************************************************************************************/
  public void display() {
    Node temp = top;
    while (temp != null) {
      System.out.println(temp.getData().getGuestName());
      temp = temp.getNext();
    }
  }

  /*********************************************************************************************
   * Overrides the default Object toString method. This toString returns a
   * string that displays the top of the list and the list's size
   *
   * @return String of the top and size of the LinkedList
   *********************************************************************************************/
  @Override
  public String toString() {
    return "MySingleWithOutTailLinkedList{" + "top=" + top + ", size=" + size + '}';
  }
}
