package org.b3log.solo.common;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2019/11/20 18:09.
 *
 * @author zhaozengjie
 * Description : 存在有效时间的Map
 */
public class ExpireMap<K, V> extends ConcurrentHashMap<K, V> {

    private static final long serialVersionUID = 5962044388484617123L;

    private Map<K, Long> expiryMap = new ConcurrentHashMap<K, Long>();

    // 默认超时时间1s
    private long EXPIRY = 1000;

    public ExpireMap() {
        this(1000);
    }

    public ExpireMap(long defaultExpiryTime) {
        this(1 << 4, defaultExpiryTime);
    }

    public ExpireMap(int initialCapacity, long defaultExpiryTime) {
        super(initialCapacity);
        this.EXPIRY = defaultExpiryTime;
        BaseExpireCheckTask task = new BaseExpireCheckTask(expiryMap);
        task.setDaemon(false);
        task.start();
    }

    @Override
    public V put(K key, V value) {
        expiryMap.put(key, System.currentTimeMillis() + EXPIRY);
        return super.put(key, value);
    }

    @Override
    public boolean containsKey(Object key) {
        return !checkExpiry(key, true) && super.containsKey(key);
    }

    /**
     * 在put的时候可以指定超时时间
     *
     * @param key
     * @param value
     * @param expireTime 超时时间，毫秒值
     * @return
     */
    public V put(K key, V value, Long expireTime) {
        expiryMap.put(key, System.currentTimeMillis() + expireTime);
        return super.put(key, value);
    }

    @Override
    public int size() {
        return entrySet().size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            return Boolean.FALSE;
        }
        Set<Entry<K, V>> set = super.entrySet();
        Iterator<Entry<K, V>> iterator = set.iterator();

        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (value.equals(entry.getValue())) {
                if (checkExpiry(entry.getKey(), false)) {
                    iterator.remove();
                    return Boolean.FALSE;
                } else {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = super.values();

        if (values == null || values.size() < 1) {
            return values;
        }

        Iterator<V> iterator = values.iterator();

        while (iterator.hasNext()) {
            V next = iterator.next();
            if (!containsValue(next)) {
                iterator.remove();
            }
        }
        return values;
    }

    @Override
    public V get(Object key) {
        if (key == null) {
            return null;
        }

        if (checkExpiry(key, true)) {
            return null;
        }
        return super.get(key);
    }

    /**
     * @param key
     * @return
     * @Description: 是否过期
     */
    public Object isInvalid(Object key) {
        if (key == null) {
            return null;
        }

        if (!expiryMap.containsKey(key)) {
            return null;
        }
        long expiryTime = expiryMap.get(key);

        boolean flag = System.currentTimeMillis() > expiryTime;

        if (flag) {
            super.remove(key);
            expiryMap.remove(key);
            return -1;
        }
        return super.get(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            expiryMap.put(e.getKey(), System.currentTimeMillis() + EXPIRY);
        }
        super.putAll(m);
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<java.util.Map.Entry<K, V>> set = super.entrySet();
        Iterator<java.util.Map.Entry<K, V>> iterator = set.iterator();
        while (iterator.hasNext()) {
            java.util.Map.Entry<K, V> entry = iterator.next();
            if (checkExpiry(entry.getKey(), false)) {
                iterator.remove();
            }
        }

        return set;
    }

    /**
     * 检查是否已经过期
     *
     * @param key           主键
     * @param isRemoveSuper 是否检查的同时删除掉集合内的内容
     */
    private boolean checkExpiry(Object key, boolean isRemoveSuper) {
        if (!expiryMap.containsKey(key)) {
            return Boolean.FALSE;
        }
        Long expire = expiryMap.get(key);

        boolean flag = System.currentTimeMillis() > expire;

        if (flag) {
            if (isRemoveSuper) {
                super.remove(key);
            }
            super.remove(key);
        }
        return flag;
    }

    private class BaseExpireCheckTask extends Thread {

        private final Map<K, Long> delayMap;

        public BaseExpireCheckTask(Map<K, Long> map) {
            this.delayMap = map;
        }

        @Override
        public void run() {
            Iterator<K> it = null;
            K key = null;
            while (true) {
                if (delayMap != null && !delayMap.isEmpty()) {
                    it = delayMap.keySet().iterator();

                    while (it.hasNext()) {
                        key = it.next();
                        if (delayMap.get(key) <= System.currentTimeMillis()) {
                            // 移除
                            it.remove();
                            delayMap.remove(key);
                        }
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
