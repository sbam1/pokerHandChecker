package com.mygame.poker.service;

import java.util.Map;

import static com.mygame.poker.util.Constants.REASON;
import static com.mygame.poker.util.Constants.RESULT;
import static com.mygame.poker.util.Constants.TIE;
import static com.mygame.poker.util.Constants.WINNER;

public class LoggerExecutionService implements ExecutionService {

    @Override
    public void execute(Map<String, Object> moduleObject) {
        if ((boolean) moduleObject.get(TIE)) {
            System.out.println(TIE);
        }

        if ((boolean) moduleObject.get(RESULT) && !(boolean) moduleObject.get(TIE)) {
            System.out.println(moduleObject.get(WINNER) + " wins. - with " + moduleObject.get(REASON));
        }
    }
}
