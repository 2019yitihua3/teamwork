package cn.lk.newsssh.bean;


public class Ntype {
    private Integer id;
    private String typename;

    public Ntype(Integer id, String typename) {
        this.id = id;
        this.typename = typename;
    }

    public Ntype() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
