package cn.oddworld.netty.test01;

import java.nio.IntBuffer;

public class BufferTest {

    public static void main(String[] args) {
        final IntBuffer allocate = IntBuffer.allocate(5);
        allocate.put(1);
        allocate.put(2);
        allocate.put(3);
        allocate.mark();
        int position = allocate.position();
        System.out.println(position);
        allocate.put(5);
        allocate.put(4);
        // allocate.put(6); 多一个会抛出异常：BufferOverflowException
        // 切换读模式

        int position1 = allocate.position();
        System.out.println(position1);
        allocate.put(5);
        allocate.put(4);
        allocate.flip();

        while (allocate.hasRemaining()){
            System.out.println(allocate.get());
        }
    }
}
