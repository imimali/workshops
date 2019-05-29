package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.Reader;
import ro.axonsoft.internship.api.ReaderException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReader<Descriptor> implements Reader<Descriptor> {
    @Override
    public List<Descriptor> readFile(String filename) {
        List<Descriptor> descriptors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while (line != null) {
                try {
                    descriptors.add(readLine(line));
                } catch (ReaderException re) {
                    re.printStackTrace();
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return descriptors;
    }
}
