package javatuning.ch3.directbuffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * -XX:MaxDirectMemorySize=10M -Xmx10m -Xloggc:c:\gc.log -XX:+PrintGC
 */
public class TestBufferBig {

    @Test
    public void testDirectWrite() {
        long begTime = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocateDirect(3 * 1024 * 1024);
        b = null;
        //System.gc();
        b = ByteBuffer.allocateDirect(3 * 1024 * 1024);
        ByteBuffer b1 = ByteBuffer.allocate(4 * 1024 * 1024);
    }

    @Test
    public void testBufferWrite() {
        long begTime = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocate(1 * 1024 * 1024);
    }

}
