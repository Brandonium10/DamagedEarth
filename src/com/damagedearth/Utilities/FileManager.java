package com.damagedearth.Utilities;

import java.io.*;

public class FileManager
{
    public static void writeLine(String writeLine, String fileName, boolean noOverride)
    {
        FileOutputStream fop = null;
        File file;
        String content = writeLine + "\n";

        try
        {

            file = new File(fileName);
            fop = new FileOutputStream(file, noOverride);

            // if file doesnt exists, then create it
            if (!file.exists())
            {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (fop != null)
                {
                    fop.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void editLine(String change, String fileName, String heading)
    {
        try
        {
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String str;
            StringBuilder sb = new StringBuilder();
            while ((str = br.readLine()) != null)
            {
                if (str.split(":")[0].equalsIgnoreCase(heading))
                {
                    sb.append(change).append("\n");
                }
                else
                {
                    sb.append(str).append("\n");
                }
            }
            br.close();
            writeLine(sb.toString(), f.getName(), false);
        }
        catch (Exception e)
        {

        }
    }
}
