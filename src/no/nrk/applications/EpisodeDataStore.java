package no.nrk.applications;

import java.time.LocalDate;

/**
 * Created as part of
 */
public interface EpisodeDataStore {
    
    void addViewing(String episodeId, LocalDate date);
    
}