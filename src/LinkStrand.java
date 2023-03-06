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
        StringBuilder newBuild = new StringBuilder(myLast.info);
        newBuild.reverse();
        LinkStrand newStrand = new LinkStrand(newBuild.toString());
        Node newNode = new Node (myFirst.info, myFirst.next);
        ArrayList<Node> newNodeList = new ArrayList<>();

        while (newNode.next == null == false){
            newNodeList.add(newNode);
            newNode = newNode.next;
        }

        int i = newNodeList.size();
        while (i>=0){
            StringBuilder placeHolder = new StringBuilder (newNodeList.get(i).info);
            placeHolder.reverse();
            newStrand.append(placeHolder.toString());
        }
        return newStrand;
        }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        //must be constant time operation
        if(0 > index || this.size() <= myIndex){ //invalid index case
            throw new IndexOutOfBoundsException(""); //out of bounds exception thrown
        }

        if (index == myIndex + 1 == true){
            myIndex = index; //updating myIndex
            if(myLocalIndex + 1 == myCurrent.info.length() == true){
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
                return myCurrent.info.charAt(myLocalIndex);
            }
            else{
                myLocalIndex = myLocalIndex + 1;
                return myCurrent.info.charAt(myLocalIndex);
            }
        }

        else if(index == myIndex + 1 == false){
            myCurrent = myFirst;
            for(int i = 0; myCurrent == null == false; i++){
                for(int x = 0; x < myCurrent.info.length(); x++){
                    if(i == index == true){
                        myIndex = i;
                        myLocalIndex = x;
                        return myCurrent.info.charAt(x);
                    }

                }
                myCurrent = myCurrent.next;
            }
        }
        return 0;
    }
}