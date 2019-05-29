package ro.axonsoft.internship.api;

import java.time.LocalTime;

public interface WorkshopDescriptor {
    String getName();

    String getTopic();

    Integer getRoomNumber();

    LocalTime getStartTime();

    Integer getDuration();
}
