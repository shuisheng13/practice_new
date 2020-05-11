package com.yhh.practice.redis.dislock.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {
    //无成员变量 --- 无状态
    public static String getScript(String fileName){
        String path = FileUtils.class.getClassLoader().getResource(fileName).getPath();
        return readFileByLines(path);
    }

	public static String readFileByLines(String fileName) {
        FileInputStream file = null;
        BufferedReader reader = null;
        InputStreamReader inputFileReader = null;
        String content = "";
        String tempString = null;
        try {
            file = new FileInputStream(fileName);
            inputFileReader = new InputStreamReader(file, "utf-8");
            reader = new BufferedReader(inputFileReader);
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                content += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }
	
	public static void main(String[] args) {
        String path = FileUtils.class.getClassLoader().getResource("limit.lua").getPath();
		String script = FileUtils.readFileByLines(path);
		System.out.println(script);
	}

	public static String test(){

        String st = "local key =  KEYS[1]\n" +
                "local limit = tonumber(ARGV[1])\n" +
                "local expire_time = ARGV[2]\n" +
                "\n" +
                "local is_exists = redis.call(\"EXISTS\", key)\n" +
                "\n" +
                "if is_exists == 1 then\n" +
                "    if redis.call(\"INCR\", key) > limit then\n" +
                "        return 1\n" +
                "\n" +
                "    else\n" +
                "        return 0\n" +
                "\n" +
                "    end\n" +
                "\n" +
                "else\n" +
                "\n" +
                "    redis.call(\"SET\", key, 1)\n" +
                "    redis.call(\"EXPIRE\", key, expire_time)\n" +
                "    return 0\n" +
                "\n" +
                "end";
        return st;


    }
}
