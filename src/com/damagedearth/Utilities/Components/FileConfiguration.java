/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Utilities.Components;

import sun.misc.UUDecoder;
import sun.misc.UUEncoder;

import java.io.*;

public class FileConfiguration
{
    private File thisFile;
    private String path;

    public FileConfiguration(String fileName)
    {
        this.thisFile = new File(fileName);
        this.path = fileName;
    }

    /**
     * @param line         The text you want to add
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
    }

    public FileConfiguration appendLine(String lineStart, String line) throws IOException
    {
        this.editLine(lineStart, this.getLineValue(lineStart) + line);
        return this;
    }

    public String getLineValue(int lineNumber) throws NullPointerException, IOException
    {
        FileReader fr = new FileReader(this.thisFile);
        BufferedReader br = new BufferedReader(fr);

        String str;
        int currentLine = 1;
        while ((str = br.readLine()) != null)
        {
            if (currentLine == lineNumber)
            {
                br.close();
                return str;
            }
            currentLine++;
        }
        br.close();
        return null;
    }

    public String getLineValue(String lineStart) throws NullPointerException, IOException
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
        return null;
    }

    public boolean doesLineExists(String lineStart)
    {
        if (!this.getFile().exists())
        {
            return false;
        }
        try
        {
            if (this.getLineValue(lineStart).isEmpty())
            {
                return false;
            }
        }
        catch (NullPointerException e)
        {
            return false;
        }
        catch (IOException e)
        {
            return false;
        }
        return true;
    }

    public int getLastLine() throws FileNotFoundException, IOException
    {
        FileReader fr = new FileReader(this.thisFile);
        BufferedReader br = new BufferedReader(fr);

        String str;
        int lastLine = 0;
        while ((str = br.readLine()) != null)
        {
            if (!str.isEmpty())
            {
                lastLine++;
            }
        }
        br.close();
        return lastLine;
    }

    public void clear() throws FileNotFoundException, IOException
    {
        if (!this.getFile().exists()) return;

        FileOutputStream fop = null;
        String content = "";

        fop = new FileOutputStream(thisFile);

        // get the content in bytes
        byte[] contentInBytes = content.getBytes();

        fop.write(contentInBytes);
        fop.flush();
        fop.close();
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
