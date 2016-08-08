package javatuning.ch3.nio;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestCopyFile {

    public static void nioCopyFile(String resource, String destination) throws IOException {
        FileInputStream fis = new FileInputStream(resource);
        FileOutputStream fos = new FileOutputStream(destination);
        FileChannel readChannel = fis.getChannel();
        FileChannel writeChannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 8);
        while (true) {
            buffer.clear();
            int len = readChannel.read(buffer);
            if (len == -1) {
                break;
            }
            buffer.flip();
            writeChannel.write(buffer);
        }
        readChannel.close();
        writeChannel.close();
    }

    public static void ioCopyFile(String resource, String destination) throws IOException {
        FileInputStream fis = new FileInputStream(resource);
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(destination));
        //FileOutputStream fos = new FileOutputStream(destination);
        byte[] buffer = new byte[1024 * 8];
        int count = 0;
        while ((count = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, count);
        }
        fos.close();
        fis.close();
    }

    @Test
    public void testIoCopyFile() throws IOException {
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            ioCopyFile("./data/file.txt", "./data/file_copy_io.txt");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ioCopy spend:" + (endTime - begTime));

        begTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            nioCopyFile("./data/file.txt", "./data/file_copy_nio.txt");
        }
        endTime = System.currentTimeMillis();
        System.out.println("nioCopy spend:" + (endTime - begTime));
    }

}
