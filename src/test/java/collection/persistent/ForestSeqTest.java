package collection.persistent;

import org.junit.Test;

import static org.junit.Assert.*;

public class ForestSeqTest {
  @Test(expected = Seq.RangeException.class)
  public void headOfEmptySeq() {
    new ForestSeq<String>().head();
  }

  @Test(expected = Seq.RangeException.class)
  public void tailOfEmptySeq() {
    new ForestSeq<String>().tail();
  }

  @Test
  public void cons() {
    ForestSeq<Integer> t = new ForestSeq<Integer>();
    for (int n = 0; n < 1000; n++) {
      t = t.cons(n);
      assertEquals(n, (int) t.head());
      assertEquals(n + 1, t.size());
      for (int m = 0; m < t.size(); m++) {
        assertEquals(n - m, (int) t.get(m));
      }
    }
  }

  @Test
  public void update() {
    Sum sum = new Sum();
    ForestSeq<Integer> t = new ForestSeq<Integer>();
    for (int n = 0; n < 1000; n++) {
      t = t.cons(n);
    }
    for (int n = 0; n < t.size(); n++) {
      t = t.set(n, 0);
    }
    for (int n = 0; n < t.size(); n++) {
      assertEquals(0, (int) t.get(n));
    }
    t.accept(sum);
    assertEquals(0, sum.sum);
    for (int n = 0; n < t.size(); n++) {
      t = t.set(n, 1);
    }
    for (int n = 0; n < t.size(); n++) {
      assertEquals(1, (int) t.get(n));
    }
    t.accept(sum);
    assertEquals(1000, sum.sum);
  }

  class Sum implements Seq.Visitor<Integer> {
    int sum;

    @Override
    public void before(int size) {
      sum = 0;
    }

    @Override
    public void visit(Integer v) {
      sum += v;
    }

    @Override
    public void after() {}
  }
}
