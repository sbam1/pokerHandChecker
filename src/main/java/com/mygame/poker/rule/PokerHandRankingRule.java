package com.mygame.poker.rule;

import java.util.Map;

public interface PokerHandRankingRule {
    Map<String, Object> executeRule(Map<String, Object> modelObject);
}
