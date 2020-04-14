import java.io.Serializable;
import java.util.ArrayList;

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

    // Sort list after adding
    sort();
  }

  /*********************************************************************************************
   * Sorts the list, first by splitting the LinkedList into two ArrayLists consisting of RV's and
   * Tents. Then sorts those ArrayLists according to estimated check out date and adds the
   * ArrayLists back
   * to the LinkedList in order
   *********************************************************************************************/
  public void sort() {
    ArrayList<CampSite> tents = new ArrayList<>();
    ArrayList<CampSite> rvs = new ArrayList<>();
    int tempSize = size;
    Node current;

    // Split LinkedList into two ArrayLists of TentOnly and RV
    for (int i = 0; i < tempSize; ++i) {
      if (get(i) instanceof TentOnly) {
        tents.add(get(i));
      } else {
        rvs.add(get(i));
      }
    }

    // Sort each ArrayList
    sortEstCheckOut(tents);
    sortEstCheckOut(rvs);

    // Clear the current LinkedList
    clear();

    // Add each ArrayList back to the LinkedList in order
    // Add Tents
    for (CampSite tent:tents) {
      current = top;
      if (size == 0) {
        top = new Node(tent, null);
        ++size;
      }
      else {
        for (int i = 0; i < size - 1; ++i) {
          current = current.getNext();
        }
        current.setNext(new Node(tent, null));
        ++size;
      }
    }
    // Add RV's
    for (CampSite rv:rvs) {
      current = top;
      if (size == 0) {
        top = new Node(rv, null);
        ++size;
      }
      else {
        for (int i = 0; i < size - 1; ++i) {
          current = current.getNext();
        }
        current.setNext(new Node(rv, null));
        ++size;
      }
    }

    current = top;
    CampSite tempCampSite;

    // Loop through the list and check for any links that meet the special case
    while (current.getNext() != null) {
      if (current.getData().getEstimatedCheckOut().equals(
          current.getNext().getData().getEstimatedCheckOut())) {
        if (current.getData().getGuestName().compareTo(
            current.getNext().getData().getGuestName()) > 0) {
          if (current.getNext().getData() instanceof TentOnly && current.getData() instanceof TentOnly) {

            tempCampSite = current.getData();
            current.setData(current.getNext().getData());
            current.getNext().setData(tempCampSite);
          } else if (current.getNext().getData() instanceof RV && current.getData() instanceof RV) {
            tempCampSite = current.getData();
            current.setData(current.getNext().getData());
            current.getNext().setData(tempCampSite);
          }
        }
      }
      current = current.getNext();
    }
  }

  /*********************************************************************************************
   *
   * Sorts  an imported ArrayList of CampSites according to each CampSite's estimated Check Out
   *
   *********************************************************************************************/
  private void sortEstCheckOut(ArrayList<CampSite> camps) {
    camps.sort((CampSite c1, CampSite c2)->c2.getEstimatedCheckOut()
        .compareTo(c1.getEstimatedCheckOut()));
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