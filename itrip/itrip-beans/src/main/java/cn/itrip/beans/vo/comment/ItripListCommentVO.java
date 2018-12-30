package cn.itrip.beans.vo.comment;

import java.util.Date;

/**
 * 返回前端-点评列表VO
 *
 */
public class ItripListCommentVO{
	private Long id;//评论id
	private String userCode;  //发表评论的用户的姓名
	private Integer hotelLevel;  //酒店的星级
	private Date checkInDate;  //入住时间
	private String roomTitle;  //房型名称
	private String tripModeName;//出游类型中文名称
	private String content;//评论内容
	private Date creationDate;//评论发表时间
	private Integer score;//综合评分
	private Integer isHavingImg;//是否有评论图片

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTripModeName() {
		return tripModeName;
	}

	public void setTripModeName(String tripModeName) {
		this.tripModeName = tripModeName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getIsHavingImg() {
		return isHavingImg;
	}

	public void setIsHavingImg(Integer isHavingImg) {
		this.isHavingImg = isHavingImg;
	}

	public Integer getHotelLevel() {
		return hotelLevel;
	}

	public void setHotelLevel(Integer hotelLevel) {
		this.hotelLevel = hotelLevel;
	}

	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getRoomTitle() {
		return roomTitle;
	}
	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public ItripListCommentVO(Long id, String userCode, Integer hotelLevel, Date checkInDate, String roomTitle, String tripModeName, String content, Date creationDate, Integer score, Integer isHavingImg) {
		this.id = id;
		this.userCode = null;
		this.hotelLevel = null;
		this.checkInDate = checkInDate;
		this.roomTitle = null;
		if (this.tripModeName.equals("108")){
			this.tripModeName="商务出差";
		}else if (this.tripModeName.equals("111")){

			this.tripModeName="家庭亲子";
		}else if (this.tripModeName.equals("110")){
			this.tripModeName="情侣出游";
		}else if (this.tripModeName.equals("109")){
			this.tripModeName="朋友出游";
		}
		this.tripModeName = tripModeName;
		this.content = content;
		this.creationDate = creationDate;
		this.score = score;
		this.isHavingImg = isHavingImg;
	}
}
