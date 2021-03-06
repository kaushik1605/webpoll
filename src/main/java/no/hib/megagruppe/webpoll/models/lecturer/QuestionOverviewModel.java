package no.hib.megagruppe.webpoll.models.lecturer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class that contains all the answerresponses for a specific question.
 * @author Magnus
 *
 */
public class QuestionOverviewModel {
	private String questionText;
	private HashMap<String, Integer> frequencyTable;
	private int frequencySum;
	private List<QuestionAnswerOverviewModel> answers;
	
	public QuestionOverviewModel(String questionText) {
		this.questionText = questionText;
		frequencyTable = new HashMap<>();
		frequencySum = 0;
	}
	
	/**
	 * Adds an answer to this question.
	 * @param answer The answer to be added.
	 */
	public void addAnswer(String answer){
		Integer frequency = frequencyTable.get(answer);
		if(frequency == null){
			frequencyTable.put(answer, 1);
		} else {
			frequencyTable.put(answer, frequency+1);
		}
		
		frequencySum++;
	}
	
	/**
	 * Creates and returns a list of all the answers in the form of QuestionAnswerOverviewModels.
	 * QuestionAnswerOverviewModels contains the answertext, the number of times it has been chosen as an answer,
	 * and the frequency-percentage of all the different answers.
	 * The list is in descending order, with the highest-frequency answer at index 0.
	 * @return a List of all the answers in the form of QuestionAnswerOverviewModels.
	 */
	public List<QuestionAnswerOverviewModel> getAnswers(){
		answers = new ArrayList<>();
		
		for(String key : frequencyTable.keySet()){
			String answerText = key;
			Integer frequency = frequencyTable.get(key);
			double percentage = (double)frequency / frequencySum * 100;
			QuestionAnswerOverviewModel answer = new QuestionAnswerOverviewModel(answerText, frequency,
					percentage);
			
			answers.add(answer);
		}
		
		answers.sort((o1, o2) -> o2.getFrequency()-o1.getFrequency());
		
		return answers;
	}
	
	public String getQuestionText(){
		return questionText;
	}

}
