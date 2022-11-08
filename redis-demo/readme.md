# 加锁

1.setnx：独占排他 死锁、不可重入，原子性

2. setkv ex 30 nx：独占排他、死锁 不可重入
   3.hash + Lua脚本：可重入锁
   1判断锁是否被占用(exists），如果没有被占用则直接获取锁(hset/hincrby）并设置过期时间
   (expire)
   2.如果锁被占用，则判断是否当前线程占用的 (hexists)，如果是则重入(hinciby）并重置过期时间
   (expire)
   3.否则获取锁失败，在代码中重试
   4.Timer定时器+lua脚本：实现锁的自动续期
   判断锁是否自己的锁(hexists == 1)
   ，如果是自己的锁则执行expire重置过期时间

# 解锁

1.del：导致误删
2.先判断再删除同时保证原子性：需要使用lua脚本

3. hash +lua脚本：可重入
   1.判断当前线程的锁是否存在(防止删除其他线程的锁)，不存在则返回nll，抛出异常
   2.存在则直接减1 (hincrby-1) ，判断减1后的值是否为0，为0则释放锁 (del)，并返回1
   3.不为0,则返回0

# 锁重试

采用while循环的方式重新获取锁,获取失败后睡眠50毫秒重新获取





# 公平锁



+ redis中会保存一个lock_queue等待队列，保存后来的请求
+ 如果代码中设置超时时间为10秒，nginx设置的响应超时小于十秒钟，会重新发送请求。需要把nginx的响应超时时间设置的长一些



# 读写锁



+ redis会保存key为mode  value的值为write和read
+ 当mode为read的时候可以写入多个，即读与读不互斥，当mode为write时只能有一个，为独占锁


