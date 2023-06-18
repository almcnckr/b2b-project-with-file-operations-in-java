package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class FileLogger extends Logger {

    @Override
    public void log(String message) {
        processDate = LocalDateTime.now();
            BufferedWriter bWriter;
			try {
				bWriter = new BufferedWriter(new FileWriter("logs.txt", true));
				bWriter.write(message + " " + processDate);
				bWriter.newLine();
				bWriter.close();
			} catch (Exception e) {
			}
    }
}