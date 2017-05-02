/*
 * MyTicApp
 */
package myticapp;

import java.io.*;
//import java.time.*;
import static java.time.Instant.now;
import java.util.*;

/**
 *
 * @author Brian
 * File systems have an interesting story to tell.
 * If the database is down, user can still interact with the system.
 * This class do just that.
 */
public class Tic 
{
    private final List<String> ticList;
    private final List<String> xticList;
    private static int ticx = 63;
    private StringTokenizer xticx;
    private String xtic;
    private static final String FILENAME = "tic.csv";
    
    public Tic()
    {
        ticList = new ArrayList<>();
        xticList = new ArrayList<>();
        //ticList.add("ticket,time,");     
    }
    /////////////////////////////////
    public int ReadFile() 
    { 
        try 
        {
            System.out.println("#1 Test input file:"+FILENAME);
            try (FileInputStream fpdStream = new FileInputStream(FILENAME)) 
            {
                InputStreamReader fpdReader = new InputStreamReader(fpdStream);
                BufferedReader breader = new BufferedReader(fpdReader);
                String line = breader.readLine();
                System.out.println("line : "+line);
                if(line != null)
                {
                    ticList.add(line);
                    String head = line;
                    StringTokenizer tokens = new StringTokenizer(head, ",");
                    while(tokens.hasMoreTokens())
                    {
                        System.out.println(tokens.nextToken());
                    }
                    line = breader.readLine();
                    while(line!= null)
                    {
                        System.out.println(line);
                        ticList.add(line);
                        line = breader.readLine();
                    }
                    int n = ticList.size();
                    xticx = new StringTokenizer(ticList.get(n-1),",");
                    int x = 0;
                    while(xticx.hasMoreTokens())
                    {
                        xticList.add(xticx.nextToken());
                        System.out.println(x+" : "+xticList.get(x));
                        x++;
                    }
                    ticx = Integer.valueOf(xticList.get(x-2));
                    System.out.println("ticx : "+ticx);
                    OutPut(now()+",");
                }
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error from input: " + e.toString());
        }
        catch (NumberFormatException e)
        {
           System.err.println("#1 ClassName: "+e.getClass().getName()+": "+e.getMessage());
        }
        catch (Exception e)
        {
           System.err.println("#1 ClassName: "+e.getClass().getName()+": "+e.getMessage());
        }
        System.out.println("///////////\n//");
        return ticx;
    }
//////////////////////////////
    public void OutPut(String ticX)
    {
        try 
        {
            System.out.println("#2 Test output file: "+FILENAME+" : "+ticX);
            try (FileOutputStream fpoStream = new FileOutputStream(FILENAME)) 
            {
                PrintWriter poWriter = new PrintWriter(fpoStream, true);
                for(int i = 0;i<ticList.size();i++)
                {
                    xtic = ticList.get(i);
                    System.out.println(i+" : "+xtic);
                    poWriter.println(xtic);
                }
                ticx++;
                poWriter.println(ticx+","+ticX);
            }
        } 
        catch (IOException e) 
        {
            ticx++;
            System.err.println("Error from output: "+ticx+" : " + e.toString());
        }
        catch (Exception e)
        {
           ticx++; 
           System.err.println("#2 ClassName: "+e.getClass().getName()+" : "+e.getMessage());
        }
        System.out.println("///////////\n// "+ticx);
   }//
/////////////////////////////////////
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try
        {
            System.out.println("\n////Tic....////\n");
            Tic tic = new Tic();
            tic.ReadFile();
        }
        catch(Exception e)
        {
            System.err.println("Tic : "+e.getClass().getName()+"\n "+e.getMessage());
        }
    }
    
}
