package com.polaris.minilzo;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

public interface MiniLZO extends Library {

    MiniLZO instance = Native.load("minilzo", MiniLZO.class);

    public static MiniLZO getInstance() {
        return instance;
    }

    // 压缩函数
    int lzo1x_1_compress(byte[] in, int in_len, byte[] out, IntByReference out_len, byte[] wrkmem);

    // 解压缩函数
    int lzo1x_decompress(byte[] in, int in_len, byte[] out, IntByReference out_len);


}
