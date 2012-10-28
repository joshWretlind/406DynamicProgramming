
package JoshWretlind;


public class Palin {

    public final int MATCH = 0;
    public final int INSERT = 1;
    public final int DELETE = 2;
    
    public int match(char c1, char c2){
        if(c1 == c2){
            return 0;
        } else{
            return 10000; // some large number
        }
    }
    
    public Cell[][] longest_common_sub(String s1, String s2){
        int[] options = new int[3];
        if(s1.length() == 1 || s2.length() == 1){ // if we get a 1 char long string
        	Cell c = new Cell();
        	c.character = s1.charAt(0);
        	Cell[][] m = new Cell[1][1];
        	m[0][0] = c;
        	return m;
        } 
        Cell[][] m = new Cell[s1.length()][s2.length()];
        for(int i = 0; i < s1.length(); i++){
        	m[0][i] = new Cell();
            m[0][i].value = i;
            if(i > 0){
            	m[0][i].parent = m[0][i-1];
            } else{
            	m[0][0].parent = null;
            }
            if(s1.charAt(i) == s2.charAt(0)){
        		m[0][i].character = s2.charAt(0);
        	}
        }
        for(int i = 1; i < s2.length(); i++){
        	m[i][0] = new Cell();
            m[i][0].value = i;
            m[i][0].parent = m[i-1][0];
            if(s1.charAt(0) == s2.charAt(i)){
        		m[i][0].character = s1.charAt(0);
        	}
        }
        
        for(int i=1; i < s1.length();i++){
            for(int j = 1; j < s2.length(); j++){
            	m[i][j] = new Cell();
                options[MATCH] = m[i-1][j-1].value + match(s1.charAt(j), s2.charAt(i));
                options[INSERT] = m[i][j-1].value + 1;
                options[DELETE] = m[i-1][j].value + 1;
                
                m[i][j].value = options[MATCH];
                m[i][j].parent = m[i-1][j-1];
                m[i][j].moveToHere = MATCH;
                if(options[INSERT] < m[i][j].value){
                    m[i][j].value = options[INSERT];
                    m[i][j].parent = m[i][j-1];
                    m[i][j].moveToHere = INSERT;

                }
                if(options[DELETE] < m[i][j].value){
                    m[i][j].value = options[DELETE];
                    m[i][j].parent = m[i-1][j];
                    m[i][j].moveToHere = DELETE;

                }
                if(options[DELETE] == options[INSERT] && options[INSERT] < options[MATCH] && options[DELETE] < options[MATCH]){
                	if(m[i-1][j].character != ' '){
                		m[i][j].value = options[DELETE];
                        m[i][j].parent = m[i-1][j];
                        m[i][j].moveToHere = DELETE;
                	} else if(m[i][j-1].character != ' '){
                		m[i][j].value = options[INSERT];
                        m[i][j].parent = m[i][j-1];
                        m[i][j].moveToHere = INSERT;
                	}
                }
                if(m[i][j].value == options[MATCH]){
                	m[i][j].character = s1.charAt(j);
                }               
            }
        }
        if(m[1][1].parent != m[0][0]){ //if we didn't get here via a match
        	m[0][0].character = ' ';
        }
        return m;
    }
    public Palin(){
    	
    }
    public String calcPalin(String s){    
    	StringBuffer input = new StringBuffer(s);
        Cell[][] arr = longest_common_sub(input.toString(), input.reverse().toString());
        Cell cell = arr[input.length()-1][input.length()-1];
        for(int i = 0; i < arr.length; i++){
        	for(int j = 0; j < arr[0].length; j++){
        		System.out.print(arr[i][j]+ "|");
        	}
        	System.out.println("");
        }
        
        StringBuffer palin = new StringBuffer();
        while(cell != null){
        	palin.append(cell.character + "|");
        	cell =cell.parent;
        }
        //String finalString = palin.toString().replaceAll(" ",""); // get rid of all the spaces(spaces are where we didn't get a match)
        System.out.println(palin.toString());
        return palin.toString();
    }
}
