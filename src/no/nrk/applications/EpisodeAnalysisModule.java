package no.nrk.applications;

import no.nrk.model.ProgramViewership;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
public class EpisodeAnalysisModule {

    private static final String DELIMITER = ",";
    
    private Map<String, Integer> episodeViews = new HashMap<>();
    private Map<DayOfWeek, Integer> programViewsPerDay = new HashMap<>();
    
    private int numDaysWithViews = 0;
    private int totalProgramViews = 0;
    

    private ProgramViewership fromLine(String line) {
        List<String> vals = Arrays.asList(line.split(DELIMITER));
        return new ProgramViewership(vals.get(0), vals.get(1), 
                Long.parseLong(vals.get(2)), Long.parseLong(vals.get(3)));
    }
    
    public int getTotalViewsForEpisode(String programId){
        if (!episodeViews.containsKey(programId)){
            throw new IllegalArgumentException(String.format("ProgramId: %s not found",programId));
        }else {
            return episodeViews.get(programId);
        }
    }
    
    public double getAverageProgramViewsPerDay(){
        return ((double) numDaysWithViews) / totalProgramViews;
    }
    
    
}
