package com.damagedearth.Utilities;

import sun.misc.UUDecoder;
import sun.misc.UUEncoder;

import java.io.*;

public class Encoder
{
    public static void encode(String inFile, String outFile) throws Exception
    {
        UUEncoder encoder = new UUEncoder(inFile);
        InputStream inputStream = new FileInputStream(inFile);
        OutputStream out = new FileOutputStream(outFile);
        encoder.encodeBuffer(inputStream, out);
        File f = new File(inFile);
        f.delete();
    }

    public static void decode(String inFile, String outFile)
    {
        try
        {
            UUDecoder uudc = new UUDecoder();
            InputStream in = new FileInputStream(inFile);
            OutputStream out = new FileOutputStream(outFile);
            uudc.decodeBuffer(in, out);
            in.close();
            out.close();
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
    }
}
