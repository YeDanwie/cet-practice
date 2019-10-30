package bean;

import java.sql.Date;

public class Discuss {
	private int id;
	private int topic_id;
	private String username;
	private String message;
	private Date discuss_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDiscuss_date() {
		return discuss_date;
	}
	public void setDiscuss_date(Date discuss_date) {
		this.discuss_date = discuss_date;
	}
	
}
