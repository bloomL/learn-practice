package com.learn.redis.deep_adventure;

import cn.hutool.core.util.StrUtil;
import com.learn.redis.util.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Create by liguo on 2021/12/21
 **/
public class ScanTest {
    public static void main(String[] args) {
        try(Jedis jedis = RedisUtil.getJedis()) {
            long start = System.currentTimeMillis();
            List<String> result = new ArrayList<>();
            ScanParams params = buildParam("key99*", 1000);
            String cursor = "0";
            String endFlag = "0";
            while(true) {
                ScanResult<String> scanResult = jedis.scan(cursor, params);
                cursor = scanResult.getCursor();
                if (StrUtil.equals(endFlag, cursor)) {
                    break;
                }
                result.addAll(scanResult.getResult());
            }
            System.out.println("spend：" + (System.currentTimeMillis() - start) + " size：" + result.size() + " ：" + result);
        }
    }

    private static ScanParams buildParam(String match, int limit) {
        ScanParams params = new ScanParams();
        params.match(match);
        params.count(limit);
        return params;
    }
}
