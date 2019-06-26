package cn.lk.newsssh.bean;

import javax.persistence.Entity;
import java.util.Date;


@Entity
public class News implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String type;
	private String content;
	private Date tjdate;
	private String cruser;
	private Integer hitnum;

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	// Constructors


	/** full constructor */
	public News(Integer id, String title, String type, String content, Date tjdate, String cruser, Integer hitnum) {
		this.title = title;
		this.content = content;
		this.tjdate = tjdate;
		this.cruser = cruser;
		this.hitnum = hitnum;
		this.id = id;
		this.type = type;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTjdate() {
		return this.tjdate;
	}

	public void setTjdate(Date tjdate) {
		this.tjdate = tjdate;
	}

	public String getCruser() {
		return this.cruser;
	}

	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	public Integer getHitnum() {
		return this.hitnum;
	}

	public void setHitnum(Integer hitnum) {
		this.hitnum = hitnum;
	}

}