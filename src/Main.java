import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


public class Main {



    public static void main(String[] args) throws Exception
    {



        try (FileReader reader = new FileReader("src/template.txt"))                //Reading Template;
        {
            Scanner scan = new Scanner(reader);
            String SBuf = new String();
            while (scan.hasNext()) {
                SBuf += scan.next();
            }
            if (SBuf.isEmpty()) throw new Exception("Template is empty");

            StringBuffer SBBuf = new StringBuffer(SBuf);
            ArrayList<String> bracketId = new ArrayList<String>();
            int pos = 0;
            while (SBBuf.indexOf("\"") != -1){
                pos = SBBuf.indexOf("\"");
                SBBuf = SBBuf.delete(pos, pos+1);
            }
            System.out.println(SBBuf);
            while(SBBuf.indexOf(",")!=-1)
                {                                             //Parsing brackets and creating variables for them
                    bracketId.add(SBBuf.substring(0,2));
                    SBBuf.delete(0,3);

                }
            bracketId.add(SBBuf.substring(0,2));
            SBBuf.delete(0,3);
            System.out.println(bracketId.toString());
            scan.close();
            reader.close();

            try (FileReader readerText = new FileReader("src/text.txt")){
                Scanner scanText = new Scanner(reader);
                String SBufer = new String();
                while (scan.hasNext()) {
                    SBuf += scan.next();
                }
                if (SBuf.isEmpty()) throw new Exception("Text is empty");
                StringBuffer SBBufer = new StringBuffer(SBuf);
                for (int i = 0; i < SBBuf.length(); i++){
                    for (String value: bracketId) {
                        int index = 0;
                        if(value.indexOf(SBBufer.charAt(i))==0)


                    }
                    }
            
            
            
            
            
        }
        catch(Exception e) {throw new Exception("Error while parsing template");}

        
        }

    }
}