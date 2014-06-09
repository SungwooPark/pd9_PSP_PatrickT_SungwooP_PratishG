//taken from AP251 basement, and modified to include a getSize() function to improve usage with what we  need to do.

public class RQueue<T> implements Queue<T> {
	
	
    private LLNode<T> _front, _end;
    private int _size;

	public int getSize(){
		return _size;
	}
    // default constructor creates an empty queue
    public RQueue() { 
	_front = _end = null;
	_size = 0;
    }


    public void enqueue( T enQVal ) {
	//special case: when enqueuing to an empty list, 
	//make _front && _end point to same node
	if ( isEmpty() ) {
	    _front = _end = new LLNode<T>( enQVal, null );
	}
	else {
	    _end.setNext( new LLNode<T>( enQVal, null ) );
	    _end = _end.getNext();
	}
	_size++;
    }


    // remove and return thing at front of queue, then reorder elements
    // assume _queue ! empty
    public T dequeue() { 

	T retVal = _front.getValue();
	_front = _front.getNext();

	if ( _front == null ) //just moved past last node
	    _end = null;      //update _end to reflect emptiness

	_size--;

	if ( _size > 1 )  sample();

	return retVal;
    }


    public T peekFront() {
	return _front.getValue();
    }

    public void sample () {
	int cycles = (int)( _size * Math.random() );
	for( int i = 0; i < cycles; i++ )
	    enqueue( dequeue() );
    }


    public boolean isEmpty() { return _front == null; }//O(1)


    public String toString() { 
	String foo = "";
	LLNode<T> tmp = _front;
	while ( tmp != null ) {
	    foo += tmp.getValue() + " ";
	    tmp = tmp.getNext();
	}
	return foo;
    }



    
}//end class RQueue
