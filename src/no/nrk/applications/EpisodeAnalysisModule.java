package no.nrk.applications;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class EpisodeAnalysisModule implements EpisodeAnalysis, EpisodeDataStore {
    
    /* Totalt antall visninger av hver episode (for hele perioden) */
    Map<String, Long> viewsPerEpisode = new HashMap<>();
    
    /* Gjennomsnittlig antall visninger per ukedag */
    Map<DayOfWeek, Integer> numWeekdays = new HashMap<>();
    Map<DayOfWeek, Long> numViewsWeekday = new HashMap<>();
    
    /* Gjennomsnittlig antall visninger per time */
    long numHours = 0L;
    long numViews = 0L;
    
    /* Antall visninger per dato (for hele perioden) */
    Map<String, Long> numViewsDate = new HashMap<>();
    
    public EpisodeAnalysisModule(LocalDate startDate, LocalDate endDate){
        /* Set number of occurrences of each weekday */
        List<LocalDate> dates = getDays(startDate, endDate);
        dates.forEach(date -> numWeekdays.compute(date.getDayOfWeek(), (k, v) -> v == null ? 1 : v + 1));
        
        numHours = dates.size() * 24;
    }

    private List<LocalDate> getDays(LocalDate startDate, LocalDate endDate) {
        int days =  (int) startDate.until(endDate, ChronoUnit.DAYS);
        return Stream.iterate(startDate, d -> d.plusDays(1))
                .limit(days)
                .collect(Collectors.toList());
    }

    public long getProgramViewsFor(LocalDate date) {
        System.out.println("date in map?" + numViewsDate.containsKey(date));
        return numViewsDate.get(date);
    }

    public double getAverageViewsFor(DayOfWeek dayOfWeek) {
        System.out.println("Showing average views for day: " + dayOfWeek.name());
        return ((double) numViewsWeekday.get(dayOfWeek))/numWeekdays.get(dayOfWeek);
    }

    public double getAverageViewsPerHour() {
        System.out.println("Showing average views per hour: ");
        return ((double)numViews)/numHours;
    }

    public long getTotalViewsFor(String episodeId) {
        System.out.println("Showing total views for episode: " + episodeId);
        if (viewsPerEpisode.containsKey(episodeId)){
            return viewsPerEpisode.get(episodeId);
        }
        throw new IllegalArgumentException(String.format
                ("Episode with episodeId: %s not found", episodeId));
    }

    public void addViewing(String episodeId, LocalDate date) {
        numViews++;
        
        numViewsDate.compute(date.toString(), (k, v) -> v == null ? 1 : v + 1);
        
        viewsPerEpisode.compute(episodeId, (k, v) -> v == null? 1 : v + 1);
        
        numViewsWeekday.compute(date.getDayOfWeek(), (k, v) -> v == null? 1 : v + 1);
    }

}
