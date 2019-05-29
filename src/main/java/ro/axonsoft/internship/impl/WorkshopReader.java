package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.ReaderException;
import ro.axonsoft.internship.api.WorkshopDescriptor;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import static ro.axonsoft.internship.impl.FileOperationConstants.SEPARATOR;

public class WorkshopReader extends AbstractReader<WorkshopDescriptor> {


    @Override
    public WorkshopDescriptor readLine(String line) throws ReaderException {
        List<String> fields = Arrays.asList(line.split(SEPARATOR));
        if (fields.size() != 5) {
            throw new ReaderException("Invalid number of fields");
        }
        try {
            String name = fields.get(0);
            String topic = fields.get(1);
            Integer roomNumber = Integer.parseInt(fields.get(2));
            LocalTime startTime = LocalTime.parse(fields.get(3));
            Integer duration = Integer.parseInt(fields.get(4));
            return new WorkshopDescriptorImpl(name, topic, roomNumber, startTime, duration);
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new ReaderException(e.getMessage());
        }
    }
}
