package no.hib.megagruppe.webpoll.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import no.hib.megagruppe.webpoll.data.RepositoryFactory;
import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.models.lecturer.QuestionCreationModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyOverviewModel;

public class SurveyLecturerServiceImpl implements SurveyLecturerService {
	private final RepositoryFactory repositoryFactory;
	
	@Inject
	public SurveyLecturerServiceImpl(RepositoryFactory repositoryFactory) {
		this.repositoryFactory = repositoryFactory;
	}

	@Override
	public List<SurveyOverviewModel> getSurveyOverviews(Integer userID) {
		
		List<SurveyEntity> allSurveys = repositoryFactory.getSurveyRepository().findAll();
		List<SurveyOverviewModel> userSurveys = new ArrayList<>();
		
		for (SurveyEntity survey : allSurveys) {
			if (survey.getOwner().getId().equals(userID)) {
				SurveyOverviewModel surveyOverview = convertSurvey(survey);
				userSurveys.add(surveyOverview);
			}
		}
		
		return userSurveys;
	}

	@Override
	public Boolean cloneSurvey(Integer surveyID,String name) {
		
		SurveyEntity survey = repositoryFactory.getSurveyRepository().findById(surveyID);
		Boolean foundSurvey = false;
		
		if (survey != null) {
			foundSurvey = true;
			
			SurveyCreationModel surveyCreation = new SurveyCreationModel(survey.getOwner());
			surveyCreation.setName(name);
			surveyCreation.setOwner(survey.getOwner());
			copyQuestions(survey,surveyCreation);
			commitNewSurvey(surveyCreation);
		}
		
		return foundSurvey;
	}

	@Override
	public void commitNewSurvey(SurveyCreationModel creationModel) {
		SurveyEntity survey = creationModel.createSurvey();
		repositoryFactory.getSurveyRepository().add(survey);
	}
	
	private SurveyOverviewModel convertSurvey(SurveyEntity survey) {
		 // TODO Konvertere survey til surveyOverview
		return null;
	}
	
	private void copyQuestions(SurveyEntity survey, SurveyCreationModel surveyCreation) {
		for (QuestionEntity question : survey.getQuestions()) {
			
			QuestionCreationModel questionCreation = new QuestionCreationModel(question.getType(), question.getText());

			if (question.getType().isMultipleChoice()) {
				List<OptionEntity> optionEntities = question.getOptions();
				String[] options = new String[optionEntities.size()];
				
				for (int i = 0; i < optionEntities.size(); i++) {
					options[i] = optionEntities.get(i).getText();
				}
				
				questionCreation.setOptions(options);
			}
			
			surveyCreation.addQuestionCreationModel(questionCreation);
		}
	}

}
