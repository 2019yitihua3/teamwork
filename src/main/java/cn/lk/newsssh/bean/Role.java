package cn.lk.newsssh.bean;


public class Role {
    private int id;
    private int rid;
    private String rstr;
    private int power;
    private String pstr;
    public Role() {
    }

    public Role(int id, int rid, int power) {
        this.id = id;
        this.rid = rid;
        this.power = power;
    }

    public Role(int id, int rid, String rstr, int power, String pstr) {
        this.id = id;
        this.rid = rid;
        this.rstr = rstr;
        this.power = power;
        this.pstr = pstr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRstr() {
        return rstr;
    }

    public void setRstr(String rstr) {
        this.rstr = rstr;
    }

    public String getPstr() {
        return pstr;
    }

    public void setPstr(String pstr) {
        this.pstr = pstr;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
