package no.nrk.applications.module;

import no.nrk.applications.datastore.ProgramViewershipDataStore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProgramStatisticsModuleTest {
    
    ProgramViewershipDataStore dataStoreMock;
    ProgramStatisticsModule statisticsModule;
    
    @Before
    public void setUp(){
        dataStoreMock = Mockito.mock(ProgramViewershipDataStore.class);
        statisticsModule = new ProgramStatisticsModule(dataStoreMock);
    }

    @Test
    /**
     * Verify that the weekly statistics method is querying the data store seven times
     */
    public void testShowWeekDayStatistics() throws Exception {
        statisticsModule.showWeekDayStatistics();
        verify(dataStoreMock, times(DayOfWeek.values().length)).getAverageViewsFor(any(DayOfWeek.class));
    }
    
    
    @Test
    /**
     * Verify that for a list of episodes, the data store is being queried once per episode Id
     */
    public void testShowEpisodeStatistics() throws Exception {
        List<String> episodeList = Arrays.asList("mockEpisode#1", "mockEpisode#2", "mockEpisode#3");
        when(dataStoreMock.getEpisodes()).thenReturn(episodeList);
        episodeList.forEach(episode -> when(dataStoreMock.getTotalViewsFor(episode)).thenReturn(1L));
        
        statisticsModule.showEpisodeStatistics();
        episodeList.forEach(episode -> verify(dataStoreMock, times(1)).getTotalViewsFor(episode));
    }

    @Test
    public void testShowHourlyStatistics() throws Exception {
        statisticsModule.showHourlyStatistics();
        verify(dataStoreMock, times(1)).getAverageViewsPerHour();
    }

    @Test
    /**
     * Verify that for a list of dates, the data store is being queried once per date
     */
    public void testShowViewsPerDay() throws Exception {
        List<LocalDate> dates = Arrays.asList(LocalDate.of(2015, 12, 20), LocalDate.of(2015, 12, 19), LocalDate.of(2015, 12, 18));
        when(dataStoreMock.getAllDates()).thenReturn(dates);
        statisticsModule.showViewsPerDay();
        dates.forEach(date -> verify(dataStoreMock, times(1)).getProgramViewsFor(date));
    }
}