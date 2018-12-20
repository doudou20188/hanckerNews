package com.com.cskaoyan.newsproject.utill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class JedisUtils {


    static  JedisPool pool;
    static {

        pool = new JedisPool();

    }

    public  static  Jedis getJedisFromPool(){

        Jedis resource = pool.getResource();

        return  resource;

    }

    public static String get(String key){

        String v=null;
        Jedis jedis=null;
        try {
            jedis = pool.getResource();

            v = jedis.get(key);
        }catch (Exception e){

            System.out.println("e"+e.getCause());

        }
        finally {

            if (jedis!=null){
                jedis.close();
            }

        }



        return  v;
    }

    public static String set(String key,String value){

        Jedis jedis=null;
        String set=null;
        try {
            jedis = pool.getResource();

              set = jedis.set(key, value);
        }catch (Exception e){

            System.out.println("e"+e.getCause());

        }
        finally {

            if (jedis!=null){
                jedis.close();
            }

        }


        return  set;
    }

}
