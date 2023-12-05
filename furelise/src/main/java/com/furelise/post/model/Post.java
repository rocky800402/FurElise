package com.furelise.post.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "post")
@Data
public class Post implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postID;
	private String postName;
	private Date postStart;
	private Date postEnd;
	private String postDetail;
	@Column(name = "postPic", columnDefinition = "LONGBLOB")
	private byte[] postPic;
	@Column(name = "postStatus", columnDefinition = "BIT(1) DEFAULT 1")
	private Boolean postStatus = true;
	
	
	public Post() {
		super();
	}

	// Constructor without PK
	public Post(String postName, Date postStart, Date postEnd, String postDetail, byte[] postPic, Boolean postStatus) {
		super();
		this.postName = postName;
		this.postStart = postStart;
		this.postEnd = postEnd;
		this.postDetail = postDetail;
		this.postPic = postPic;
		this.postStatus = postStatus;
	}

	// Constructor without PK & postStatus
	public Post(String postName, Date postStart, Date postEnd, String postDetail, byte[] postPic) {
		super();
		this.postName = postName;
		this.postStart = postStart;
		this.postEnd = postEnd;
		this.postDetail = postDetail;
		this.postPic = postPic;
	}

	public Post(Integer postID, String postName, Date postStart, Date postEnd, String postDetail, byte[] postPic,
			Boolean postStatus) {
		super();
		this.postID = postID;
		this.postName = postName;
		this.postStart = postStart;
		this.postEnd = postEnd;
		this.postDetail = postDetail;
		this.postPic = postPic;
		this.postStatus = postStatus;
	}

}