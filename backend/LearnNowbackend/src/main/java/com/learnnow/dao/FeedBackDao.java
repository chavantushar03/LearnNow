package com.learnnow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnnow.pojo.FeedBack;
@Repository
public interface FeedBackDao extends JpaRepository<FeedBack, Long>{

}
