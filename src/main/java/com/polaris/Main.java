package com.polaris;

import com.polaris.minilzo.MiniLzoUtils;
import com.sun.jna.Platform;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(Platform.RESOURCE_PREFIX);
        // 测试数据
        byte[] bytes = new String("hello world").getBytes();

        byte[] compress = MiniLzoUtils.compress(bytes);
        System.out.println("压缩后的数据为:" + Arrays.toString(compress));

        byte[] decompress = MiniLzoUtils.decompress(compress);
        System.out.println(Arrays.toString(decompress));
    }
}
