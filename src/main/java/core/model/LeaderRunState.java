package core.model;

import java.util.List;

public class LeaderRunState {

    private List<Long> nextIndex;

    private List<Long> matchIndex;

    public List<Long> getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(List<Long> nextIndex) {
        this.nextIndex = nextIndex;
    }

    public List<Long> getMatchIndex() {
        return matchIndex;
    }

    public void setMatchIndex(List<Long> matchIndex) {
        this.matchIndex = matchIndex;
    }
}
