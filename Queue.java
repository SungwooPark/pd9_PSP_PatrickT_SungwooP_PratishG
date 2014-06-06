public interface Queue<E>{
  public E dequeue();
  public void enqueue(E n);
  public boolean isEmpty();
  public E peekFront();
  }
