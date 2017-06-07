package no.nrk.applications;

import java.time.LocalDate;

public interface ProgramStatistics {

    /**
     * Show number of views (on average) per day of week
     */
    void showWeekDayStatistics();

    /**
     * Show number of views per episode
     */
    void showEpisodeStatistics();


    /**
     * Show number of program views every hour
     */
    void showHourlyStatistics();

    /**
     * Show total number of views per day
     */
    void showViewsPerDay();

    /**
     * Add an entry to the module
     * @param episodeId
     * @param date
     */
    void addViewing(String episodeId, LocalDate date);
    
}
