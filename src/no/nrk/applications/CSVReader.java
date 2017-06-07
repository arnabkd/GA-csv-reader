package no.nrk.applications;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.stream.Stream;

/**
 * Created as part of
 */
public class CSVReader {
    
    public CSVReader(String fileName, String delimiter, EpisodeAnalysisModule analysisModule){
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.skip(1).forEach(line -> processLine(line, delimiter, analysisModule));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private void processLine(String line, String delimiter, EpisodeAnalysisModule analysisModule) {
        String [] row = line.split(delimiter);
        if (row.length != 4) {
            throw new IllegalArgumentException("malformed data row");
        }

        process(row, analysisModule);
    }

    private void process(String [] row, EpisodeAnalysisModule analysisModule){
        // Important: the epoch column is in seconds, not milliseconds
        LocalDate date = Instant.ofEpochSecond(Long.parseLong(row[2])).atZone(ZoneId.systemDefault()).toLocalDate();
        
        analysisModule.addViewing(row[1],date);
    }
}
