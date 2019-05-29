package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.StudentDescriptor;

import java.time.LocalTime;
import java.util.List;

public class StudentDescriptorImpl implements StudentDescriptor {
    private String fullName;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<String> topics;

    public StudentDescriptorImpl(String fullName, LocalTime startTime, LocalTime endTime, List<String> topics) {
        this.fullName = fullName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.topics = topics;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public List<String> getTopics() {
        return topics;
    }

    @Override
    public String toString() {
        return "StudentDescriptorImpl{" +
                "fullName='" + fullName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", topics=" + topics +
                '}';
    }
}
