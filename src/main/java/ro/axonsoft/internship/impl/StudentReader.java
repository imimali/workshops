package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.ReaderException;
import ro.axonsoft.internship.api.StudentDescriptor;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import static ro.axonsoft.internship.impl.FileOperationConstants.SEPARATOR;

public class StudentReader extends AbstractReader<StudentDescriptor> {


    @Override
    public StudentDescriptor readLine(String line) throws ReaderException {
        List<String> fields = Arrays.asList(line.split(SEPARATOR));
        if (fields.size() < 3) {
            throw new ReaderException("invalid number of fields");
        }
        try {
            String name = fields.get(0);
            LocalTime startTime = LocalTime.parse(fields.get(1));
            LocalTime endTime = LocalTime.parse(fields.get(2));
            List<String> topics = fields.subList(3, fields.size());
            return new StudentDescriptorImpl(name, startTime, endTime, topics);
        } catch (DateTimeParseException de) {
            throw new ReaderException(de.getMessage());
        }

    }
}