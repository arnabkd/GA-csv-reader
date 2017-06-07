package no.nrk;

import no.nrk.applications.CSVReader;
import no.nrk.applications.EpisodeAnalysisModule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Created as part of
 */
public class Main {
    
    public static void main(String [] args){
        String fileName = "../unge-lovende/unge-lovende.csv";
        String delimiter = ",";
        LocalDate start = LocalDate.of(2015, 1, 1);
        LocalDate end = LocalDate.of(2015, 12, 20);


        EpisodeAnalysisModule analysisModule = new EpisodeAnalysisModule(start, end);
        CSVReader episodeAnalysis = new CSVReader(fileName, delimiter, analysisModule);
        
        
        System.out.println(analysisModule.getTotalViewsFor("KMTE20000214"));
        System.out.println(analysisModule.getAverageViewsPerHour());
        
        Arrays.asList(DayOfWeek.values()).forEach(day -> System.out.println(analysisModule.getAverageViewsFor(day)));
        
        
        System.out.println(analysisModule.getProgramViewsFor(LocalDate.of(2015, 12, 15)));
    }
}
