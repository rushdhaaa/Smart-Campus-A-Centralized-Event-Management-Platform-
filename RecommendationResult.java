package com.campus.events.dto;

import com.campus.events.model.Event;

/**
 * DTO carrying a recommended event together with its AI-derived match label and score.
 * No Lombok — explicit constructor + getters.
 */
public class RecommendationResult {

    public enum MatchLevel { HIGH, MEDIUM, LOW }

    private final Event  event;
    private final MatchLevel matchLevel;   // HIGH / MEDIUM / LOW
    private final int    matchScore;       // 0-100
    private final String matchReason;      // one-line explanation from AI

    public RecommendationResult(Event event, MatchLevel matchLevel,
                                int matchScore, String matchReason) {
        this.event       = event;
        this.matchLevel  = matchLevel;
        this.matchScore  = matchScore;
        this.matchReason = matchReason;
    }

    public Event      getEvent()       { return event;       }
    public MatchLevel getMatchLevel()  { return matchLevel;  }
    public int        getMatchScore()  { return matchScore;  }
    public String     getMatchReason() { return matchReason; }
}
