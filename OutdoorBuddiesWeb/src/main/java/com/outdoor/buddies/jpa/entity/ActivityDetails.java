package com.outdoor.buddies.jpa.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="ACTIVITY_DETAILS")
public class ActivityDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long activityDetailsId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="activityId")
	private Activity activity;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId", nullable=false)
	private UserProfile activityOwner;
	
	private Boolean isCompleted;
	
	@Temporal(TemporalType.TIME)
	private Date activityStartTime;
	
	@Temporal(TemporalType.TIME)
	private Date activityEndTime;
	
	private String activityPlace;
	
	private String activityAddress;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="activityDetailsId", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<ActivityParticipant> participants;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="activityDetailsId", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<ActivityGallery> gallery;

	
    @PreUpdate
    public void preUpdate() {
    	lastModified = new Date();
    }
     
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        createDate = now;
        lastModified = now;
    }
    
	public Long getActivityDetailsId() {
		return activityDetailsId;
	}

	public void setActivityDetailsId(Long activityDetailsId) {
		this.activityDetailsId = activityDetailsId;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Date getActivityStartTime() {
		return activityStartTime;
	}

	public void setActivityStartTime(Date activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public String getActivityPlace() {
		return activityPlace;
	}

	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}

	public String getActivityAddress() {
		return activityAddress;
	}

	public void setActivityAddress(String activityAddress) {
		this.activityAddress = activityAddress;
	}

	public Date getCreateDate() {
		return createDate;
	}


	public Date getLastModified() {
		return lastModified;
	}

	public UserProfile getActivityOwner() {
		return activityOwner;
	}

	public void setActivityOwner(UserProfile activityOwner) {
		this.activityOwner = activityOwner;
	}

	public List<ActivityParticipant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ActivityParticipant> participants) {
		this.participants = participants;
	}
	
	public void addParticipants(ActivityParticipant participant) {
		participant.setActivityDetailsId(this);
		participants.add(participant);
	}

	public List<ActivityGallery> getGallery() {
		return gallery;
	}

	public void setGallery(List<ActivityGallery> gallery) {
		this.gallery = gallery;
	}
	
	public void addGallery(ActivityGallery gallery) {
		gallery.setActivityDetailsId(this);
		this.gallery.add(gallery);
	}
	
	
}
