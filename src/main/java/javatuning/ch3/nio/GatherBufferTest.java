package javatuning.ch3.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GatherBufferTest {

    private static final String F_PATH = "./data/gather.txt";
    private static int bookLen = 0;
    private static int authLen = 0;

    @Test
    public void testGatherData() throws Exception {
        ByteBuffer bookBuf = ByteBuffer.wrap("java程序性能优化".getBytes("utf-8"));
        ByteBuffer autBuf = ByteBuffer.wrap("JengoWang".getBytes("utf-8"));
        bookLen = bookBuf.limit();
        authLen = autBuf.limit();
        ByteBuffer[] bbArray = new ByteBuffer[]{bookBuf, autBuf};
        File file = new File(F_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        FileChannel fc = fos.getChannel();
        fc.write(bbArray);
        fos.close();

        testScatterData();
    }

    @Test
    public void testScatterData() throws Exception {
        ByteBuffer b1 = ByteBuffer.allocate(bookLen);
        ByteBuffer b2 = ByteBuffer.allocate(authLen);
        ByteBuffer[] bfArray = new ByteBuffer[]{b1, b2};
        File file = new File(F_PATH);
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        fc.read(bfArray);
        String bookName = new String(bfArray[0].array(), "utf-8");
        String authName = new String(bfArray[1].array(), "utf-8");
        System.out.println(bookName + authName);
    }

}
