import java.util.*;

public class LinkStrand implements IDnaStrand{
    private class Node{
        //instance variables
        private String info;
        private Node next;

        //creating Node
        public Node(String input, Node node1){
            this.info = input;
            next = node1;
        }

        public Node(String input){
            this.info = input;
            next = null;
        }
    }
        private Node myFirst, myLast;
        private long mySize;
        private int myAppends;
        private int myIndex;
        private Node myCurrent;
        private int myLocalIndex;
    

    //initializing instance variables
    public LinkStrand(String s) { //string parameter
		initialize(s);
	}
    public LinkStrand(){ //no parameter
        this("");
    }

    @Override
    public long size() {
        return mySize;
    }

    @Override
    public void initialize(String source) {
       myFirst = new Node(source);
       myLast = myFirst;
       myAppends = 0;
       mySize = source.length();

       myIndex = 0;
       myCurrent = myFirst;
       myLocalIndex = 0;
    }

    @Override
    public IDnaStrand getInstance(String source) { //one line runtime
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        myLast.next = new Node(dna);
		myLast = myLast.next;
		myAppends = myAppends + 1;
        mySize = mySize + myLast.info.length();
		return this;
	}	

    public String toString(){

        StringBuilder newStringBuilder =  new StringBuilder();
        Node newNode = new Node (myFirst.info, myFirst.next);
        while (newNode.next == null == false){ 
            //while newNode still has elements to iterate through
            newStringBuilder.append(newNode.info);//add node info to new StringBuilder object
            newNode = newNode.next;
        }
        newStringBuilder.append(newNode.info);
        return newStringBuilder.toString();
    }

    @Override
    public IDnaStrand reverse() {
        Node current = myFirst;
        if (current == null == true) {
            return new LinkStrand();
        }
        current = current.next;
        StringBuilder final1 = new StringBuilder(myFirst.info);
        final1 = final1.reverse();
        Node newFinal = new Node(final1.toString());

        while (current == null == false) {
            final1 = new StringBuilder(current.info);
            final1 = final1.reverse();
            String reverseString = final1.toString();
            newFinal = new Node(reverseString, newFinal);
            current = current.next;
        }
        current = newFinal;
        LinkStrand newStrand = new LinkStrand(current.info);
        current = current.next;
        while (current == null == false) {
            newStrand.append(current.info);
            current = current.next;
        }
        return newStrand;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) throws IndexOutOfBoundsException{
        if(0 > index || this.size() < myIndex || myIndex == this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if (myIndex >= index)
		{
			myIndex = 0;
			myLocalIndex = 0;
			myCurrent = myFirst;
		}
		while (index != myIndex)
		{
			myIndex = myIndex + 1;
			myLocalIndex = myLocalIndex + 1;
			if (myCurrent.next != null && myCurrent.info.length()<= myLocalIndex) {
				myCurrent = myCurrent.next;
				myLocalIndex = 0;
			}	
		}
		return myCurrent.info.charAt(myLocalIndex);
	}	
}
