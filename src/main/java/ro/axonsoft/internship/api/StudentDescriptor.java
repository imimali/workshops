package ro.axonsoft.internship.api;

import java.time.LocalTime;
import java.util.List;

public interface StudentDescriptor {
    String getFullName();

    LocalTime getStartTime();

    LocalTime getEndTime();

    List<String> getTopics();
}
