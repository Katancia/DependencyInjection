package pl.karol.lingu;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    private String fileName = "data.csv";

    public List<Entry> readAllFiles() throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(CsvEntryConverter::parse)
                .collect(Collectors.toList());
    }

    public void saveEntries(List<Entry> entries) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        for (Entry entry : entries) {
            bufferedWriter.write(entry.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    private static class CsvEntryConverter {
        private static Entry parse(String text) {
            String[] split = text.split(";");
            return new Entry(split[0], split[1]);
        }
    }
}
