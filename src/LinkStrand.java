import java.util.*;

public class LinkStrand implements IDnaStrand{
    private class Node{
        private String info;
        private Node next;


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
    


    public LinkStrand(String s) {
		initialize(s);
	}
    public LinkStrand(){
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
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        Node newNode;
        //create new Node with dna as info
        newNode = new Node (dna);
        if (myAppends == 0 == true){
            myFirst.next = newNode;
            myLast = newNode;
        }
        else if (myAppends == 0 == false){
            myLast.next = newNode;
            myLast = newNode;
        }
        mySize = mySize + dna.length();
        myAppends = myAppends + 1;

        return this;
    }

    public String toString(){
        StringBuilder newStringBuilder =  new StringBuilder();
        Node newNode = new Node (myFirst.info, myFirst.next);
        while (newNode.next !=null){
            newStringBuilder.append(newNode.info);
            newNode = newNode.next;
        }
        return newStringBuilder.toString();
    }

    @Override
    public IDnaStrand reverse() {
        StringBuilder newBuild = new StringBuilder(myLast.info);
        newBuild.reverse();
        LinkStrand newStrand = new LinkStrand(newBuild.toString());
        Node newNode = new Node (myFirst.info, myFirst.next);
        
        ArrayList<Node> newNodeList = new ArrayList<>();
        while (newNode.next !=null){
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
        if(index > mySize && index < 0){ //invalid index case
            throw new IndexOutOfBoundsException("");
        }
        if(index == myIndex + 1 == false){
            myCurrent = myFirst;
            for(int i = 0; myCurrent != null; i++){
                for(int x = 0; x < myCurrent.info.length(); x++){
                    if(i == index){
                        myIndex = i;
                        myLocalIndex = x;
                        return myCurrent.info.charAt(x);
                    }

                }
                myCurrent = myCurrent.next;
            }
        }
        else if (index == myIndex + 1 == true){
            myIndex = index;
            if(myLocalIndex + 1 == myCurrent.info.length()){
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
                return myCurrent.info.charAt(myLocalIndex);
            }
            else{
                myLocalIndex += 1;
                return myCurrent.info.charAt(myLocalIndex);
            }
        }
        return 0;
    }
}

    
