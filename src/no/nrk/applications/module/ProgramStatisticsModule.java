package no.nrk.applications.module;

import no.nrk.applications.datastore.ProgramViewershipDataStore;

import java.time.DayOfWeek;
import java.util.Arrays;

public class ProgramStatisticsModule implements ProgramStatistics {

    private final ProgramViewershipDataStore dataStore;

    public ProgramStatisticsModule(ProgramViewershipDataStore dataStore){
        this.dataStore = dataStore;
    }
    
    public void showWeekDayStatistics() {
        System.out.println("**** Weekday statistics *****");
        Arrays.asList(DayOfWeek.values()).forEach(weekday ->
                System.out.println(
                        String.format("Average views for %ss : %s", weekday.name().toLowerCase(), dataStore.getAverageViewsFor(weekday))));
        System.out.println("*****************************\n");

    }

    public void showEpisodeStatistics() {
        System.out.println("**** Episode statistics *****");
        dataStore.getEpisodes().forEach(episode ->
                System.out.println(String.format
                        ("Number of views for episode %s: %d", episode, dataStore.getTotalViewsFor(episode))));
        System.out.println("*****************************\n");
    }

    public void showHourlyStatistics() {
        System.out.println("**** Hourly statistics *****");
        System.out.println("Average unique viewings every hour: " + dataStore.getAverageViewsPerHour());
        System.out.println("*****************************\n");
    }

    public void showViewsPerDay() {
        System.out.println("**** Daily statistics *****");
        dataStore.getAllDates().forEach(day ->
                System.out.println(String.format
                        ("Number of unique views on %s: %d", day, dataStore.getProgramViewsFor(day))));
        System.out.println("*****************************\n");
    }

}
