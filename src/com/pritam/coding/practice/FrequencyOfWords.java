/**
 * 
 */
package com.pritam.coding.practice;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Find the frequency of words in multiple files
 * 
 * @author pribiswas
 *
 */
public class FrequencyOfWords {

	private static final String PATH = "/home/pribiswas/workspace/program_data";

	private final File srcDir;

	/**
	 * @param srcDir
	 */
	public FrequencyOfWords(File srcDir) {
		this.srcDir = srcDir;
	}

	/**
	 * Calculate the frequency of words
	 * 
	 * @return
	 */
	public Map<String, Integer> calculateFrequency() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Future<Map<String, Integer>>> futures = new ArrayList<>();
		File[] files = srcDir.listFiles((FileFilter) file -> !file.isDirectory());
		for (File file : files) {
			FreqOfWordsFromFile task = new FreqOfWordsFromFile(file);
			futures.add(executor.submit(task));
		}
		Map<String, Integer> result = new HashMap<>();
		for (Future<Map<String, Integer>> future : futures) {
			try {
				Map<String, Integer> futureResult = future.get();
				for (Entry<String, Integer> entry : futureResult.entrySet()) {
					Integer count;
					if ((count = result.get(entry.getKey())) != null) {
						count = count + entry.getValue();
						result.put(entry.getKey(), count);
					} else {
						result.put(entry.getKey(), entry.getValue());
					}
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executor.shutdown();
		return result;
	}

	public static void main(String[] args) {
		File srcDir = new File(PATH);
		if (srcDir.exists() && srcDir.isDirectory()) {
			FrequencyOfWords freq = new FrequencyOfWords(srcDir);
			System.out.println(freq.calculateFrequency());
		} else {
			System.out.println("Provide a valid source directory");
			System.exit(1);
		}
	}

	private static class FreqOfWordsFromFile implements Callable<Map<String, Integer>> {

		private final File file;

		/**
		 * @param file
		 */
		public FreqOfWordsFromFile(File file) {
			this.file = file;
		}

		@Override
		public Map<String, Integer> call() throws Exception {
			Map<String, Integer> result = new HashMap<>();
			final List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
			for (String line : lines) {
				reduceLine(line, result);
			}
			return result;
		}

		/**
		 * Reduce the result by counting frequency of words in given line
		 * 
		 * @param line
		 * @param result
		 */
		private void reduceLine(String line, Map<String, Integer> result) {
			final String[] words = line.split("\\s+");
			for (String word : words) {
				Integer count;
				if ((count = result.get(word)) != null) {
					result.put(word, ++count);
				} else {
					result.put(word, 1);
				}
			}
		}

	}

}
