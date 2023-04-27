package core.model;

import model.LogEntry;

import java.util.List;

public class PersistentState {

    /**
     * latest term server has seen (initialized to 0
     * on first boot, increases monotonically)
     */
    private Long currentTerm;

    /**
     * candidateId that received vote in current
     * term (or null if none)
     */
    private Long voteFor;

    private List<LogEntry> logs;



}
