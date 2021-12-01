import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SnakeFileWriter {

private File log;
private PrintWriter out;


	SnakeFileWriter() {
		try {
			log = new File("Score.txt");
			out = new PrintWriter(new FileWriter(log, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveScore(int score) throws IOException {
		out.append("Score: " + score + "\n");
		out.close();
	}
}
