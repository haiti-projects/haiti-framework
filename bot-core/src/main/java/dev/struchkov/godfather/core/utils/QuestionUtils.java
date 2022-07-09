//package dev.struchkov.godfather.core.utils;
//
//import dev.struchkov.godfather.context.domain.BoxAnswer;
//import dev.struchkov.godfather.context.utils.KeyBoards;
//import dev.struchkov.godfather.core.domain.question.Question;
//import dev.struchkov.godfather.core.domain.question.QuestionAnswer;
//import dev.struchkov.godfather.core.domain.question.QuestionResult;
//import dev.struchkov.godfather.core.domain.unit.AnswerSave;
//import dev.struchkov.godfather.core.domain.unit.AnswerText;
//import dev.struchkov.godfather.core.domain.unit.MainUnit;
//import dev.struchkov.godfather.core.domain.unit.UnitActiveType;
//import dev.struchkov.godfather.context.service.save.Preservable;
//import dev.struchkov.godfather.context.service.save.Pusher;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
///**
// * Утилита для быстрой генерации цепочки Unit-ов, образующих сценарий "Тестирование".
// *
// * @author upagge [14/07/2019]
// */
//public class QuestionUtils {
//
//    private final Preservable<QuestionResult> preservable;
//    private final List<Question> questions;
//    private Pusher<QuestionResult> pusher;
//
//    private QuestionUtils(List<Question> questions, Preservable<QuestionResult> preservable) {
//        this.questions = questions;
//        this.preservable = preservable;
//    }
//
//    private QuestionUtils(List<Question> questions, Preservable<QuestionResult> preservable, Pusher<QuestionResult> pusher) {
//        this.questions = questions;
//        this.preservable = preservable;
//        this.pusher = pusher;
//    }
//
//    public static QuestionUtils builder(Preservable<QuestionResult> preservable, List<Question> questions) {
//        return new QuestionUtils(questions, preservable);
//    }
//
//    public static QuestionUtils builder(Preservable<QuestionResult> preservable, Pusher<QuestionResult> pusher, List<Question> list) {
//        return new QuestionUtils(list, preservable, pusher);
//    }
//
//    public MainUnit build(MainUnit finishUnit) {
//        return generateTest(finishUnit);
//    }
//
//    public MainUnit build() {
//        return generateTest(null);
//    }
//
//    private MainUnit generateTest(MainUnit finishUnit) {
//        AnswerText previousUnit = null;
//        for (int i = questions.size() - 1; i >= 0; i--) {
//            Question question = this.questions.get(i);
//            List<String> collectAnswer = question.getQuestionAnswers().stream()
//                    .map(QuestionAnswer::getText)
//                    .collect(Collectors.toList());
//            BoxAnswer boxAnswer = question.getBoxAnswer().toBuilder()
//                    .keyBoard(KeyBoards.verticalDuoMenuString(collectAnswer)).build();
//
//            AnswerText.Builder answerTextBuilder = AnswerText.builder()
//                    .boxAnswer(message -> boxAnswer);
//
//            for (QuestionAnswer questionAnswer : question.getQuestionAnswers()) {
//                AnswerSave.AnswerSaveBuilder answerSaveBuilder = AnswerSave.<QuestionResult>builder()
//                        .preservable(preservable)
//                        .preservableData(
//                                message -> new QuestionResult(
//                                        question.getBoxAnswer().getMessage(),
//                                        questionAnswer.getText(),
//                                        questionAnswer.getPoints()
//                                )
//                        )
//                        .phrase(questionAnswer.getText());
//                if (i != this.questions.size() - 1) {
//                    answerSaveBuilder.nextUnit(previousUnit).build();
//                } else {
//                    answerSaveBuilder.pusher(pusher);
//                    Optional.of(finishUnit).ifPresent(answerSaveBuilder::nextUnit);
//                }
//                answerTextBuilder.nextUnit(answerSaveBuilder.build());
//            }
//            if (i == 0) answerTextBuilder.activeType(UnitActiveType.AFTER);
//            previousUnit = answerTextBuilder.build();
//        }
//        return previousUnit;
//    }
//
//}
