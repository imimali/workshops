package ro.axonsoft.internship.main;

import ro.axonsoft.internship.api.*;
import ro.axonsoft.internship.impl.StudentReader;
import ro.axonsoft.internship.impl.WorkshopFinderImpl;
import ro.axonsoft.internship.impl.WriterImpl;

import java.util.List;
import java.util.stream.Collectors;

public class Launcher {


    public static void main(String[] args) {
        Reader<StudentDescriptor> studentDescriptorReader = new StudentReader();
        List<StudentDescriptor> studentDescriptors = studentDescriptorReader.readFile("students.csv");
        Writer writer = new WriterImpl();
        WorkshopFinder workshopFinder = new WorkshopFinderImpl();

        List<SearchResult> searchResults = studentDescriptors.stream()
                .map(workshopFinder::getWorkshops)
                .collect(Collectors.toList());

        //writer.writeResult(searchResults);
        //new A();
        //new B();
        new A().something();
    }
}
