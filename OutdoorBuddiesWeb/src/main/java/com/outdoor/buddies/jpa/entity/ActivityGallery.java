package com.outdoor.buddies.jpa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ACTIVITY_GALLERY")
public class ActivityGallery {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long galleryId;
	
	@ManyToOne
	@JoinColumn(name="activityDetailsId")
	private ActivityDetails activityDetailsId;
	
	private String galleryItemDescription;
	
	private String imageUrl;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	public Long getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(Long galleryId) {
		this.galleryId = galleryId;
	}

	public ActivityDetails getActivityDetailsId() {
		return activityDetailsId;
	}

	public void setActivityDetailsId(ActivityDetails activityDetailsId) {
		this.activityDetailsId = activityDetailsId;
	}

	public String getGalleryItemDescription() {
		return galleryItemDescription;
	}

	public void setGalleryItemDescription(String galleryItemDescription) {
		this.galleryItemDescription = galleryItemDescription;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	
	
	
}
