package com.learn.redis.actual_combat;

import com.learn.redis.util.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.*;

/**
 * 文章投票
 *
 * Create by liguo on 2022/2/11
 *
 * "time:" 发布时间排序文章的有序集合
 * "voted:" 文章已投票人员的集合
 * "score:" 评分排序文章的有序集合
 * "group:" + tag 文章标签有序集合
 *
 **/
public class Chapter01 {
    private static final int ONE_WEEK_IN_SECONDS = 7 * 86400;
    private static final int VOTE_SCORE = 432;
    private static final int ARTICLES_PER_PAGE = 25;

    public static void main(String[] args) {
        new Chapter01().run();
    }

    public void run() {
        try(Jedis jedis = RedisUtil.getJedis()) {
            String articleId = postArticle(jedis, "username", "A title", "http://www.google.com");
            Map<String, String> articleData = jedis.hgetAll("article:" + articleId);
            for (Map.Entry<String, String> entry : articleData.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            System.out.println();
            articleVote(jedis, "other_user", "article:" + articleId);
            String votes = jedis.hget("article:" + articleId, "votes");
            List<Map<String, String>> articles = getArticles(jedis, 1, "score:");
            printArticles(articles);
            addGroup(jedis, articleId, new String[]{"new_group"});
            List<Map<String, String>> groupArticles = getGroupArticles(jedis, "new_group", 1, "score:");
            printArticles(groupArticles);
        }
    }

    /**
     * 发布功能
     */
    public String postArticle(Jedis jedis, String user, String title, String link) {
        String articleId = String.valueOf(jedis.incr("article:"));
        String voted = "voted:" + articleId;
        //将发布文章的用户添加到文章的已投票用户名单里面，
        jedis.sadd(voted, user);
        // 名单的过期时间设置为一周
        jedis.expire(voted, (long)ONE_WEEK_IN_SECONDS);
        long now = System.currentTimeMillis() / 1000;
        String article = "article:" + articleId;
        // 文章信息保存到hash
        HashMap<String, String> articleData = new HashMap<>();
        articleData.put("title", title);
        articleData.put("link", link);
        articleData.put("poster", user);
        articleData.put("time", String.valueOf(now));
        articleData.put("votes", "1");
        jedis.hmset(article, articleData);
        // 将文章添加到根据发布时间排序的有序集合
        jedis.zadd("time:", now, article);
        // 将文章添加到根据评分排序的有序集合里面(评分随时间不断减少) 当前时间 + 票数 * VOTE_SCORE
        jedis.zadd("score:", now + VOTE_SCORE, article);
        return articleId;
    }

    /**
     * 投票功能
     */
    public void articleVote(Jedis jedis, String user, String article) {
        // 文章投票截止时间
        long cutOff = (System.currentTimeMillis() / 1000) - ONE_WEEK_IN_SECONDS;
        // 是否还能投票
        if (jedis.zscore("time:", article) < cutOff) {
            return;
        }
        String articleId = article.split(":")[1];
        // sadd 与 sismember
        // 第一次投票
        if (jedis.sadd("voted:" + articleId, user) == 1) {
            // 增加评分
            jedis.zincrby("score:", VOTE_SCORE, article);
            // 增加投票数量
            jedis.hincrBy(article, "votes", 1);
        }
    }

    /**
     * 获取文章(评分最高,最新发布)
     */
    public List<Map<String, String>> getArticles(Jedis jedis, int page, String order) {
        int start = (page - 1) * ARTICLES_PER_PAGE;
        int end = start + ARTICLES_PER_PAGE - 1;
        // 反序
        Set<String> ids = jedis.zrevrange(order, start, end);
        List<Map<String, String>> articles = new ArrayList<>();
        for (String id : ids) {
            Map<String, String> articleData = jedis.hgetAll(id);
            articleData.put("id", id);
            articles.add(articleData);
        }
        return articles;
    }

    /**
     * 添加标签
     */
    public void addGroup(Jedis jedis, String articleId, String[] tags) {
        String article = "article:" + articleId;
        for (String tag : tags) {
            jedis.sadd("group:" + tag, article);
        }
    }

    /**
     * 获取分组文章
     */
    public List<Map<String, String>> getGroupArticles(Jedis jedis, String group, int page, String order) {
        // 群组的每种排列顺序都创建一个键
        String key = order + group;
        if (!jedis.exists(key)) {
            ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
            // 集合的交集
            jedis.zinterstore(key, params, "group:" + group, order);
            // 减少工作量(群组文章多的情况下，zinterstore耗时)
            jedis.expire(key, 60L);
        }
        return getArticles(jedis, page, key);
    }

    private void printArticles(List<Map<String,String>> articles) {
        for (Map<String, String> article : articles) {
            System.out.println("  id: " + article.get("id"));
            for (Map.Entry<String, String> entry : article.entrySet()) {
                if (entry.getKey().equals("id")) {
                    continue;
                }
                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
