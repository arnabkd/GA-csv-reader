package no.nrk.applications;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Note: A better solution would be to use Google BigQuery to import from CSV and 
 * then construct queries for each statistic
 */
public class EpisodeAnalysisModule implements ProgramStatistics {
    
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
    public EpisodeAnalysisModule(LocalDate startDate, LocalDate endDate){
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
    // ProgramStatistics implementation
    //================================================================================
    public void showWeekDayStatistics() {
        System.out.println("**** Weekday statistics *****");
        Arrays.asList(DayOfWeek.values()).forEach(weekday -> 
                System.out.println(
                        String.format("Average views for %ss : %s", weekday.name().toLowerCase(), getAverageViewsFor(weekday))));
        System.out.println("*****************************\n");

    }

    public void showEpisodeStatistics() {
        System.out.println("**** Episode statistics *****");
        viewsPerEpisode.keySet().forEach(episode -> 
                System.out.println(String.format
                        ("Number of views for episode %s: %d", episode, getTotalViewsFor(episode))));
        System.out.println("*****************************\n");
    }

    public void showHourlyStatistics() {
        System.out.println("**** Hourly statistics *****");
        System.out.println("Average unique viewings every hour: " + getAverageViewsPerHour());
        System.out.println("*****************************\n");
    }

    public void showViewsPerDay() {
        System.out.println("**** Daily statistics *****");
        numViewsForDate.keySet().forEach(day ->
                System.out.println(String.format
                        ("Number of unique views on %s: %d", day, getProgramViewsFor(day))));
        System.out.println("*****************************\n");
    }

    
    
    //================================================================================
    // Accessors
    //================================================================================

    private List<LocalDate> getDays(LocalDate startDate, LocalDate endDate) {
        int days =  (int) startDate.until(endDate, ChronoUnit.DAYS);
        return Stream.iterate(startDate, d -> d.plusDays(1))
                .limit(days)
                .collect(Collectors.toList());
    }

    private long getProgramViewsFor(LocalDate date) {
        return numViewsForDate.get(date);
    }

    private double getAverageViewsFor(DayOfWeek dayOfWeek) {
        return ((double) numViewsWeekday.get(dayOfWeek))/numWeekdays.get(dayOfWeek);
    }

    private double getAverageViewsPerHour() {
        return ((double)numViews)/numHours;
    }

    private long getTotalViewsFor(String episodeId) {
        if (viewsPerEpisode.containsKey(episodeId)){
            return viewsPerEpisode.get(episodeId);
        }
        throw new IllegalArgumentException(String.format
                ("Episode with episodeId: %s not found", episodeId));
    }

}
