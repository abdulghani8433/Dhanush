package ghani.abdul;
import java.io.FileReader;  

public class fileReader{
	    public static void main(String args[])throws Exception{    
	          FileReader fr=new FileReader("C:\\Users\\abdul.ghani\\Desktop\\hi.txt");    
	          int i;    
	          while((i=fr.read())!=-1)    
	          System.out.print((char)i);    
	          fr.close();    
	    }    
	}    


