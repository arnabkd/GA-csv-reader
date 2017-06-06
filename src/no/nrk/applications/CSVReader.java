package no.nrk.applications;

import no.nrk.model.ProgramViewership;

import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class CSVReader {
    private static final String DELIMITER = ",";
    
    public ProgramViewership getProgramViewership(String line) {
        List<String> params = Arrays.asList(line.split(DELIMITER));
        return new ProgramViewership(params.get(0), params.get(1), Long.parseLong(params.get(2)), Long.parseLong(params.get(3)));
    }
}
