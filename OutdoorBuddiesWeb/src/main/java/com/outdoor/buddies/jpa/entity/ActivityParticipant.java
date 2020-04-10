package com.outdoor.buddies.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="ACTIVITY_PARTICIPANT")
public class ActivityParticipant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long participantId;
	
	@ManyToOne
	@JsonBackReference
	private ActivityDetails activityDetailsId;
	
	@ManyToOne
	private UserProfile userProfile;
	
	@Column(columnDefinition="TINYINT(1) DEFAULT 0")
	private Boolean accepted = false;
	
	@Column(columnDefinition="TINYINT(1) DEFAULT 0")
	private Boolean attended = false;

	private String feedback;
	
	private Date createDate;
	
	private Date lastModified;

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
    
	public Long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}

	public ActivityDetails getActivityDetailsId() {
		return activityDetailsId;
	}

	public void setActivityDetailsId(ActivityDetails activityDetails) {
		this.activityDetailsId = activityDetails;
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

	public Date getCreateDate() {
		return createDate;
	}

	public Date getLastModified() {
		return lastModified;
	}
	
}
