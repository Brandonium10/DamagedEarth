package com.damagedearth.Utilities.Components;

import sun.misc.UUDecoder;
import sun.misc.UUEncoder;

import java.io.*;

public class FileConfiguration
{
    File thisFile;
    String path;

    public FileConfiguration(String fileName)
    {
        this.thisFile = new File(fileName);
        this.path = fileName;
    }

    /**
     * @param line The text you want to add
     * @param antiOverride Should the file not override?
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeln(String line, boolean antiOverride) throws FileNotFoundException, IOException
    {
        FileOutputStream fop = null;
        String content = line + "\n";

        fop = new FileOutputStream(thisFile, antiOverride);

        // if file doesnt exists, then create it
        if (!thisFile.exists())
        {
            thisFile.createNewFile();
        }

        // get the content in bytes
        byte[] contentInBytes = content.getBytes();

        fop.write(contentInBytes);
        fop.flush();
        fop.close();
    }

    /**
     * @param lineNumber The number of the line you wish to edit
     * @param line       What you want to change the line to
     * @throws IOException
     */
    public void editLine(int lineNumber, String line) throws IOException
    {
        FileReader fr = new FileReader(this.thisFile);
        BufferedReader br = new BufferedReader(fr);

        String str;
        StringBuilder sb = new StringBuilder();
        int currentLine = 1;
        while ((str = br.readLine()) != null)
        {
            if (currentLine == lineNumber)
            {
                sb.append(line).append("\n");
            }
            else
            {
                sb.append(str).append("\n");
            }
            currentLine++;
        }
        br.close();
        this.writeln(sb.toString(), false);
        System.out.println("Edited " + this.thisFile.getName());
    }

    /**
     * @param lineStart The start of the line you want to edit
     * @param line      What you want to change the line to
     * @throws IOException
     */
    public void editLine(String lineStart, String line) throws IOException
    {
        FileReader fr = new FileReader(this.thisFile);
        BufferedReader br = new BufferedReader(fr);

        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null)
        {
            if (str.startsWith(lineStart))
            {
                sb.append(line).append("\n");
            }
            else
            {
                sb.append(str).append("\n");
            }
        }
        br.close();
        this.writeln(sb.toString(), false);
        System.out.println("Edited " + this.thisFile.getName());
    }

    public String getLineValue(int lineNumber) throws FileNotFoundException, IOException
    {
        FileReader fr = new FileReader(this.thisFile);
        BufferedReader br = new BufferedReader(fr);

        String str;
        StringBuilder sb = new StringBuilder();
        int currentLine = 1;
        while ((str = br.readLine()) != null)
        {
            if (currentLine == lineNumber) {
                br.close();
                return str;
            }
            currentLine++;
        }
        br.close();
        System.out.println("Edited " + this.thisFile.getName());
        return null;
    }

    public String getLineValue(String lineStart) throws FileNotFoundException, IOException
    {
        FileReader fr = new FileReader(this.thisFile);
        BufferedReader br = new BufferedReader(fr);

        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null)
        {
            if (str.startsWith(lineStart))
            {
                br.close();
                return str;
            }
        }
        br.close();
        this.writeln(sb.toString(), false);
        System.out.println("Edited " + this.thisFile.getName());
        return null;
    }

    public void encode() throws IOException
    {
        UUEncoder encoder = new UUEncoder(thisFile.getPath());
        InputStream inputStream = new FileInputStream(thisFile.getPath());
        File f = new File("encode.txt");
        OutputStream outputStream = new FileOutputStream(f);
        encoder.encodeBuffer(inputStream, outputStream);
        thisFile.delete();
        f.renameTo(new File(this.path));
        inputStream.close();
        outputStream.close();
    }

    public void decode() throws IOException
    {
        UUDecoder encoder = new UUDecoder();
        InputStream inputStream = new FileInputStream(thisFile.getPath());
        File f = new File("decode.txt");
        OutputStream outputStream = new FileOutputStream(f);
        encoder.decodeBuffer(inputStream, outputStream);
        thisFile.delete();
        f.renameTo(new File(this.path));
        inputStream.close();
        outputStream.close();
    }

    public File getFile()
    {
        return thisFile;
    }
}
