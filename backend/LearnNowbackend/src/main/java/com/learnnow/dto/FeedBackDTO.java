package com.learnnow.dto;

import com.learnnow.pojo.FeedBack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeedBackDTO {
	private Long id;
	private Long userId;
	private Long courseId;
	private String comment;
	private Integer rating;
	public FeedBackDTO(FeedBack feedback) {
		this.id = feedback.getId();
        this.userId = feedback.getUserId();
        this.courseId = feedback.getCourseId();
        this.comment = feedback.getCommit();
        this.rating = feedback.getRating();
    }

}
