package tri_fusion;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		int[] table = new Random().ints(100000000, 1, 2000).toArray();
		LocalDateTime start = LocalDateTime.now();
		Sorter sorter = new Sorter(table, 20);
		sorter.start();
		try {
			sorter.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		System.out.println("\n" + ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
	}
	
}
