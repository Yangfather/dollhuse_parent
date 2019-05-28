package com.dollhouse.entity.comm;

import java.math.BigDecimal;
import java.util.Date;

import com.dollhouse.core.annotation.Column;
import com.dollhouse.core.annotation.Table;
import com.dollhouse.core.entity.BaseEntity;


@Table(name="mb_member_info")
public class MbMemberInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    
    @Column(name="member_id")
    private Long memberId;
    
    @Column(name="member_name")
    private String memberName;
    
    @Column(name="avatar")
    private String avatar;
    
    @Column(name="nickname")
    private String nickname;
    
    @Column(name="realname")
    private String realname;
    
    @Column(name="gender")
    private String gender;
    
    @Column(name="birthday")
    private Date birthday;
    
    @Column(name="card_id")
    private String cardId;
    
    @Column(name="qq")
    private Long qq;
    
    @Column(name="educational_background")
    private Integer educationalBackground;
    
    @Column(name="marry_status")
    private Integer marryStatus;
    
    @Column(name="graduated")
    private String graduated;
    
    @Column(name="company_industry")
    private Integer companyIndustry;
    
    @Column(name="company_scale")
    private Integer companyScale;
    
    @Column(name="company_office")
    private Integer companyOffice;
    
    @Column(name="monthly_income")
    private Integer monthlyIncome;
    
    @Column(name="invite_money")
    private BigDecimal inviteMoney;
    
    @Column(name="hometown_province")
    private Integer hometownProvince;
    
    @Column(name="hometown_city")
    private Integer hometownCity;
    
    @Column(name="hometown_area")
    private Integer hometownArea;
    
    @Column(name="hometown_post")
    private String hometownPost;

    public Long getMemberId() {
        return memberId;
    }
 
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }
 
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAvatar() {
        return avatar;
    }
 
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }
 
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }
 
    public void setRealname(String realname) {
        this.realname = realname;
    }


    public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
        return birthday;
    }
 
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCardId() {
        return cardId;
    }
 
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Long getQq() {
        return qq;
    }
 
    public void setQq(Long qq) {
        this.qq = qq;
    }

    public Integer getEducationalBackground() {
        return educationalBackground;
    }
 
    public void setEducationalBackground(Integer educationalBackground) {
        this.educationalBackground = educationalBackground;
    }

    public Integer getMarryStatus() {
        return marryStatus;
    }
 
    public void setMarryStatus(Integer marryStatus) {
        this.marryStatus = marryStatus;
    }

    public String getGraduated() {
        return graduated;
    }
 
    public void setGraduated(String graduated) {
        this.graduated = graduated;
    }

    public Integer getCompanyIndustry() {
        return companyIndustry;
    }
 
    public void setCompanyIndustry(Integer companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public Integer getCompanyScale() {
        return companyScale;
    }
 
    public void setCompanyScale(Integer companyScale) {
        this.companyScale = companyScale;
    }

    public Integer getCompanyOffice() {
        return companyOffice;
    }
 
    public void setCompanyOffice(Integer companyOffice) {
        this.companyOffice = companyOffice;
    }

    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }
 
    public void setMonthlyIncome(Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public BigDecimal getInviteMoney() {
        return inviteMoney;
    }
 
    public void setInviteMoney(BigDecimal inviteMoney) {
        this.inviteMoney = inviteMoney;
    }

    public Integer getHometownProvince() {
        return hometownProvince;
    }
 
    public void setHometownProvince(Integer hometownProvince) {
        this.hometownProvince = hometownProvince;
    }

    public Integer getHometownCity() {
        return hometownCity;
    }
 
    public void setHometownCity(Integer hometownCity) {
        this.hometownCity = hometownCity;
    }

    public Integer getHometownArea() {
        return hometownArea;
    }
 
    public void setHometownArea(Integer hometownArea) {
        this.hometownArea = hometownArea;
    }

    public String getHometownPost() {
        return hometownPost;
    }
 
    public void setHometownPost(String hometownPost) {
        this.hometownPost = hometownPost;
    }
}


