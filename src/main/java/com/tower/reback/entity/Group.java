package com.tower.reback.entity;

import java.util.HashMap;
import java.util.HashSet;


public class Group {
    public static HashMap<String, HashSet<String>> getBranchmap(){
        HashMap<String, HashSet<String>> map = new HashMap<>();
        final HashSet<String> fengjie = new HashSet<String>() {
            {   add("奉节"); add("云阳"); add("巫山");add("巫溪");}
        };
        final HashSet<String> fuling = new HashSet<String>() {
            { add("涪陵");add("长寿");add("垫江");add("南川");add("武隆");add("丰都"); }
        };
        final HashSet<String> hechuan = new HashSet<String>() {
            { add("合川");add("北碚");add("铜梁");add("潼南"); }
        };
        final HashSet<String> liangjiang = new HashSet<String>() {
            { add("两江");add("江北");add("渝北"); }
        };
        final HashSet<String> nanan = new HashSet<String>() {
            { add("南岸");add("巴南");add("綦江");add("江津"); }
        };
        final HashSet<String> qianjiang = new HashSet<String>() {
            { add("黔江");add("秀山");add("酉阳");add("彭水"); }
        };
        final HashSet<String> wanzhou = new HashSet<String>() {
            { add("万州");add("开州");add("梁平");add("城口");add("忠县");add("石柱"); }
        };
        final HashSet<String> yongchuan = new HashSet<String>() {
            { add("永川");add("大足");add("璧山");add("荣昌"); }
        };
        final HashSet<String> yuzhong = new HashSet<String>() {
            { add("渝中");add("沙坪坝");add("九龙坡");add("大渡口"); }
        };
        final HashSet<String> chongqing = new HashSet<String>() {
            {
                add("奉节"); add("云阳"); add("巫山");add("巫溪");
                add("涪陵");add("长寿");add("垫江");add("南川");add("武隆");add("丰都");
                add("合川");add("北碚");add("铜梁");add("潼南");
                add("两江");add("江北");add("渝北");
                add("南岸");add("巴南");add("綦江");add("江津");
                add("黔江");add("秀山");add("酉阳");add("彭水");
                add("万州");add("开州");add("梁平");add("城口");add("忠县");add("石柱");
                add("永川");add("大足");add("璧山");add("荣昌");
                add("渝中");add("沙坪坝");add("九龙坡");add("大渡口");
            }
        };
        map.put("奉节",fengjie);
        map.put("涪陵",fuling);
        map.put("合川",hechuan);
        map.put("两江",liangjiang);
        map.put("南岸",nanan);
        map.put("黔江",qianjiang);
        map.put("万州",wanzhou);
        map.put("永川",yongchuan);
        map.put("渝中",yuzhong);
        map.put("重庆",chongqing);
        return map;
    }

    public static  HashMap<String, String> getAreaMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("奉节","奉节");
        map.put("云阳","奉节");
        map.put("巫山","奉节");
        map.put("巫溪","奉节");
        map.put("涪陵","涪陵");
        map.put("长寿","涪陵");
        map.put("垫江","涪陵");
        map.put("南川","涪陵");
        map.put("武隆","涪陵");
        map.put("丰都","涪陵");
        map.put("合川","合川");
        map.put("北碚","合川");
        map.put("铜梁","合川");
        map.put("潼南","合川");
        map.put("两江","两江");
        map.put("江北","两江");
        map.put("渝北","两江");
        map.put("南岸","南岸");
        map.put("巴南","南岸");
        map.put("綦江","南岸");
        map.put("江津","南岸");
        map.put("黔江","黔江");
        map.put("秀山","黔江");
        map.put("酉阳","黔江");
        map.put("彭水","黔江");
        map.put("万州","万州");
        map.put("开州","万州");
        map.put("梁平","万州");
        map.put("城口","万州");
        map.put("忠县","万州");
        map.put("石柱","万州");
        map.put("永川","永川");
        map.put("大足","永川");
        map.put("璧山","永川");
        map.put("荣昌","永川");
        map.put("渝中","渝中");
        map.put("沙坪坝","渝中");
        map.put("九龙坡","渝中");
        map.put("大渡口","渝中");
        return map;
    };
    public static final HashSet<String> AreaSet = new HashSet<String>() {
        {
            add("巴南");
            add("北碚");
            add("两江");
            add("璧山");
            add("城口");
            add("大渡口");
            add("大足");
            add("垫江");
            add("丰都");
            add("奉节");
            add("涪陵");
            add("合川");
            add("江北");
            add("江津");
            add("九龙坡");
            add("开州");
            add("梁平");
            add("南岸");
            add("南川");
            add("彭水");
            add("綦江");
            add("黔江");
            add("荣昌");
            add("沙坪坝");
            add("石柱");
            add("铜梁");
            add("潼南");
            add("万州");
            add("巫山");
            add("巫溪");
            add("武隆");
            add("秀山");
            add("永川");
            add("酉阳");
            add("渝北");
            add("渝中");
            add("云阳");
            add("长寿");
            add("忠县");

        }
    };
    public static final HashSet<String> CustomerSet = new HashSet<String>() {
        {
            add("移动");
            add("联通");
            add("电信");
        }
    };
    public static final HashSet<String> ShareCustomerSet = new HashSet<String>() {
        {
            add("移动");
            add("联通");
            add("电信");
            add("移动+联通");
            add("移动+电信");
            add("联通+电信");
            add("联通+移动");
            add("电信+移动");
            add("电信+联通");
            add("移动+联通+电信");
            add("移动+电信+联通");
            add("联通+移动+电信");
            add("联通+电信+移动");
            add("电信+移动+联通");
            add("电信+联通+移动");

        }
    };


   
}
