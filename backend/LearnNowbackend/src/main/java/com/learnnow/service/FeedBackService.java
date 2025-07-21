package com.learnnow.service;

import java.util.List;

import com.learnnow.dto.FeedBackDTO;

public interface FeedBackService {
	FeedBackDTO addFeedBack(FeedBackDTO dto);
	List<FeedBackDTO>getAllFeedBack();
	FeedBackDTO updateFeedBack(Long id,FeedBackDTO feedBackDTO);
	FeedBackDTO getFeedBackId(Long id);
	void deleteFeedBack(Long id);
		

}
