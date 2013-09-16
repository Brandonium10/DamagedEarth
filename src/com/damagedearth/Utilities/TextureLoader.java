package com.damagedearth.Utilities;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader
{
    private static final int BYTES_PER_PIXEL = 4;

    public static int loadTexture(BufferedImage image)
    {

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL);

        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {

                int index = pixels[y * image.getWidth() + x];

                byte v1 = (byte) ((index >> 16) & 0xFF);
                byte v2 = (byte) ((index >> 8) & 0xFF);
                byte v3 = (byte) (index & 0xFF);
                byte v4 = (byte) ((index >> 24) & 0xFF);

                buffer.put(v1);
                buffer.put(v2);
                buffer.put(v3);
                buffer.put(v4);
            }
        }

        buffer.flip(); //FOR THE LOVE OF GOD DO NOT FORGET THIS

        int textureID = glGenTextures(); //Generate texture ID
        glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID

        //Setup wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        //Send texel data to OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        //Return the texture ID so we can bind it later again
        return textureID;
    }

    public static BufferedImage loadImage(String loc)
    {
        try
        {
            return ImageIO.read(new File(loc));
        }
        catch (Exception e)
        {
            //Error Handling Here
            e.printStackTrace();
        }
        return null;
    }
}
