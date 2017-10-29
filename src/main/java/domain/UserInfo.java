package domain;

public class UserInfo {
    private String name;
    private String zhiye;
    private String dizhi;
    private int age;
    private int NumGuanzhu;
    private int NumWorks;

    public UserInfo(String name, String zhiye, String dizhi, int age, int numGuanzhu,int NumWorks) {
        this.name = name;
        this.zhiye = zhiye;
        this.dizhi = dizhi;
        this.age = age;
        NumGuanzhu = numGuanzhu;
        this.NumWorks=NumWorks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZhiye() {
        return zhiye;
    }

    public void setZhiye(String zhiye) {
        this.zhiye = zhiye;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumGuanzhu() {
        return NumGuanzhu;
    }

    public void setNumGuanzhu(int numGuanzhu) {
        NumGuanzhu = numGuanzhu;
    }

    public int getNumWorks() {
        return NumWorks;
    }

    public void setNumWorks(int numWorks) {
        NumWorks = numWorks;
    }
}
