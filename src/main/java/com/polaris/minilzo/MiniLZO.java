package com.polaris.minilzo;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.ptr.IntByReference;

public interface MiniLZO extends Library {

    public static class Inner {
        private static MiniLZO instance;
        static {
            try {
                System.out.println("当前系统前缀为:"+ Platform.RESOURCE_PREFIX);
                instance = Native.load("minilzo", MiniLZO.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static MiniLZO getInstance() {
        return Inner.instance;
    }

    // 压缩函数
    int lzo1x_1_compress(byte[] in, int in_len, byte[] out, IntByReference out_len, byte[] wrkmem);

    // 解压缩函数
    int lzo1x_decompress(byte[] in, int in_len, byte[] out, IntByReference out_len);


}
