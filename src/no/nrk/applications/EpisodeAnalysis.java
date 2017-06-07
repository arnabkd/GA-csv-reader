package no.nrk.applications;

import java.time.DayOfWeek;
import java.time.LocalDate;

public interface EpisodeAnalysis {

    /**
     * Get total program views for this date
     * @param year
     * @param month
     * @param day
     * @return
     */
    long getProgramViewsFor(int year, int month, int day);

    /**
     * Get average program views for Monday / Tuesday / Wednesday etc
     * @param dayOfWeek
     * @return
     */
    double getAverageViewsFor(DayOfWeek dayOfWeek);

    /**
     * Get total views for an episode
     * @param episodeId
     * @return
     */
    long getTotalViewsFor(String episodeId);

    /**
     * Get average program viewings per hour
     * @return
     */
    double getAverageViewsPerHour();


    /**
     * 
     * @param episodeId
     * @param date
     */
    void addViewing(String episodeId, LocalDate date);
}
