package com.shf.app26_baseadapter;

/**
 * 每行item数据封装类
 */
public class ShopInfo {
    private int icon;
    private String name;
    private String content;

    public ShopInfo() {
    }

    public ShopInfo(int icon, String name, String content) {
        this.icon = icon;
        this.name = name;
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ShopInfo{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
