import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiThreadedFileDownloader {

    private static final int THREAD_COUNT = 4;

    public static void main(String[] args) {
        String fileURL = "https://www.sample-videos.com/download-sample-csv.php"; // Replace with a valid file URL
        String saveFile = "downloaded_file.zip";

        try {
            // Step 1: Get file size
            HttpURLConnection conn = (HttpURLConnection) new URL(fileURL).openConnection();
            conn.setInstanceFollowRedirects(true); // Follow redirects
            conn.connect();

            // Debugging - Print response headers
            System.out.println("Response Code: " + conn.getResponseCode());
            for (String header : conn.getHeaderFields().keySet()) {
                System.out.println(header + ": " + conn.getHeaderField(header));
            }

            int fileSize = conn.getContentLength();
            if (fileSize <= 0) {
                System.out.println("Error: Unable to determine file size.");
                return;
            }

            System.out.println("File size: " + fileSize + " bytes");

            // Step 2: Pre-allocate the file
            try (RandomAccessFile outputFile = new RandomAccessFile(saveFile, "rw")) {
                outputFile.setLength(fileSize);
            }

            // Step 3: Start threads to download the file in parts
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
            int partSize = fileSize / THREAD_COUNT;

            for (int i = 0; i < THREAD_COUNT; i++) {
                int start = i * partSize;
                int end = (i == THREAD_COUNT - 1) ? fileSize - 1 : (start + partSize - 1);
                executor.execute(new DownloadThread(fileURL, saveFile, start, end, i));
            }

            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            System.out.println("Download completed successfully.");

        } catch (Exception e) {
            System.err.println("Download failed: " + e.getMessage());
        }
    }

    static class DownloadThread implements Runnable {
        private final String fileURL;
        private final String saveFile;
        private final int start;
        private final int end;
        private final int id;

        public DownloadThread(String fileURL, String saveFile, int start, int end, int id) {
            this.fileURL = fileURL;
            this.saveFile = saveFile;
            this.start = start;
            this.end = end;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                // Open a connection to the file URL and set the range of bytes to download
                HttpURLConnection conn = (HttpURLConnection) new URL(fileURL).openConnection();
                conn.setRequestProperty("Range", "bytes=" + start + "-" + end);
                conn.setInstanceFollowRedirects(true); // Follow redirects
                conn.connect();

                try (InputStream input = conn.getInputStream();
                     RandomAccessFile outputFile = new RandomAccessFile(saveFile, "rw")) {
                    outputFile.seek(start);  // Set the position to write in the file
                    byte[] buffer = new byte[8192];
                    int bytesRead;

                    System.out.printf("Thread %d downloading bytes %d - %d\n", id, start, end);
                    while ((bytesRead = input.read(buffer)) != -1) {
                        outputFile.write(buffer, 0, bytesRead);
                    }
                    System.out.printf("Thread %d finished.\n", id);
                }
            } catch (IOException e) {
                System.err.printf("Thread %d failed: %s\n", id, e.getMessage());
            }
        }
    }
}
