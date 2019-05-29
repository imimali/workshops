package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.*;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkshopFinderImpl implements WorkshopFinder {
    @Override
    public SearchResult getWorkshops(StudentDescriptor studentDescriptor) {
        Reader<WorkshopDescriptor> workshopReader = new WorkshopReader();
        List<WorkshopDescriptor> workshops = workshopReader.readFile("workshops.csv");

        //if the student has preferred topics, filter workshops by those
        if (studentDescriptor.getTopics().size() > 0) {
            workshops = filterByStudentPreferredTopics(studentDescriptor, workshops);
        }

        //leave only workshops that fit in the student's schedule
        workshops = filterByStudentAvailability(studentDescriptor, workshops);

        //interval scheduling algorithm
        workshops.sort(Comparator.comparing(wd -> wd.getStartTime().plusMinutes(wd.getDuration())));

        List<WorkshopDescriptor> possibleWorkshops = new LinkedList<>();
        if (workshops.size() > 0) {
            WorkshopDescriptor lastWorkshopAdded = workshops.get(0);
            possibleWorkshops.add(lastWorkshopAdded);
            for (WorkshopDescriptor wd : workshops.subList(1, workshops.size())) {
                if (isValid(wd, lastWorkshopAdded)) {
                    possibleWorkshops.add(wd);
                    lastWorkshopAdded = wd;
                }
            }
        }
        return new SearchResultImpl(studentDescriptor.getFullName(), possibleWorkshops);
    }

    /***
     * checks whether one workshop can follow another. If they are in the same room, they
     * can directly follow one another, otherwise we consider an additional delay of ten minutes
     * @param current the workshop we want to check
     * @param lastAdded the workshop that is said to be already accepted, against which we test
     * @return true if current can start after lastAdded without overlap, false otherwise
     */
    private boolean isValid(WorkshopDescriptor current, WorkshopDescriptor lastAdded) {
        Integer delay = 0;
        if (!current.getRoomNumber().equals(lastAdded.getRoomNumber())) {
            delay = 10;
        }
        return current.getStartTime().plusMinutes(1)//avoid equality check
                .isAfter(lastAdded.getStartTime()
                        .plusMinutes(lastAdded.getDuration() + delay));
    }

    /***
     * filter workshops by the student's preferred topics
     * @param studentDescriptor data about the student
     * @param workshops the list of workshop descriptors to be filtered
     * @return a list of workshop descriptors whose topics match the students preferences
     */
    private List<WorkshopDescriptor> filterByStudentPreferredTopics(StudentDescriptor studentDescriptor,
                                                                    List<WorkshopDescriptor> workshops) {
        return workshops
                .stream()
                .filter(workshopDescriptor ->
                        studentDescriptor.getTopics()
                                .stream()
                                .anyMatch(topic -> topic.equals(workshopDescriptor.getTopic())))
                .collect(Collectors.toList());

    }

    /**
     * filter workshops by the time the student can attend them
     *
     * @param studentDescriptor data about the student
     * @param workshops         the list of workshop descriptors to be filtered
     * @return a list of workshop descriptors whose start and end times fit in the student's schedule
     */
    private List<WorkshopDescriptor> filterByStudentAvailability(StudentDescriptor studentDescriptor,
                                                                 List<WorkshopDescriptor> workshops) {
        return workshops.stream()
                .filter(workshopDescriptor -> workshopDescriptor
                        .getStartTime().plusMinutes(1)// avoid another check if they are equal
                        .isAfter(studentDescriptor.getStartTime()) &&
                        workshopDescriptor.getStartTime()
                                .plusMinutes(workshopDescriptor
                                        .getDuration())
                                .minusMinutes(1)// to avoid equality check
                                .isBefore(studentDescriptor.getEndTime())
                ).collect(Collectors.toList());
    }
}
