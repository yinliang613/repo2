package cn.suse.baozi;

/**
 * 包子类
 * 属性：皮，馅，状态
 */

public class BaoZi {
    private String pi;
    private  String xian;
    boolean flag = false;

    public String getPi() {
        return pi;
    }

    public void setPi(String pi) {
        this.pi = pi;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }
}
