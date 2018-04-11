package com.outdoor.buddies.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ACTIVITY_PARTICIPANT")
public class ActivityParticipant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long participantId;
	
	@ManyToOne
	@JoinColumn(name="activityDetailsId")
	private ActivityDetails activityDetails;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private UserProfile userProfile;
	
	@Column(columnDefinition="TINYINT(1) DEFAULT 0")
	private Boolean accepted;
	
	@Column(columnDefinition="TINYINT(1) DEFAULT 0")
	private Boolean attended;

	private String feedback;
	
	private Integer expertise;
	
	private Date createDate;
	
	private Date lastModified;

	public Long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}

	public ActivityDetails getActivityDetails() {
		return activityDetails;
	}

	public void setActivityDetails(ActivityDetails activityDetails) {
		this.activityDetails = activityDetails;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public Boolean getAttended() {
		return attended;
	}

	public void setAttended(Boolean attended) {
		this.attended = attended;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Integer getExpertise() {
		return expertise;
	}

	public void setExpertise(Integer expertise) {
		this.expertise = expertise;
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
