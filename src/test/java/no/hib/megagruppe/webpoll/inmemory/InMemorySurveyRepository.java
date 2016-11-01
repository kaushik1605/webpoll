package no.hib.megagruppe.webpoll.inmemory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;



// TEMPORARY!!!!!!
public class InMemorySurveyRepository implements SurveyRepository {
    private Map<Integer, SurveyEntity> surveys;

    QuestionEntity question1; public String getQuestion1Text(){return question1.getText();}
    QuestionEntity question2; public String getQuestion2Text(){return question2.getText();}
    public InMemorySurveyRepository() {
        surveys = new HashMap<>();

        UserEntity user = new UserEntity();
        user.setEmail("test@testesen.no");
        user.setFirstName("Test");
        user.setLastName("Testesen");
        user.setId(1);

        //////
        OptionEntity optionA = new OptionEntity();
        optionA.setId(1);
        optionA.setText("Ja");

        OptionEntity optionB = new OptionEntity();
        optionB.setId(2);
        optionB.setText("Nei");

        question1 = new QuestionEntity();
        question1.setId(1);
        question1.setText("Har du noen gang programmert JavaEE?");
        question1.setType(QuestionEntity.QuestionType.MULTIPLE_CHOICE_RADIO);

        List<OptionEntity> options = new ArrayList<>();
        options.add(optionA);
        options.add(optionB);
        question1.setOptions(options);

        optionA.setQuestion(question1);
        optionB.setQuestion(question1);
        //////
        //////
        question2 = new QuestionEntity();
        question2.setId(2);
        question2.setText("Hva synes du om WebPoll?");
        question2.setType(QuestionEntity.QuestionType.FREE_TEXT);
        //////

        SurveyEntity survey = new SurveyEntity();
        survey.setId(1);
        survey.setName("Testundersøkelse");
        survey.setDateCreated(new Timestamp(System.currentTimeMillis() - 3600));
        survey.setDeadline(new Timestamp(System.currentTimeMillis() + 36000000));
        survey.setOwner(user);
        survey.setActive(true);
        survey.setCode("testabc");

        List<QuestionEntity> questions = new ArrayList<>();
        question1.setSurvey(survey);
        question2.setSurvey(survey);
        questions.add(question1);
        questions.add(question2);

        survey.setQuestions(questions);

        surveys.put(survey.getId(), survey);
    }

    @Override
    public SurveyEntity add(SurveyEntity entity) {
        surveys.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public SurveyEntity findById(int id) {
        SurveyEntity result = null;
        for (SurveyEntity survey : surveys.values()) {
            result = survey;
        }
        return result;
    }

    @Override
    public List<SurveyEntity> findAll() {
        List<SurveyEntity> result = new ArrayList<>();
        for (SurveyEntity e : surveys.values()) {
            result.add(e);
        }
        return result;
    }

    @Override
    public SurveyEntity update(SurveyEntity entity) {
        SurveyEntity current = findById(entity.getId());
        if (current != null) {
            surveys.put(entity.getId(), entity);
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public void remove(SurveyEntity entity) {
        surveys.remove(entity.getId());
    }

    @Override
    public SurveyEntity findByCode(String code) {
        SurveyEntity result = null;
        for (SurveyEntity survey : surveys.values()) {
            if (survey.getCode().equals(code)) {
                result = survey;
            }
        }
        return result;
    }
}