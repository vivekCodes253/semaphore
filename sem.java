import java.io.*;
import java.util.Scanner;


class sem
{
    public static String SOURCE = "status.txt";

    /*Function Name : sop
    Purpose         : Simplifies System.out.print()
    Input           : String to output
    Return          : --    */
    public static void sop(String s){System.out.print(s);}

    /*Function Name : filewrite
    Purpose         : write data to file
    Input           : filepath and data
    Return          : Status of write    */
    public static String filewrite(String filepath, String data)
    {
        return filewrite(filepath,data,true);
    }

    /*Function Name : filewrite [file operation]
    Purpose         : write string into file
    Input           : filepath, data string and a boolean whether to overwrite or not, not means append
    Return          : Status    */
    public static String filewrite(String filepath, String data, boolean overwrite)
    {
        File file;
        BufferedWriter writer;   
        try{
            writer = new BufferedWriter(new FileWriter(filepath,!overwrite));
        }
        catch(FileNotFoundException ex)
        {
            return("\nFile not found, create one or check the path!\n");
        }
        catch(Exception e)
        {
            return(""+e);
        }
        try{           
            writer.write(data);
            writer.close();
        }
        catch(Exception e)
        {
            return(""+e);
        }     
        return "Successfully appended!"; 
    }

    /*Function Name : fileread
    Purpose         : read data from file
    Input           : filepath
    Return          : Read message or status    */
    public static String fileread(String filepath)
    {
        File file = new File(filepath);
        String str, read_string = "";
        try{
            BufferedReader in = new BufferedReader(new FileReader(file)); 
            while ((str = in.readLine()) != null) 
            {
                read_string += str;     
            }   
        }
        catch(FileNotFoundException ex)
        {
            return("\nFile not found, create one or check the path!\n");
        }
        catch(Exception e)
        {
            return(e+"\n");
        }

        return read_string;
    }


    /*Function Name : 
    Purpose         : 
    Input           : 
    Return          :     */
    public static void test(){}

    /*Function Name : Req hold
    Purpose         : To request for semaphore hold, or show waiting till the other process has left
    Input           : --
    Return          :     */
    public static void reqhold()
    {
        String st = fileread(SOURCE);
        sop("\nWaiting..");
        while(st.equals("1"))
        {
            st = fileread(SOURCE);
        }
        
            filewrite(SOURCE,"1");
            sop("Acquired!");
       

    }

    /*Function Name : Drop hold
    Purpose         : To drop the semaphore hold
    Input           : --
    Return          :     */
    public static void drophold()
    {
        String st = fileread(SOURCE);
        if(st.equals("1")) 
        {
            filewrite(SOURCE,"0");
            sop("\nDropped");
        }
    }


    /*Function Name : menu
    Purpose         : Display menu, get user choice and perform operation
    Input           : 
    Return          : True if menu needs to be displayed again, false if exit condition    */
    public static boolean menu()
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        sop("\n\n1) Request hold \n2) Drop hold \n3) Exit\n");
        choice = sc.nextInt();
        if(choice==3)
            return false;
        if(choice==1)
            reqhold();
        else if(choice==2)
            drophold();
        return true;
    }

    public static void main(String[] args)
    {
        //test();
        while(menu());

        //code will have a menu
        //1) Request hold
        //2) Drop hold
        //3) Exit

        
    }
}