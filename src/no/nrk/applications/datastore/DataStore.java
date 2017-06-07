package no.nrk.applications.datastore;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


public interface DataStore {

    void addViewing(String episodeId, LocalDate date);

    List<LocalDate> getDays(LocalDate startDate, LocalDate endDate);

    long getProgramViewsFor(LocalDate date);

    double getAverageViewsFor(DayOfWeek dayOfWeek);

    double getAverageViewsPerHour();

    long getTotalViewsFor(String episodeId);

    List<String> getEpisodes();

    List<LocalDate> getAllDates();
}
