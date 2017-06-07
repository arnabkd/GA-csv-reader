package no.nrk;

import no.nrk.applications.CSVReader;
import no.nrk.applications.ProgramStatisticsModule;
import no.nrk.applications.ProgramViewershipDataStore;

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
        
        ProgramViewershipDataStore dataStore = new ProgramViewershipDataStore(start, end);
        ProgramStatisticsModule analysisModule = new ProgramStatisticsModule(dataStore);
        CSVReader csvReader = new CSVReader();
        try {
            csvReader.readFileToModule(fileName, delimiter, dataStore);
            
            analysisModule.showEpisodeStatistics();
            analysisModule.showWeekDayStatistics();
            analysisModule.showHourlyStatistics();
            analysisModule.showViewsPerDay();
            
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
     
    }
}
