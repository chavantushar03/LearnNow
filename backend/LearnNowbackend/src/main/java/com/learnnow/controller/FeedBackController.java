package com.learnnow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import com.learnnow.dto.FeedBackDTO;
import com.learnnow.service.FeedBackService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/feedbacks")
public class FeedBackController {
	@Autowired FeedBackService feedBackService;
	@GetMapping("/{id}")
	public ResponseEntity<?>getFeedBackById(@PathVariable Long id)
	{
		return ResponseEntity.ok(feedBackService.getFeedBackId(id));
	}
	 @GetMapping
	    public ResponseEntity<List<FeedBackDTO>> getAllFeedbacks() 
	 {
	        return ResponseEntity.ok(feedBackService.getAllFeedBack());
	 }
	 @PutMapping("/{id}")
	    public ResponseEntity<FeedBackDTO> updateFeedback(@PathVariable Long id, @RequestBody FeedBackDTO dto) {
	        return ResponseEntity.ok(feedBackService.updateFeedBack(id, dto));
	    }
	 @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
	        feedBackService.deleteFeedBack(id);
	        return ResponseEntity.ok("Feedback deleted successfully");
	    }
	 @PostMapping
	 public ResponseEntity<FeedBackDTO> addFeedBack(@Valid@RequestBody FeedBackDTO dto) {
	     return ResponseEntity.ok(feedBackService.addFeedBack(dto));
	 }

}
