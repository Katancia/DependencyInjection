package pl.karol.lingu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.karol.lingu.crypto.FileCipher;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    private String fileName;
    private FileCipher fileCipher;

    @Autowired
    public FileService(@Value("${filename}") String fileName, FileCipher fileCipher) {
        this.fileName = fileName;
        this.fileCipher = fileCipher;
    }

    public List<Entry> readAllFiles() throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(fileCipher::decrypt)
                .map(CsvEntryConverter::parse)
                .collect(Collectors.toList());
    }

    public void saveEntries(List<Entry> entries) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        for (Entry entry : entries) {
            bufferedWriter.write(fileCipher.encrypt(entry.toString()));
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
