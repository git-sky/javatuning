package javatuning.ch3.directbuffer;

import org.junit.Test;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class TestBufferAllocate {

    private void monDirectBuffer() throws ClassNotFoundException, Exception, NoSuchFieldException {
        Class clazz = Class.forName("java.nio.Bits");
        Field maxMemory = clazz.getDeclaredField("maxMemory");
        maxMemory.setAccessible(true);
        Field reservedMemory = clazz.getDeclaredField("reservedMemory");
        reservedMemory.setAccessible(true);
        synchronized (clazz) {
            Long maxMemoryValue = (Long) maxMemory.get(null);
            Long reservedMemoryValue = (Long) reservedMemory.get(null);
            System.out.println("maxMemoryValue:" + maxMemoryValue);
            System.out.println("reservedMemoryValue:" + reservedMemoryValue);
        }
    }

    @Test
    public void testDirectAllocate() throws Exception {
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            ByteBuffer b = ByteBuffer.allocateDirect(1000);
            monDirectBuffer();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testDirectAllocate:" + (endTime - begTime));
    }

    @Test
    public void testBufferAllocate() {
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            ByteBuffer b = ByteBuffer.allocate(1000);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testBufferAllocate:" + (endTime - begTime));
    }

}
