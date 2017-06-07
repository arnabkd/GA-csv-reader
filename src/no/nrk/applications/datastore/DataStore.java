package no.nrk.applications.datastore;

import java.time.LocalDate;


public interface DataStore {

    void addViewing(String episodeId, LocalDate date);
    
}
