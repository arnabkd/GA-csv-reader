package no.nrk.applications;

import java.time.DayOfWeek;
import java.time.LocalDate;

public interface EpisodeAnalysis {

    /**
     * Get total program views on a given date
     * @param date
     * @return
     */
    public long getProgramViewsFor(LocalDate date);

    /**
     * Get average program views for Monday / Tuesday / Wednesday etc
     * @param dayOfWeek
     * @return
     */
    public double getAverageViewsFor(DayOfWeek dayOfWeek);

    /**
     * Get total views for an episode
     * @param episodeId
     * @return
     */
    public long getTotalViewsFor(String episodeId);

    /**
     * Get average program viewings per hour
     * @return
     */
    public double getAverageViewsPerHour();
    
}
