package com.furelise.post.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {

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

	public Integer getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public Date getPostStart() {
		return postStart;
	}

	public void setPostStart(Date postStart) {
		this.postStart = postStart;
	}

	public Date getPostEnd() {
		return postEnd;
	}

	public void setPostEnd(Date postEnd) {
		this.postEnd = postEnd;
	}

	public String getPostDetail() {
		return postDetail;
	}

	public void setPostDetail(String postDetail) {
		this.postDetail = postDetail;
	}

	public byte[] getPostPic() {
		return postPic;
	}

	public void setPostPic(byte[] postPic) {
		this.postPic = postPic;
	}

	public Boolean getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(Boolean postStatus) {
		this.postStatus = postStatus;
	}

	@Override
	public String toString() {
		return "Post [postID=" + postID + ", postName=" + postName + ", postStart=" + postStart + ", postEnd=" + postEnd
				+ ", postDetail=" + postDetail + ", postPic=" + Arrays.toString(postPic) + ", postStatus=" + postStatus
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(postPic);
		result = prime * result + Objects.hash(postDetail, postEnd, postID, postName, postStart, postStatus);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(postDetail, other.postDetail) && Objects.equals(postEnd, other.postEnd)
				&& Objects.equals(postID, other.postID) && Objects.equals(postName, other.postName)
				&& Arrays.equals(postPic, other.postPic) && Objects.equals(postStart, other.postStart)
				&& Objects.equals(postStatus, other.postStatus);
	}
	
}