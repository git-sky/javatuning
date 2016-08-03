package javatuning.ch2.buffer;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileWriterBufferTest {

    public static final int CIRCLE = 100000;

    @Test
    public void testFileWriter() throws IOException {
        Writer writer = new FileWriter(new File("data/file.txt"));
        long beg = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            writer.write(i);
        }
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println("testFileWriter spend:" + (end - beg) + "ms");
    }

    @Test
    public void testFileWriterBuffer() throws IOException {
        Writer writer = new BufferedWriter(new FileWriter(new File("data/file.txt")));
        long beg = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            writer.write(i);
        }
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println("testFileWriterBuffer spend:" + (end - beg) + "ms");
    }

}
