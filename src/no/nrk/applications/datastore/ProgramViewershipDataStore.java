package no.nrk.applications.datastore;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Note: A better solution would be to use Google BigQuery to import from CSV and 
 * then construct queries for each statistic
 */
public class ProgramViewershipDataStore implements DataStore {
    
    /* Totalt antall visninger av hver episode (for hele perioden) */
    Map<String, Long> viewsPerEpisode = new HashMap<>();
    
    /* Gjennomsnittlig antall visninger per ukedag */
    Map<DayOfWeek, Integer> numWeekdays = new HashMap<>();
    Map<DayOfWeek, Long> numViewsWeekday = new HashMap<>();
    
    /* Gjennomsnittlig antall visninger per time */
    long numHours = 0L;
    long numViews = 0L;
    
    Map<LocalDate, Long> numViewsForDate = new HashMap<>();
    
    
    
    //================================================================================
    // Initializer and statistics builder
    //================================================================================
    public ProgramViewershipDataStore(LocalDate startDate, LocalDate endDate){
        init(startDate, endDate);

    }

    private void init(LocalDate startDate, LocalDate endDate) {
        /* Set number of occurrences of each weekday */
        List<LocalDate> dates = getDays(startDate, endDate);
        dates.forEach(date -> numWeekdays.compute(date.getDayOfWeek(), (k, v) -> v == null ? 1 : v + 1));

        numHours = dates.size() * 24;
    }

    public void addViewing(String episodeId, LocalDate date) {
        numViews++;
        viewsPerEpisode.compute(episodeId, (k, v) -> v == null? 1 : v + 1);
        numViewsWeekday.compute(date.getDayOfWeek(), (k, v) -> v == null ? 1 : v + 1);
        numViewsForDate.compute(date, (k, v) -> v == null? 1 : v + 1);
    }
    
    
    //================================================================================
    // External Accessors
    //================================================================================

    public List<LocalDate> getDays(LocalDate startDate, LocalDate endDate) {
        int days =  (int) startDate.until(endDate, ChronoUnit.DAYS);
        return Stream.iterate(startDate, d -> d.plusDays(1))
                .limit(days)
                .collect(Collectors.toList());
    }

    public long getProgramViewsFor(LocalDate date) {
        return numViewsForDate.get(date);
    }

    public double getAverageViewsFor(DayOfWeek dayOfWeek) {
        return ((double) numViewsWeekday.get(dayOfWeek))/numWeekdays.get(dayOfWeek);
    }

    public double getAverageViewsPerHour() {
        return ((double)numViews)/numHours;
    }

    public long getTotalViewsFor(String episodeId) {
        if (viewsPerEpisode.containsKey(episodeId)){
            return viewsPerEpisode.get(episodeId);
        }
        throw new IllegalArgumentException(String.format
                ("Episode with episodeId: %s not found", episodeId));
    }

    public List<String> getEpisodes() {
        return new ArrayList<>(viewsPerEpisode.keySet());
    }
    
    public List<LocalDate> getAllDates() {
        return new ArrayList<>(numViewsForDate.keySet());
    }
    
}
