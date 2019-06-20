package iducs.springboot.board.domain;

import java.time.LocalDateTime;

public class Answer {
	private long id; // primary key
	private Board question;
	private String title;
	private User writer;	
	private String contents;
	private LocalDateTime createTime;	
	
	public Answer() {}
	public Answer(User writer, Board question, String contents) {
		super();
		this.writer = writer;
		this.question = question;
		this.contents = contents;
		this.createTime = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	public Board getQuestion() {
		return question;
	}
	public void setQuestion(Board question) {
		this.question = question;
	}
	public boolean isSameUser(User user, User writer) {
		if(user.equals(writer))
			return true; 
		return false;
	}
}
