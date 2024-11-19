package com.polaris.minilzo;

import com.sun.jna.ptr.IntByReference;

public class MiniLzoUtils {

    public static byte[] compress(byte[] data) {
        byte[] bytes = new byte[data.length + (data.length / 16) + 64 + 3];
        byte[] wrkmem = new byte[data.length * 64 + 2048];
        IntByReference intByReference = new IntByReference();
        // 调用
        int i = MiniLZO.getInstance().lzo1x_1_compress(data, data.length, bytes, intByReference, wrkmem);
        if (i == 0) {
            byte[] res = new byte[intByReference.getValue()];
            System.arraycopy(bytes, 0, res, 0, res.length);
            return res;
        } else {
            throw new RuntimeException("压缩失败");
        }
    }

    public static byte[] decompress(byte[] data) {
        byte[] bytes = new byte[data.length * 5];
        IntByReference intByReference = new IntByReference();
        for (; ; ) {
            // 调用
            int i = MiniLZO.getInstance().lzo1x_decompress(data, data.length, bytes, intByReference);
            if (i == 0) {
                // 如果解压缩后的长度大于原始数据的长度，则说明数据被截断了，需要重新解压缩
                if (intByReference.getValue() > bytes.length) {
                    bytes = new byte[intByReference.getValue()];
                    continue;
                }
                // 如果分配后的数组等于原始数据的长度，则说明数据没有被截断，直接返回
                if (intByReference.getValue() == bytes.length) {
                    return bytes;
                }
                byte[] res = new byte[intByReference.getValue()];
                System.arraycopy(bytes, 0, res, 0, res.length);
                return res;
            } else {
                throw new RuntimeException("解压缩失败");
            }
        }

    }
}
