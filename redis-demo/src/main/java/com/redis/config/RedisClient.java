package com.redis.config;

import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.*;
import org.springframework.util.CollectionUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RedisClient {

    /**
     * 默认编码
     */
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private static ValueOperations<String, Object> valueOperations;

    private static HashOperations<String, String, String> hashOperations;

    private static SetOperations<String, String> setOperations;

    private static RedisTemplate redisTemplate;

    private static ListOperations<String, String> listOperations;

    private static ZSetOperations<String, String> zSetOperations;

    @Autowired
    public RedisClient(ValueOperations<String, Object> valueOperations,
                       HashOperations<String, String, String> hashOperations, SetOperations<String, String> setOperations,
                       RedisTemplate redisTemplate, ListOperations<String, String> listOperations,
                       ZSetOperations<String, String> zSetOperations) {
        RedisClient.valueOperations = valueOperations;
        RedisClient.hashOperations = hashOperations;
        RedisClient.setOperations = setOperations;
        RedisClient.redisTemplate = redisTemplate;
        RedisClient.listOperations = listOperations;
        RedisClient.zSetOperations = zSetOperations;
    }

    public static Boolean del(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return redisTemplate.delete(key);
    }

    public static void delMatching(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        Set<String> keys = redisTemplate.keys(key + "*");
        redisTemplate.delete(keys);
    }

    public static Boolean delIfAbsent(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        byte[] keyByteArray = redisTemplate.getKeySerializer().serialize(key);
        return (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) {
                return connection.del(keyByteArray) > 0;
            }

        }, true);
    }

    public static void expire(String keyFormat, Integer seconds, String... keyValues) {
        String key = format(keyFormat, keyValues);
        redisTemplate.expire(key, (long) seconds.intValue(), TimeUnit.SECONDS);
    }

    public static void expire(String keyFormat, Long seconds, String... keyValues) {
        String key = format(keyFormat, keyValues);
        redisTemplate.expire(key, seconds.intValue(), TimeUnit.SECONDS);
    }

    public static void expireAt(String keyFormat, Date date, String... keyValues) {
        String key = format(keyFormat, keyValues);
        redisTemplate.expireAt(key, date);
    }

    public static void set(String keyFormat, Object value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        valueOperations.set(key, value);
    }

    public static void convertAndSend(String keyFormat, Object value) {
        redisTemplate.convertAndSend(keyFormat, value);
    }

    public static boolean setnx(String keyFormat, Object value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return valueOperations.setIfAbsent(key, value).booleanValue();
    }

    public static boolean setnxTime(String keyFormat, Object value, long expireSeconds, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return valueOperations.setIfAbsent(key, value, expireSeconds, TimeUnit.SECONDS);
    }

    public static void set(String keyFormat, Object value, long expireSeconds, String... keyValues) {
        String key = format(keyFormat, keyValues);
        valueOperations.set(key, value, expireSeconds, TimeUnit.SECONDS);
    }

    public static <T> T get(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        Object o = valueOperations.get(key);
        return o != null ? (T) o : null;
    }

    public static <T> T mGet(Collection keys) {
        Object o = valueOperations.multiGet(keys);
        return o != null ? (T) o : null;
    }

    public static List<String> hmGet(String keyFormat, List<String> keys, String... keyValues) {
        String key = format(keyFormat, keyValues);
        List<String> list = hashOperations.multiGet(key, keys);
        List<String> collect = list.stream().filter(a -> a != null).collect(Collectors.toList());
        return !CollectionUtils.isEmpty(collect) ? collect : null;
    }

    public static void mSet(Map<String, ?> map) {
        valueOperations.multiSet(map);
    }

    public static String getString(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        String result = (String) valueOperations.get(key);
        return result;
    }

    public static void setString(String keyFormat, String value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        valueOperations.set(key, String.valueOf(value));
    }

    public static Long getNumber(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        String result = (String) valueOperations.get(key);
        return result != null ? Long.valueOf(result) : Long.valueOf(0L);
    }

    public static void setNumber(String keyFormat, Long value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        valueOperations.set(key, String.valueOf(value));
    }

    public static void setNumber(String keyFormat, Long value, long expireSeconds, String... keyValues) {
        String key = format(keyFormat, keyValues);
        valueOperations.set(key, value, expireSeconds, TimeUnit.SECONDS);
    }

    public static Long incrBy(String keyFormat, Long value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return valueOperations.increment(key, value.longValue());
    }

    public static Long decrBy(String keyFormat, Long value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return valueOperations.increment(key, -value.longValue());
    }

    public static Set<String> sMembers(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        Set<String> values = setOperations.members(key);
        return values;
    }

    public static Long scard(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        Long count = setOperations.size(key);
        return count == null ? Long.valueOf(0L) : count;
    }

    public static Set<String> sUnion(Set<String> keys) {
        String key = "";
        Set<String> values = setOperations.union(key, keys);
        return values;
    }

    public static Long sUnionAndStore(Set<String> keys, String resultKey) {
        if (CollectionUtils.isEmpty(keys)) {
            return Long.valueOf(-1L);
        } else {
            String key = (String) keys.iterator().next();
            keys.remove(key);
            Long result = setOperations.unionAndStore(key, keys, resultKey);
            return result;
        }
    }

    public static Long zUnionAndStore(Set<String> keys, String resultKey) {
        if (CollectionUtils.isEmpty(keys)) {
            return Long.valueOf(-1L);
        } else {
            String key = (String) keys.iterator().next();
            keys.remove(key);
            Long result = zSetOperations.unionAndStore(key, keys, resultKey);
            return result;
        }
    }

    public static Set<String> sDiff(String key1, String key2) {
        Set<String> values = setOperations.difference(key1, key2);
        return values;
    }

    public static Long sAdd(String key1, String value, String... keyValues) {
        String key = format(key1, keyValues);
        Long result = setOperations.add(key, new String[]{value});
        return result;
    }

    public static Long sAddAll(String key1, Collection<String> values, String... keyValues) {
        String key = format(key1, keyValues);
        String[] valuesCopy = new String[values.size()];
        values.toArray(valuesCopy);
        Long result = setOperations.add(key, valuesCopy);
        return result;
    }

    public static Long sRem(String key1, String value, String... keyValues) {
        String key = format(key1, keyValues);
        return setOperations.remove(key, new Object[]{value});
    }

    public static String sPop(String key1, String... keyValues) {
        String key = format(key1, keyValues);
        String result = (String) setOperations.pop(key);
        return result;
    }

    public static Long hIncrBy(String keyFormat, String field, long value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return hashOperations.increment(key, field, value);
    }

    public static void hDel(String keyFormat, String field, String... keyValues) {
        String key = format(keyFormat, keyValues);
        hashOperations.delete(key, field);
    }

    public static void hDelAll(String keyFormat, String[] field, String... keyValues) {
        String key = format(keyFormat, keyValues);
        hashOperations.delete(key, field);
    }

    public static Map<String, String> hGetAll(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        Map<String, String> result = hashOperations.entries(key);
        return result;
    }

    public static String hGet(String keyFormat, String hashKey, String... keyValues) {
        String key = format(keyFormat, keyValues);
        String result = (String) hashOperations.get(key, hashKey);
        return result;
    }

    public static void hPut(String keyFormat, String hashKey, String value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        hashOperations.put(key, hashKey, value);
    }

    public static void hPutAll(String keyFormat, Map<String, String> map, String... keyValues) {
        String key = format(keyFormat, keyValues);
        hashOperations.putAll(key, map);
    }

    public static String format(String formatKey, String... keyValues) {
        if (keyValues != null && keyValues.length != 0) {
            StringBuilder key = new StringBuilder();
            char[] chars = formatKey.toCharArray();
            int index = -1;
            boolean inmark = false;
            boolean firstinmark = false;

            for (int i = 0; i < chars.length; ++i) {
                char ch = chars[i];
                if (ch == 123) {
                    ++index;
                    inmark = true;
                    firstinmark = true;
                } else if (ch == 125) {
                    inmark = false;
                } else if (inmark) {
                    if (firstinmark) {
                        firstinmark = false;
                        key.append(keyValues[index]);
                    }
                } else {
                    key.append(chars[i]);
                }
            }

            return key.toString();
        } else {
            return formatKey;
        }
    }

    public static String stepFormat(String formatKey, String... keyValues) {
        if (keyValues != null && keyValues.length != 0) {
            StringBuilder key = new StringBuilder();
            char[] chars = formatKey.toCharArray();
            int index = -1;
            boolean inmark = false;
            boolean firstinmark = false;

            for (int i = 0; i < chars.length; ++i) {
                char ch = chars[i];
                if (ch == 123) {
                    ++index;
                    inmark = true;
                    firstinmark = true;
                } else if (ch == 125) {
                    inmark = false;
                } else if (inmark) {
                    if (firstinmark) {
                        firstinmark = false;
                        if (index < keyValues.length) {
                            key.append(keyValues[index]);
                        } else {
                            key.append(chars, i - 1, chars.length - i + 1);
                        }
                    }
                } else {
                    key.append(chars[i]);
                }
            }

            return key.toString();
        } else {
            return formatKey;
        }
    }

    public static void lPush(String keyFormat, String value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        listOperations.leftPush(key, value);
    }

    public static void rPush(String keyFormat, String value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        listOperations.rightPush(key, value);
    }

    public static Long rPushAll(String keyFormat, Collection<String> value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return listOperations.rightPushAll(key, value);
    }

    public static void lPushAll(String keyFormat, Collection<String> value, String... keyValues) {
        String key = format(keyFormat, keyValues);
        listOperations.leftPushAll(key, value);
    }

    public static String lPop(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return listOperations.leftPop(key);
    }

    public static Long lLen(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return listOperations.size(key);
    }

    public static Long lCount(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return listOperations.size(key);
    }

    public static String brPop(String key1, String key2) {
        return listOperations.rightPopAndLeftPush(key1, key2);
    }

    public static String lindex(String key, Long index) {
        return listOperations.index(key, index);
    }

    public static List<String> lRange(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return listOperations.range(key, 0, -1);
    }

    public static List<String> lRange(String keyFormat, int start, int end, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return listOperations.range(key, start, end);
    }

    public static Long lSize(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return listOperations.size(key);
    }

    /**
     * 为Zset的键值添加分数
     *
     * @param keyFormat 键
     * @param value     键值
     * @param score     分数
     */
    public static Double incrementScore(String keyFormat, String value, double score, String... keyValue) {
        String key = format(keyFormat, keyValue);
        return zSetOperations.incrementScore(key, value, score);
    }

    /**
     * 获取索引区间内的元素
     *
     * @param keyFormat 键
     * @param begin     键值
     * @param end       分数
     */
    public static Set<ZSetOperations.TypedTuple<String>> rangeWithScores(String keyFormat, int begin, int end,
                                                                         String... keyValue) {
        String key = format(keyFormat, keyValue);
        return zSetOperations.rangeWithScores(key, begin, end);
    }

    /**
     * 倒序获取索引区间内的元素
     *
     * @param keyFormat 键
     * @param begin     键值
     * @param end       分数
     */
    public static Set<ZSetOperations.TypedTuple<String>> reverseRangeWithScores(String keyFormat, int begin, int end,
                                                                                String... keyValue) {
        String key = format(keyFormat, keyValue);
        return zSetOperations.reverseRangeWithScores(key, begin, end);
    }

    /**
     * 获取索引区间内的元素
     *
     * @param keyFormat 键
     * @param value     键值
     */
    public static Long reverseRank(String keyFormat, String value, String... keyValue) {
        String key = format(keyFormat, keyValue);
        return zSetOperations.reverseRank(key, value);
    }

    /**
     * 获取指定元素的分数
     *
     * @param keyFormat 键
     * @param value     键值
     */
    public static Double score(String keyFormat, String value, String... keyValue) {
        String key = format(keyFormat, keyValue);
        return zSetOperations.score(key, value);
    }

    /**
     * redis addZSet数据结构 : 将ZSet元素add
     *
     * @param keyFormat the key
     * @param hashValue the hashValue
     * @param score     分数
     */
    public static void addZSet(String keyFormat, String hashValue, double score, String... keyValue) {
        String key = format(keyFormat, keyValue);
        zSetOperations.add(key, hashValue, score);
    }

    /**
     * redis removeZSet数据结构 : 删除ZSet下某个元素
     *
     * @param keyFormat the key
     * @param hashValue the hashValue
     */
    public static Long zSetRemove(String keyFormat, String hashValue, String... keyValue) {
        String key = format(keyFormat, keyValue);
        return zSetOperations.remove(key, hashValue);
    }

    public static Boolean hasKey(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return redisTemplate.hasKey(key);
    }

    public static Long zSetCount(String keyFormat, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return zSetOperations.size(key);
    }

    public static Long zSetRemoveRangeByScore(String keyFormat, double min, double max, String... keyValues) {
        String key = format(keyFormat, keyValues);
        return zSetOperations.removeRangeByScore(key, min, max);
    }

    public static RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 获得Set两个键值的差值
     *
     * @param key      键
     * @param otherKey 开始
     * @return 区间列表
     */
    public static Set<String> sDifference(String key, String otherKey) {
        return setOperations.difference(key, otherKey);
    }

    /**
     * 随机活得N个Set列表
     *
     * @param keyFormat 键
     * @param num       开始
     * @return 区间列表
     */
    public static Set<String> sDistinctRandomMembers(String keyFormat, long num, String... keyValue) {
        String key = format(keyFormat, keyValue);
        return setOperations.distinctRandomMembers(key, num);
    }

    /**
     * 随机活得N个Set列表
     *
     * @param keyFormat   键
     * @param geoLocation 开始
     */
    public static void geoAdd(String keyFormat, RedisGeoCommands.GeoLocation<String> geoLocation, String... keyValue) {
        String key = format(keyFormat, keyValue);
        redisTemplate.opsForGeo().add(key, geoLocation);
    }

    /**
     * 随机活得N个Set列表
     *
     * @param keyFormat 键
     * @param value     值
     */
    public static void geoRemove(String keyFormat, String value, String... keyValue) {
        String key = format(keyFormat, keyValue);
        redisTemplate.opsForGeo().remove(key, value);
    }

    /**
     * 获得Redis geo中 N距离内的人
     *
     * @param keyFormat 键
     * @param within    值
     */
    public static GeoResults<RedisGeoCommands.GeoLocation<String>> geoRadius(String keyFormat, Circle within,
                                                                             RedisGeoCommands.GeoRadiusCommandArgs args, String... keyValue) {
        String key = format(keyFormat, keyValue);
        return redisTemplate.opsForGeo().radius(key, within, args);
    }

    /**
     * 判断某个主键是否存在
     *
     * @param key the key
     * @return the boolean
     */
    public static Boolean exists(final String key) {
        return (Boolean) redisTemplate
                .execute((RedisCallback<Boolean>) connection -> connection.exists(key.getBytes(DEFAULT_CHARSET)));
    }

    /**
     * 判断某个主键是否存在
     *
     * @param keyFormat
     * @param hashKey
     * @param keyValue
     * @return the boolean
     */
    public static Boolean hExists(String keyFormat, String hashKey, String... keyValue) {
        String key = format(keyFormat, keyValue);
        return hashOperations.hasKey(key, hashKey);
    }

    /**
     * 获得ZSet区间列表
     *
     * @param key   键
     * @param begin 开始
     * @param end   结束
     * @return 区间列表
     */
    public static Set<String> zSetRange(String key, long begin, long end) {
        return zSetOperations.range(key, begin, end);
    }

    /**
     * @param keyFormat
     * @param minScore  最小分数
     * @param maxScore  最大分数
     * @param keyValue
     * @return
     */
    public static Set<String> zSetRangeByScore(String keyFormat, int minScore, int maxScore, String... keyValue) {
        String key = format(keyFormat, keyValue);
        return zSetOperations.rangeByScore(key, minScore, maxScore);
    }


    public static Long zCountAllScore(String keyFormat, int start, int end, String... keyValues) {
        String key = format(keyFormat, keyValues);
        Set<ZSetOperations.TypedTuple<String>> typedTuples = zSetOperations.reverseRangeWithScores(key, start, end);
        return CollectionUtils.isEmpty(typedTuples) ? 0L : typedTuples.stream().mapToLong(typed -> typed.getScore().longValue()).sum();
    }

    /**
     * redis addSet数据结构 : 将Set元素add
     *
     * @param keyFormat the key
     * @param hashValue the hashValue
     */
    public static void addSet(String keyFormat, String hashValue, String... keyValue) {
        String key = format(keyFormat, keyValue);
        setOperations.add(key, hashValue);
    }

    /**
     * redis addSet数据结构 : 将Set元素add
     *
     * @param key       the key
     * @param hashValue the hashValue
     */
    public static Long sAddAll(String key, String... hashValue) {
        return setOperations.add(key, hashValue);
    }

    /**
     * redis deleteSet数据结构 : 将Set元素delete
     *
     * @param keyFormat
     * @param hashValue
     */
    public static void sDel(String keyFormat, String hashValue, String... keyValue) {
        String key = format(keyFormat, keyValue);
        setOperations.remove(key, hashValue);
    }

    /**
     * redis deleteSet数据结构 : 将Set元素delete
     *
     * @param key
     * @param hashValue
     */
    public static void sDelAll(String key, String... hashValue) {
        setOperations.remove(key, hashValue);
    }

    /**
     * redis Hash数据结构 : 返回列表 key 的长度 ; 如果 key 不存在，则 key 被解释为一个空列表，返回 0 ; 如果 key 不是列表类型，返回一个错误。
     *
     * @param keyFormat the key
     * @param keyValue  the key value
     * @return the long
     */
    public static Long hSize(String keyFormat, String... keyValue) {
        String key = format(keyFormat, keyValue);
        return hashOperations.size(key);
    }

    /**
     * redis setIfAbsent : 如果不存在则插入
     *
     * @param key   键
     * @param value 值
     * @return Boolean
     */
    public static Boolean setIfAbsent(String key, String value) {

        return valueOperations.setIfAbsent(key, value);
    }

    /**
     * redis rename 替换名称
     *
     * @param oldKey
     * @param newKey
     */
    public static void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * redis rename 替换名称
     *
     * @param key
     * @return
     */
    public static Set<String> keys(String key) {
        return redisTemplate.keys(key);
    }

    /**
     * 弹出元素到另一个集合
     *
     * @param keyFormat 键
     * @param value     数量
     * @return 区间列表
     */
    public static void sMove(String keyFormat, String value, String targetKey, String... keyValue) {
        String key = format(keyFormat, keyValue);
        setOperations.move(key, value, targetKey);
    }

    /**
     * 添加多个到集合中
     *
     * @param keyFormat 键
     * @param value     值
     * @return 区间列表
     */
    public static void putAll(String keyFormat, Map<String, String> value, String... keyValue) {
        String key = format(keyFormat, keyValue);
        hashOperations.putAll(key, value);
    }

    /**
     * 获取队列长度
     *
     * @param keyFormat 键
     * @return 区间列表
     */
    public static Long sSize(String keyFormat, String... keyValue) {
        String key = format(keyFormat, keyValue);
        return setOperations.size(key);
    }

    /**
     * 获取队列长度
     *
     * @param key 键
     * @return 区间列表
     */
    public static Set<String> setUnion(Set<String> key) {
        return setOperations.union(key);
    }

    /**
     * 随机获得
     *
     * @param key 键
     * @return 区间列表
     */
    public static String sRandomMember(String key) {
        return setOperations.randomMember(key);
    }

    /**
     * 获取redis键的过期时间
     *
     * @return 区间列表
     */
    public static Long getRedisExpire(String keyFormat, String... keyValue) {
        String key = format(keyFormat, keyValue);
        return valueOperations.getOperations().getExpire(key);
    }

    /**
     * list的lTrim命令
     *
     * @param keyFormat
     * @param start
     * @param end
     * @param keyValues
     */
    public static void lTrim(String keyFormat, long start, long end, String... keyValues) {
        String key = format(keyFormat, keyValues);
        listOperations.trim(key, start, end);
    }


    /*********************************************************************************
     *
     * 对bitmap的操作
     *
     ********************************************************************************/

    /**
     * 将指定param的值设置为1，{@param param}会经过hash计算进行存储。
     *
     * @param key   bitmap结构的key
     * @param param 要设置偏移的key，该key会经过hash运算。
     * @param value true：即该位设置为1，否则设置为0
     * @return 返回设置该value之前的值。
     */
    public static Boolean setBit(String key, Long param, boolean value) {
        return valueOperations.setBit(key, hashLong(param), value);
    }

    /**
     * 将指定param的值设置为0，{@param param}会经过hash计算进行存储。
     *
     * @param key   bitmap结构的key
     * @param param 要移除偏移的key，该key会经过hash运算。
     * @return 若偏移位上的值为1，那么返回true。
     */
    public static Boolean getBit(String key, Long param) {
        return valueOperations.getBit(key, hashLong(param));
    }


    /**
     * 统计对应的bitmap上value为1的数量
     *
     * @param key bitmap的key
     * @return value等于1的数量
     */
    public static Long bitCount(String key) {
        Object execute = redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
        return Long.valueOf(String.valueOf(execute));
    }

    /**
     * 统计指定范围中value为1的数量
     *
     * @param key   bitMap中的key
     * @param start 该参数的单位是byte（1byte=8bit），{@code setBit(key,7,true);}进行存储时，单位是bit。那么只需要统计[0,1]便可以统计到上述set的值。
     * @param end   该参数的单位是byte。
     * @return 在指定范围[start*8,end*8]内所有value=1的数量
     */
    public static Long bitCount(String key, int start, int end) {
        Object execute = redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes(), start, end));
        return Long.valueOf(String.valueOf(execute));
    }


    /**
     * 对一个或多个保存二进制的字符串key进行元操作，并将结果保存到saveKey上。
     * <p>
     * bitop and saveKey key [key...]，对一个或多个key逻辑并，结果保存到saveKey。
     * bitop or saveKey key [key...]，对一个或多个key逻辑或，结果保存到saveKey。
     * bitop xor saveKey key [key...]，对一个或多个key逻辑异或，结果保存到saveKey。
     * bitop xor saveKey key，对一个或多个key逻辑非，结果保存到saveKey。
     * <p>
     *
     * @param op      元操作类型；
     * @param saveKey 元操作后将结果保存到saveKey所在的结构中。
     * @param desKey  需要进行元操作的类型。
     * @return 1：返回元操作值。
     */
    public static Long bitOp(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        byte[][] bytes = new byte[desKey.length][];
        for (int i = 0; i < desKey.length; i++) {
            bytes[i] = desKey[i].getBytes();
        }
        Object execute = redisTemplate.execute((RedisCallback<Long>) con -> con.bitOp(op, saveKey.getBytes(), bytes));
        return Long.valueOf(String.valueOf(execute));
    }

    /**
     * 对一个或多个保存二进制的字符串key进行元操作，并将结果保存到saveKey上，并返回统计之后的结果。
     *
     * @param op      元操作类型；
     * @param saveKey 元操作后将结果保存到saveKey所在的结构中。
     * @param desKey  需要进行元操作的类型。
     * @return 返回saveKey结构上value=1的所有数量值。
     */
    public static Long bitOpResult(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        bitOp(op, saveKey, desKey);
        return bitCount(saveKey);
    }

    /**
     * guava依赖获取hash值。
     */
    private static long hash(String key) {
        Charset charset = Charset.forName("UTF-8");
        return Math.abs(Hashing.murmur3_32().hashObject(key, Funnels.stringFunnel(charset)).asInt());
    }

    private static long hashLong(Long key) {
        HashCode hashCode = Hashing.murmur3_32().hashLong(key);
        long i1 = Math.abs(Hashing.consistentHash(hashCode, 10000000));
        return i1;
    }

    public static void main(String[] args) {

        System.out.println(ONE_BITMAP_SIZE);
        System.out.println(1024*1024);
        long r1=223456;
        BitMapKey u1 = computeUserGroup(r1, ONE_BITMAP_SIZE, SHARD_COUNT);
        System.out.println(u1);

        long r2=123456;
        BitMapKey u2 = computeUserGroup(r2, ONE_BITMAP_SIZE, SHARD_COUNT);
        System.out.println(u2);


        String redisKey = u2.generateKeyWithPrefix("ttt");
        System.out.println(redisKey);

        long hash = hashLong(851812399886368000L);

        System.out.println("hash==="+hash);


    }

    // 单个bitmap占用1M内存
    // 如果useId < 100亿， 则会分到7000个分组里
    private static final int ONE_BITMAP_SIZE = 1 << 20;
    // 同一个分组里的的useId划分到20个bitmap里
    // 避免出现范围用户太多导致查询时出现热key
    private static final int SHARD_COUNT = 20;

    // 计算用户的 raw， shard， 和对应的offset
    public static BitMapKey computeUserGroup(long userId, int oneBitMapSize, int shardCount) {
        //获取组
        long groupIndex = userId / oneBitMapSize;
        //获取分片位置
        int shardIndex = Math.abs((int) (hash(userId+"") % shardCount));
        //获取（组-分片）下的offset位置
        int bitIndex = (int) (userId - groupIndex * oneBitMapSize);
        //获取到对象
        return new BitMapKey((int) groupIndex, shardIndex, bitIndex);
    }

    @Data
    public static class BitMapKey {
        /**
         * 组
         */
        private final int groupIndex;
        /**
         * 组中分片
         */
        private final int shardIndex;
        /**
         *
         */
        private final int bitIndex;

        public BitMapKey(int groupIndex, int shardIndex, int bitIndex) {
            this.groupIndex = groupIndex;
            this.shardIndex = shardIndex;
            this.bitIndex = bitIndex;
        }

        public int getBitIndex() {
            return bitIndex;
        }

        public String generateKeyWithPrefix(String prefix) {
            return String.join(":", prefix, groupIndex + "", shardIndex + "");
        }
    }

}
