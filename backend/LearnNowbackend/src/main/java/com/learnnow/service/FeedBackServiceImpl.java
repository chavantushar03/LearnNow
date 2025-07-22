package com.learnnow.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnnow.dao.FeedBackDao;
import com.learnnow.dto.FeedBackDTO;
import com.learnnow.pojo.FeedBack;
@Service
public class FeedBackServiceImpl implements FeedBackService{

	@Autowired
	private FeedBackDao feedBackDao;
	
	@Override
	public FeedBackDTO addFeedBack(FeedBackDTO dto) {
		FeedBack feedBack = new FeedBack();
		feedBack.setUserId(dto.getUserId());
		feedBack.setCourseId(dto.getCourseId());
		feedBack.setActive(true);
		feedBack.setCommit(dto.getComment());
		feedBack.setRating(dto.getRating());
		FeedBack saved = feedBackDao.save(feedBack);
		return new FeedBackDTO(saved);
	}

	@Override
	public List<FeedBackDTO> getAllFeedBack() {
		// TODO Auto-generated method stub
		return feedBackDao.findAll().stream()
	            .map(FeedBackDTO::new)
	            .collect(Collectors.toList());
	}

	@Override
	public FeedBackDTO updateFeedBack(Long id, FeedBackDTO feedBackDTO) {
		// TODO Auto-generated method stub
		FeedBack feedBack = feedBackDao.findById(id).orElseThrow(()->new RuntimeException("FeedBack not Found with Id:"+id));
		feedBack.setCommit(feedBackDTO.getComment());
		feedBack.setRating(feedBackDTO.getRating());
		FeedBack  updated = feedBackDao.save(feedBack);
		return new FeedBackDTO(updated);
	}

	@Override
	public FeedBackDTO getFeedBackId(Long id) {
		// TODO Auto-generated method stub
		FeedBack feedback = feedBackDao.findById(id)
	            .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));
	        return new FeedBackDTO(feedback);
	}

	@Override
	public void deleteFeedBack(Long id) {
		// TODO Auto-generated method stub
//		if (!feedBackDao.existsById(id)) {
//            throw new RuntimeException("Feedback not found with id: " + id);
//        }
//		feedBackDao.deleteById(id);
		FeedBack feedback = feedBackDao.findById(id)
	            .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));
		feedback.setActive(false);
		feedBackDao.save(feedback);
	}

}
