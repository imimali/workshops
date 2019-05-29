package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.SearchResult;
import ro.axonsoft.internship.api.WorkshopDescriptor;

import java.util.List;

public class SearchResultImpl implements SearchResult {
    private String studentName;
    private List<WorkshopDescriptor> workshops;

    public SearchResultImpl(String studentName, List<WorkshopDescriptor> workshops) {
        this.studentName = studentName;
        this.workshops = workshops;
    }

    @Override
    public String getStudentName() {
        return studentName;
    }

    @Override
    public List<WorkshopDescriptor> getWorkshops() {
        return workshops;
    }

    @Override
    public String toString() {
        return "SearchResultImpl{" +
                "studentName='" + studentName + '\'' +
                ", workshops=" + workshops +
                '}';
    }
}
