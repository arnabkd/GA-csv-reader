/**
 * A simple POJO with getters
 */
public class ProgramViewership {

    private final String userId;
    private final String programId;
    private final long visitStartTime;
    private final long timeWithinVisitMS;

    public String getUserId() {
        return userId;
    }

    public String getProgramId() {
        return programId;
    }

    public long getVisitStartTime() {
        return visitStartTime;
    }

    public long getTimeWithinVisitMS() {
        return timeWithinVisitMS;
    }
    
    public ProgramViewership(String userId, String programId, long visitStartTime, long timeWithinVisitMS){
        this.userId = userId;
        this.programId = programId;
        this.visitStartTime = visitStartTime;
        this.timeWithinVisitMS = timeWithinVisitMS;
    }
    
}
