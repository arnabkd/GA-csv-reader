package no.nrk.model;

import lombok.NonNull;

/**
 * A simple POJO with lombok (for simplification)
 */
@lombok.Data
public class ProgramViewership {

    @NonNull
    private final String userId;
    
    @NonNull
    private final String programId;
    
    @NonNull
    private final long visitStartTime;
    
    @NonNull
    private final long timeWithinVisitMS;

}
