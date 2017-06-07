package no.nrk;

import no.nrk.applications.CSVReader;
import no.nrk.applications.EpisodeAnalysisModule;

import java.io.IOException;
import java.time.LocalDate;

/**
 * 
 */
public class Main {
    
    public static void main(String [] args){
        String fileName = "../unge-lovende/unge-lovende.csv";
        String delimiter = ",";
        LocalDate start = LocalDate.of(2015, 1, 1);
        LocalDate end = LocalDate.of(2015, 12, 20);


        EpisodeAnalysisModule analysisModule = new EpisodeAnalysisModule(start, end);
        CSVReader csvReader = new CSVReader();
        try {
            csvReader.readFileToModule(fileName, delimiter, analysisModule);
            
            analysisModule.showEpisodeStatistics();
            analysisModule.showWeekDayStatistics();
            analysisModule.showHourlyStatistics();
            analysisModule.showViewsPerDay();
            
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
     
    }
}
