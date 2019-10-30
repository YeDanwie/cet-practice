package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Quiz;
import bean.Word;
import dao.VocabularyMapper;

@Service
public class PracticeSeriveImpl implements PracticeService{
	@Autowired
	private VocabularyMapper vocabularyMapper;
	
	@Override
	public Quiz getOneQuiz() {
		HashSet<Integer> set = new HashSet<>();
		Random random = new Random();
		while(set.size()!=4) {
			set.add(1+random.nextInt(7989));
		}
		List<Integer> idList = new ArrayList<Integer>(set);
		List<Word> words = vocabularyMapper.selectFourWords(idList);
		Word word = words.get(random.nextInt(4));
		
		Quiz quiz = new Quiz();
		if(random.nextBoolean()) {
			quiz.setTitle(word.getChinese());
			List<String> options = new ArrayList<>();
			for(Word w: words)
				options.add(w.getEnglish());
			quiz.setOptions(options);
			quiz.setAnswer(word.getEnglish());
		} else {
			quiz.setTitle(word.getEnglish());
			List<String> options = new ArrayList<>();
			for(Word w: words)
				options.add(w.getChinese());
			quiz.setOptions(options);
			quiz.setAnswer(word.getChinese());
		}
		return quiz;
	}

	@Override
	public List<Word> queryAllWords() {
		return vocabularyMapper.queryAllWords();
	}

}
