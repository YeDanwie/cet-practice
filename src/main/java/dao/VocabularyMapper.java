package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import bean.Word;

@Mapper
public interface VocabularyMapper {
	public List<Word> queryAllWords();
	public List<Word> selectFourWords(List<Integer> idList);
}
