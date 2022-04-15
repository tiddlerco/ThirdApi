package com.coderbuff.third2resttemplateprop.study.hashalgorithm;

import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.util.Set;

/**
 * Description:
 * 需要jar:
 *         <dependency>
 *             <groupId>com.google.guava</groupId>
 *             <artifactId>guava</artifactId>
 *             <version>23.0</version>
 *         </dependency>
 *
 * @author yuke
 * @date 2020-08-25
 */
public class MurmurHashTest {


    public static void main(String[] args) {

        int testCount = 10;
        String msg = "诸葛小猿";

        long totalTime = 0L;

        long start = System.currentTimeMillis();

        // Hashing.murmur3_32(seed)
        HashFunction murmur3 = Hashing.murmur3_32();

        Set<String> set = Sets.newHashSet();
        for (int i = 0; i < testCount; i++) {

            // 计算每一次hash的耗时
            Stopwatch w = Stopwatch.createStarted();

            HashCode murmur3HashCode = murmur3.hashString(msg + i, Charsets.UTF_8);
            int i1 = Hashing.consistentHash(murmur3HashCode, 5);
            System.out.println("当前的bucket是："+i1);
            String murmur3HashCodeStr = murmur3HashCode.toString();

            System.out.println(String.format("murmur3's hashCode:%s,length:%s,it consumes:%s", murmur3HashCodeStr, murmur3HashCodeStr.length(), w));

            set.add(murmur3HashCodeStr);
        }

        long end = System.currentTimeMillis();

        totalTime = end - start;

        System.out.println("set的元素个数" + set.size());

        System.out.println("总耗时" + totalTime);

    }

}
