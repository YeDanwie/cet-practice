package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import bean.Discuss;
import bean.Topic;

@Mapper
public interface DiscussMapper {
	public List<Topic> queryAllTopics();
	public void createTopic(Topic topic);
	public Topic queryTopicById(int id);
	public List<Discuss> queryDiscussByTopicId(int topic_id);
	public void insertNewDiscuss(Discuss discuss);
}
