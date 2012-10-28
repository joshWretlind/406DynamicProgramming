
package JoshWretlind;

public class Cell {

    public int value;
    public Cell parent;
    public char character;
    public int moveToHere = -1 ;
    
    public Cell(){
    	value = 0;
    	parent = null;
    	character = ' ';
    }
    
    public Cell(int value){
    	this.value = value;
    }
    
    @Override
    public String toString(){
    	return value + " " + character + " " + moveToHere;
    }
}
