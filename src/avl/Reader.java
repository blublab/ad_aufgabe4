package avl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	final static String delimiter = " ";

	private Reader() {
	};

	public static int[] getSequence(String path) throws IOException {
		List<Integer> sequence = new ArrayList<Integer>();
		String temp = new String();

		InputStreamReader isr = new InputStreamReader(
				new FileInputStream(path), "UTF-8");
		BufferedReader input = new BufferedReader(isr);

		temp = input.readLine();
		for (String s : temp.split(delimiter)) {
			sequence.add(Integer.parseInt(s));
		}

		input.close();
		int[] erg = new int[sequence.size()];
		for (int i = 0; i < sequence.size(); i++) {
			erg[i] = sequence.get(i);
		}
		return erg;
	}

}