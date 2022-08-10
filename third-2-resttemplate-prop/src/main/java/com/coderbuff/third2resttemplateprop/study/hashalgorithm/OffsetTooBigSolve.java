package com.coderbuff.third2resttemplateprop.study.hashalgorithm;

import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;


/**
 * @Author yuke
 * @Date 2022/7/22 10:12
 * @Description: bitmap offset过长的解决方案
 * 根据一致性hash来解决
 */
public class OffsetTooBigSolve {

    public static void main(String[] args) {

        // Hashing.murmur3_32(seed)
        HashFunction murmur3 = Hashing.murmur3_32();

        HashCode murmur3HashCode = murmur3.hashLong(999907182432485376L);
        long i1 = Hashing.consistentHash(murmur3HashCode, 60000000);
        System.out.println("当前的bucket是：" + i1);

    }

}
