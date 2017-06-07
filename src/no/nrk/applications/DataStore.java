package no.nrk.applications;

import java.time.LocalDate;


public interface DataStore {

    void addViewing(String episodeId, LocalDate date);
    
}
