package service;

import java.util.List;

import bean.Quiz;
import bean.Word;

public interface PracticeService {
	public List<Word> queryAllWords();
	public Quiz getOneQuiz();
}
