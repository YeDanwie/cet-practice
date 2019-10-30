package service;

import java.util.List;

import bean.Discuss;
import bean.Topic;

public interface DiscussService {
	public List<Topic> queryAllTopic();
	public void createTopic(Topic topic);
	public Topic queryTopicById(int id);
	public List<Discuss> queryDiscussByTopicId(int topic_id);
	public void insertNewDiscuss(Discuss discuss);
}
