package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.WorkshopDescriptor;

import java.time.LocalTime;

public class WorkshopDescriptorImpl implements WorkshopDescriptor {
    private String name;
    private String topic;
    private Integer roomNumber;
    private LocalTime startTime;
    private Integer duration;

    public WorkshopDescriptorImpl(String name, String topic, Integer roomNumber, LocalTime startTime, Integer duration) {
        this.name = name;
        this.topic = topic;
        this.roomNumber = roomNumber;
        this.startTime = startTime;
        this.duration = duration;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public Integer getRoomNumber() {
        return roomNumber;
    }

    @Override
    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public Integer getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "WorkshopDescriptorImpl{" +
                "name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                ", roomNumber=" + roomNumber +
                ", startTime=" + startTime +
                ", duration=" + duration +
                '}';
    }
}
