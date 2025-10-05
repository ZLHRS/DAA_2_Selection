package cli;
import org.example.cli.BenchmarkRunner;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BenchmarkRunnerTest {

    @Test
    void smokeTestProducesOutputAndCsv() throws Exception {
        final String outFile = "test_metrics_cli.csv";
        final String[] args = {
                "--algorithm=SelectionSort",
                "--n=50",
                "--trials=1",
                "--dist=random",
                "--out=" + outFile
        };

        final PrintStream originalOut = System.out;
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        try {
            BenchmarkRunner.main(args);
        } finally {
            System.setOut(originalOut);
        }

        final String printed = baos.toString();
        assertTrue(printed.contains("n=50"));
        assertTrue(printed.contains("time=") || printed.contains("time"));

        // csv created
        final Path p = Path.of(outFile);
        assertTrue(Files.exists(p));
        Files.deleteIfExists(p);
    }
}
