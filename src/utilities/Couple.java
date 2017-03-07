package utilities;

/**
 * A bean for couples of generic items
 *
 * @author Xenofon Vasilakos xvas@aueb.gr
 */
public class Couple<F, S> {

   private F first;
   private S second;

   public Couple() {
      this.first = null;
      this.second = null;
   }
   
   public Couple(F first, S second) {
      this.first = first;
      this.second = second;
   }

   /**
    * @return the first
    */
   public F getFirst() {
      return first;
   }

   /**
    * @return the second
    */
   public S getSecond() {
      return second;
   }

   public int compareToFirst(Couple other) {
      if (first instanceof Comparable) {
         return ((Comparable) this.first).compareTo(other.first);
      }
      throw new UnsupportedOperationException("");
   }

   public int compareToSecond(Couple other) {
      if (second instanceof Comparable) {
         return ((Comparable) this.second).compareTo(other.second);
      }
      throw new UnsupportedOperationException("");
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final Couple<?, ?> other = (Couple<?, ?>) obj;
      return other.first.equals(this.first) && other.second.equals(this.second);
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 11 * hash + this.first.hashCode();
      hash = 11 * hash + this.second.hashCode();
      return hash;
   }

   @Override
   public String toString() {
      return "<" + first.toString() + ", " + second.toString() + ">";
   }

   public void setFirst(F newFirst) {
      this.first = newFirst;
   }

   public void setSecond(S newSecond) {
      this.second = newSecond;
   }

}
