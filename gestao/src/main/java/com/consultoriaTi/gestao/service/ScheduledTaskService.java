package com.consultoriaTi.gestao.service;

import com.consultoriaTi.gestao.event.producer.MessengerPublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class ScheduledTaskService {
//    private final SurveyService surveyService;
//    private final MessengerPublisherService messengerService;
//    private final VoteService voteService;
    @Scheduled(cron = "1 * * * * *", zone = "America/Sao_Paulo")
    public void updateSurveyStatus() {
//        List<Survey> listSurveys = surveyService.findAllSurveysToUpdateSurveyStatusToClosed();
//        for (Survey survey : listSurveys) {
//            surveyService.save(survey.withStatus(SurveyStatusEnum.CLOSED));
//            messengerService.directPublisher(voteService.surveyResult(survey.getId()));
//        }
    }
}
