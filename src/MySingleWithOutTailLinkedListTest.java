import org.junit.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.ArrayList;

public class MySingleWithOutTailLinkedListTest {

  @Test
  public void size() {
    MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();
    createList(list);

    Assert.assertEquals(30,list.size());

  }

  @Test
  public void clear() {
    MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();
    createList(list);
    list.clear();

    Assert.assertEquals(0, list.size());
  }

  @Test
  public void add() {
    MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();

    // Case 0 & 1:
    createSmallList(list);
    Assert.assertEquals(4, list.size());
  }

  @Test
  public void sort() {
    MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyy");
    GregorianCalendar g1 = new GregorianCalendar();

    // Create the test list with a set seed
    try {
      Date d1 = df.parse("1/1/2020");
      g1.setTime(d1);
      int count = 0;
      Random rand = new Random(10);
      String guest;

      while (count < 10) {
        Date date = df.parse("1/" + (rand.nextInt(10) + 2) + "/2020");
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(date);
        if (rand.nextBoolean()) {
          guest = "t" + rand.nextInt(6);
          TentOnly tent = new TentOnly(guest, g1, g, null, rand.nextInt(20));
          list.add(tent);
        } else {
          guest = "RV" + rand.nextInt(6);
          date = df.parse("1/" + (rand.nextInt(10) + 2) + "/2020");
          g.setTime(date);
          RV rv = new RV(guest, g1, g, null, rand.nextInt(2000));
          list.add(rv);
        }
        ++count;
      }
    } catch (ParseException e) {
      throw new RuntimeException("Error in parsing");
    }

    // The correct order of the CampSites when sorted
    ArrayList<String> ref = new ArrayList<>();
    ref.add("t1");
    ref.add("t2");
    ref.add("t3");
    ref.add("t1");
    ref.add("RV0");
    ref.add("RV1");
    ref.add("RV4");
    ref.add("RV2");
    ref.add("RV4");
    ref.add("RV3");

    for (int i = 0; i < list.size(); ++i) {
      Assert.assertEquals((ref.get(i)), list.get(i).getGuestName());
    }
  }

  @Test
  public void remove() {
    MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();

    // Case 0:
    Assert.assertNull(list.remove(1));

    // Case 1:
    createList(list);
    Assert.assertNull(list.remove(-1));
    list.clear();

    // Case 2:
    createList(list);
    Assert.assertNull(list.remove(40));
    list.clear();

    // Case 3:
    createList(list);
    list.remove(0);
    Assert.assertEquals(29, list.size());
    list.clear();

    // Case 4:
    createList(list);
    list.remove(29);
    Assert.assertEquals(29, list.size());
    list.clear();

    // Case 5:
    createList(list);
    while (list.size() > 10) {
      list.remove(1);
    }
    Assert.assertEquals(10, list.size());
  }

  @Test
  public void get() {
    MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();
    ArrayList<CampSite> ref = new ArrayList<>();

    // Case 0:
    Assert.assertNull(list.get(1));

    // Case 1:
    createList(list);
    Assert.assertNull(list.get(-1));
    list.clear();

    // Case 2:
    createList(list);
    Assert.assertNull(list.get(40));
    list.clear();

    // Case 3 & 4:
    createSmallList(list);
    for (int i = 0; i < list.size(); i++) {
      ref.add(list.get(i));
    }

    for (int i = 0; i < list.size(); i++) {
      Assert.assertEquals(ref.get(i), list.get(i));
    }
  }

  // Running for the sake of coverage
  @Test
  public void display() {
    MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();
    createList(list);
    list.display();
  }

  // Running for the sake of coverage
  @Test
  public void toStringTest() {
    MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();
    createSmallList(list);
    Assert.assertEquals("MySingleWithOutTailLinkedList{top=:T1 :T2 :RV1 :RV2 null, size=4}",
        list.toString());
  }

  private void createList(MySingleWithOutTailLinkedList list) {
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    GregorianCalendar g1 = new GregorianCalendar();
    GregorianCalendar g2 = new GregorianCalendar();

    try {
      Date d1 = df.parse("1/1/2020");
      g1.setTime(d1);
      Date d2 = df.parse("1/4/2020");
      g2.setTime(d2);

      TentOnly tentOnly1 = new TentOnly("T1", g1, g2,g2,4);
      TentOnly tentOnly2 = new TentOnly("T2", g1,g2,g1, 8);

      RV RV1 = new RV("RV1",g1,g2,g2, 1000);
      RV RV2 = new RV("RV2",g1,g2,g1, 1000);

      list.add(tentOnly1);
      list.add(tentOnly2);

      list.add(RV1);
      list.add(RV2);

      // create a bunch of them.
      int count = 0;
      Random rand = new Random();
      String guest;

      while (count <26) {
        Date date = df.parse("1/" + (rand.nextInt(10) + 2) + "/2020");
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(date);
        if (rand.nextBoolean()) {
          guest = "T" + rand.nextInt(5);
          TentOnly tent = new TentOnly(guest, g1, g, null, rand.nextInt(20));
          list.add(tent);
        } else {
          guest = "RV" + rand.nextInt(5);
          date = df.parse("1/" + (rand.nextInt(10) + 2) + "/2020");
          g.setTime(date);
          RV rv = new RV(guest, g1, g, null, rand.nextInt(2000));
          list.add(rv);
        }
        count++;
      }
    } catch (ParseException e) {
      throw new RuntimeException("Error in testing, creation of list");
    }
  }

  private void createSmallList(MySingleWithOutTailLinkedList list) {
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    GregorianCalendar g1 = new GregorianCalendar();
    GregorianCalendar g2 = new GregorianCalendar();
    ArrayList<CampSite> camps = new ArrayList<>();

    try {
      Date d1 = df.parse("4/12/2020");
      g1.setTime(d1);
      Date d2 = df.parse("1/12/2020");
      g2.setTime(d2);

      TentOnly tentOnly1 = new TentOnly("T1", g1, g2, g2, 4);
      TentOnly tentOnly2 = new TentOnly("T2", g1,g2,g1, 8);

      RV RV1 = new RV("RV1",g1,g2,g2, 1000);
      RV RV2 = new RV("RV2",g1,g2,g1, 1000);

      list.add(tentOnly1);
      list.add(tentOnly2);

      list.add(RV1);
      list.add(RV2);
    } catch (ParseException e) {
      throw new RuntimeException("Error in parsing date");
    }
  }
}