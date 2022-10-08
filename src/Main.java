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


            ArrayList<String> lBracket = new ArrayList<String>();
            ArrayList<String> rBracket = new ArrayList<String>();
            ArrayList<String> bracketQueue = new ArrayList<String>();
            ArrayList<String> bracketQueueIndex = new ArrayList<String>();

            int pos = 0;
            while (SBBuf.indexOf("\"") != -1){
                pos = SBBuf.indexOf("\"");
                SBBuf = SBBuf.delete(pos, pos+1);
            }
            System.out.println(SBBuf);
            while(SBBuf.indexOf("left")!=-1)
                {                                             //Parsing brackets and creating variables for them
                    lBracket.add(SBBuf.substring(SBBuf.indexOf("left")+7,SBBuf.indexOf("left")+8));
                    rBracket.add(SBBuf.substring(SBBuf.indexOf("right")+8, SBBuf.indexOf("right")+9));
                    SBBuf.delete(0,SBBuf.indexOf("right")+8);

                }

            System.out.println(lBracket.toString());
            System.out.println(rBracket.toString());
            scan.close();
            reader.close();

            try (FileReader readerText = new FileReader("src/text.txt")){
                Scanner scanText = new Scanner(readerText);
                SBuf = new String();
                while (scanText.hasNext()) {
                    SBuf += scanText.next();
                }
                if (SBuf.isEmpty()) throw new Exception("Text is empty");
                StringBuffer SBBufer = new StringBuffer(SBuf);
                for (int i = 0; i < SBBufer.length(); i++) {
                        /*if(rBracket.indexOf(SBBufer.charAt(i)) != -1)
                        {


                        }else if(lBracket.indexOf(SBBufer.charAt(i)) != -1)
                        {
                            bracketQueue.add(String.valueOf(SBBufer.charAt(i)));
                        }*/

                    if((rBracket.indexOf(String.valueOf(String.valueOf(SBBufer.charAt(i)))) != -1) && (lBracket.indexOf(String.valueOf(SBBufer.charAt(i))) == -1))
                    {
                        if(bracketQueue.isEmpty()){
                            bracketQueueIndex.add(String.valueOf(i));
                        }else if(lBracket.indexOf(bracketQueue.get(bracketQueue.size()-1)) == rBracket.indexOf(String.valueOf(SBBufer.charAt(i)))){
                                bracketQueue.remove(bracketQueue.size()-1);
                            }else{
                                //bracketQueue.remove(bracketQueue.size()-1);
                                bracketQueueIndex.add(String.valueOf(i));
                            }

                        /*bracketQueue.add(String.valueOf(SBBufer.charAt(i)));
                        bracketQueueIndex.add(String.valueOf(i));*/
                    }else if((rBracket.indexOf(String.valueOf(SBBufer.charAt(i))) != -1) && (lBracket.indexOf(String.valueOf(SBBufer.charAt(i))) != -1)){
                        if(bracketQueue.isEmpty()){
                            bracketQueue.add(String.valueOf(SBBufer.charAt(i)));
                        }else if((lBracket.indexOf(bracketQueue.get(bracketQueue.size()-1))) == (rBracket.indexOf(String.valueOf(SBBufer.charAt(i))))){
                            bracketQueue.remove(bracketQueue.size()-1);
                        }else {
                            bracketQueue.add(String.valueOf(SBBufer.charAt(i)));
                            /*System.out.println((lBracket.indexOf(bracketQueue.get(bracketQueue.size()-1))));
                            System.out.println(SBBufer.charAt(i));
                            System.out.println((rBracket.indexOf(SBBufer.charAt(i))));*/
                        }
                    }else if((lBracket.indexOf(String.valueOf(SBBufer.charAt(i))) != -1)){
                        bracketQueue.add(String.valueOf(SBBufer.charAt(i)));
                    }
                }
                if(bracketQueueIndex.isEmpty())
                    System.out.println("No mistakes found");
                else
                    System.out.println("Found mistakes on position(s): " + bracketQueueIndex.toString());
        }
        catch(Exception e) {throw new Exception("Error while parsing template");}
        }

    }
}