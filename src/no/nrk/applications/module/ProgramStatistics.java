package no.nrk.applications.module;

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
    
}
