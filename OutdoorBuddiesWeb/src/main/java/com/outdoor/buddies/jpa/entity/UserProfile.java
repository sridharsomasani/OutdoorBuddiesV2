package com.outdoor.buddies.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="USER_PROFILE")
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@NotEmpty(message="firstname cannot be blank")
	private String firstName;
	
	@NotEmpty(message="lastname cannot be blank")
	private String lastName;
	
	@NotEmpty(message="username field cannot be blank")
	@Column(unique=true, nullable=false)
	private String userName;
	
	@NotEmpty(message="display cannot be blank")
	private String displayName;
	
	private String password;
	
	@NotEmpty(message="email id field cannot be blank")
	@Column(unique=true, nullable=false)
	private String emailId;
	
	@NotNull(message="DOB field cannot be blank")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@NotEmpty(message="gender field cannot be blank")
	private String gender;
	
	private String mobileNumber;
	
	private String address;
	
	private Integer expertise;
	
	@JsonIgnoreProperties
	private String imageUrl;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnoreProperties
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnoreProperties
	private Date lastModified;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnoreProperties
	private Date lastLogin;

    @PreUpdate
    public void preUpdate() {
    	lastModified = new Date();
    	setDefaultImage();
    }
     
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        createDate = now;
        lastModified = now;
        setDefaultImage();
    }
	
	private void setDefaultImage() {
        if(imageUrl == null || "".equals(imageUrl)) {
        	String mGender = getGender().equals("Male") ? "/resources/images/male.jpg" : "/resources/images/female.jpg";
        	setImageUrl(mGender);
        }
		
	}
    
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Date getLastModified() {
		return lastModified;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public Integer getExpertise() {
		return expertise;
	}

	public void setExpertise(Integer expertise) {
		this.expertise = expertise;
	}
	
	
}
