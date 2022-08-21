package cn.oddworld.netty.test02;

import java.nio.ByteBuffer;

public class ReadOnlyBuffer {

    public static void main(String[] args) {
        ByteBuffer allocate = ByteBuffer.allocate(10);
        for(int i =0; i< 10;i++){
            allocate.put((byte)i);
        }
        allocate.flip();
        // 只读buffer
        ByteBuffer readOnlyBuffer = allocate.asReadOnlyBuffer();

        System.out.println(allocate);
        System.out.println(readOnlyBuffer);
        /**
         * java.nio.HeapByteBuffer[pos=0 lim=10 cap=10]
         * java.nio.HeapByteBufferR[pos=0 lim=10 cap=10]
         */
    }
}
