import org.junit.*;
import org.junit.Test;

import java.text.SimpleDateFormat;

public class MySingleWithOutTailLinkedListTest {

  @Test
  public void size() {
    MySingleWithOutTailLinkedList test = new MySingleWithOutTailLinkedList();

    TentOnly t1 = new TentOnly();
    RV r1 = new RV();



//    Assert.assertEquals(test.size(), 0);

    test.add(t1);

//    Assert.assertEquals(test.size(), 1);

    test.add(r1);

//    Assert.assertEquals(test.size(), 2);
  }

  @Test
  public void clear() {
  }

  @Test
  public void add() {
  }

  @Test
  public void remove() {
  }

  @Test
  public void get() {
  }
}