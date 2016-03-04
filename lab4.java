/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap_lab4;
import java.util.*;
import java.io.*;

/**
 *
 * @author hrehman.bscs13seecs
 */
public class ap_lab4 
{
public static void addTree(File file, Collection<File> chain) 
    {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                addTree(child, chain);
            }
        }else{
            chain.add(file);
        }
    }
public static void IndexSearch(HashMap files) throws IOException
    {
        //for user input
        Scanner in = new Scanner(System.in);
        
        while(true){
            System.out.println("Enter the keyword: ");
            System.out.println("Or Press q to exit: ");
            String s = in.nextLine();
            //exit method
            if(s.equals("q")||s.equals("Q"))
            {
                System.out.println("Please see Summary.txt in the project folder for results.");
                System.out.println("Exiting the program...");
                return;
            }
            
            //to traverse the map
            Set set = files.entrySet();
            Iterator i = set.iterator();
            
            while(i.hasNext()) {
                Map.Entry curr = (Map.Entry)i.next();
                String val = (String)curr.getValue();
                
                File file = new File(val);
                
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = null;
                while ((line = br.readLine()) != null) {
                        if(line.contains(s)){
                          System.out.println("Content found in file: "+" "+val);
                        }
                }
     
            }   
        }
    }
    
    public static void printIndex(HashMap files)
    {
        
            Set set = files.entrySet();
            Iterator s = set.iterator();
            System.out.println("Index mapping: ");
            while(s.hasNext()) 
            {
                Map.Entry curr = (Map.Entry)s.next();
                System.out.print(curr.getKey() + ": ");
                System.out.println(curr.getValue());
            }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Collection<File> chain = new ArrayList<File>();
        //Start crawling from Desktop
    addTree(new File("C:\\Users\\hrehman.bscs13seecs\\Desktop\\ABC"), chain);

    try {
        
        FileWriter fWriter = null;
        fWriter = new FileWriter("summary.txt");
        BufferedWriter out = new BufferedWriter(fWriter);

        //creating HashMap for indexing
        
        HashMap<String,String> files = new HashMap();
               
        Iterator itr = chain.iterator();
        while(itr.hasNext())
        {
            //get file
            File tested = (File) itr.next();

            //get the filename
            String Fname = tested.getName();
            String Fpath = tested.getPath();
            
   
            //write output to a file
            out.write(Fname+"  ----->  "+Fpath);
            out.newLine();
            
            //putting files to hashmap
            files.put(Fname,Fpath);
                   
        }
            
        //print indexed files
        printIndex(files);
        //search the index
        IndexSearch(files);
        
        out.close();
        }
    catch(IOException ex)
        {
            System.out.println("Error Occured!");
        }
    }
    
}
