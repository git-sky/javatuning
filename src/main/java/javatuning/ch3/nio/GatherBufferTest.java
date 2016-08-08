package javatuning.ch3.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GatherBufferTest {

    private static final String TPATH = "c:/gather.txt";
    private static int bookLen = 0;
    private static int authLen = 0;

    @Test
    public void testGatherData() throws Exception {
        ByteBuffer bookBuf = ByteBuffer.wrap("java�����Ż�����".getBytes("utf-8"));
        ByteBuffer autBuf = ByteBuffer.wrap("��һ��".getBytes("utf-8"));
        bookLen = bookBuf.limit();
        authLen = autBuf.limit();
        ByteBuffer[] bbArray = new ByteBuffer[]{bookBuf, autBuf};
        File file = new File(TPATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        FileChannel fc = fos.getChannel();
        fc.write(bbArray);
        fos.close();
    }

    @Test
    public void testScatterData() throws Exception {
        ByteBuffer b1 = ByteBuffer.allocate(bookLen);
        ByteBuffer b2 = ByteBuffer.allocate(authLen);
        ByteBuffer[] bufs = new ByteBuffer[]{b1, b2};
        File file = new File(TPATH);
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        fc.read(bufs);
        String bookName = new String(bufs[0].array(), "utf-8");
        String authName = new String(bufs[1].array(), "utf-8");
        System.out.println(bookName + authName);
    }

}
