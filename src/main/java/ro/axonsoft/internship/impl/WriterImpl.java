package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.api.Writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static ro.axonsoft.internship.impl.FileOperationConstants.LINE_SEPARATOR;
import static ro.axonsoft.internship.impl.FileOperationConstants.SEPARATOR;

public class WriterImpl implements Writer {
    @Override
    public void writeResult(List<SearchResult> results) {
        results.forEach(searchResult -> {
            String fileName = searchResult
                    .getStudentName().toLowerCase().replace(' ', '_') + ".txt";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                searchResult.getWorkshops().forEach(workshopDescriptor -> {
                    try {
                        bw.write(workshopDescriptor.getName() + SEPARATOR
                                + workshopDescriptor.getTopic() + SEPARATOR
                                + workshopDescriptor.getRoomNumber() + SEPARATOR
                                + workshopDescriptor.getStartTime() + SEPARATOR
                                + workshopDescriptor.getDuration() + LINE_SEPARATOR);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                });
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });
    }
}
