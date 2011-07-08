package collection;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class FingerTreeSeqTest {
  @Test(expected = Seq.RangeException.class)
  public void headOfEmptySeq() {
    new FingerTreeSeq<String>().head();
  }

  @Ignore
  @Test(expected = Seq.RangeException.class)
  public void tailOfEmptySeq() {
    new FingerTreeSeq<String>().tail();
  }

  @Test
  public void cons() {
    FingerTreeSeq<Integer> t = new FingerTreeSeq<Integer>();
    for (int n = 0; n < 1000; n++) {
      t = t.cons(n);
      assertEquals(n, (int) t.head());
      assertEquals(n + 1, t.size());
      for (int m = 0; m < t.size(); m++) {
        assertEquals(m, (int) t.nth(m));
      }
    }
    //t.dump(new IndentingPrintWriter(System.out));
  }

  @Test
  public void snoc() {
    FingerTreeSeq<Integer> t = new FingerTreeSeq<Integer>();
    for (int n = 0; n < 1000; n++) {
      t = t.snoc(n);
      assertEquals(0, (int) t.head());
      assertEquals(n + 1, t.size());
      for (int m = 0; m < t.size(); m++) {
        assertEquals(n - m, (int) t.nth(m));
      }
    }
    //t.dump(new IndentingPrintWriter(System.out));
  }
}
