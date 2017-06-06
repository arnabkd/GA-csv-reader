package no.nrk.model;
/**
 * A simple POJO with getters
 */
@lombok.Data
public class ProgramViewership {

    private final String userId;
    private final String programId;
    private final long visitStartTime;
    private final long timeWithinVisitMS;

    public ProgramViewership(String userId, String programId, long visitStartTime, long timeWithinVisitMS) {
        this.userId = userId;
        this.programId = programId;
        this.visitStartTime = visitStartTime;
        this.timeWithinVisitMS = timeWithinVisitMS;
    }

}
