package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Discuss;
import bean.Topic;
import dao.DiscussMapper;

@Service
public class DiscussServiceImpl implements DiscussService{
	@Autowired
	private DiscussMapper discussMapper;
	
	@Override
	public List<Topic> queryAllTopic() {
		return discussMapper.queryAllTopics();
	}

	@Override
	public void createTopic(Topic topic) {
		discussMapper.createTopic(topic);
	}

	@Override
	public Topic queryTopicById(int id) {
		return discussMapper.queryTopicById(id);
	}

	@Override
	public List<Discuss> queryDiscussByTopicId(int topic_id) {
		return discussMapper.queryDiscussByTopicId(topic_id);
	}

	@Override
	public void insertNewDiscuss(Discuss discuss) {
		discussMapper.insertNewDiscuss(discuss);
		
	}

}
