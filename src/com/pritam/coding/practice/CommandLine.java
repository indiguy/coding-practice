/**
 *
 */
package com.pritam.coding.practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simulate the sub directory creation in computer system through command line
 *
 * @author pribiswas
 *
 */
public class CommandLine {

	private static enum Command {
		dir, mkdir, cd, up
	}

	/**
	 * Simulation of a computer directory
	 *
	 * @author pribiswas
	 *
	 */
	private static class Directory {

		private final Directory parent;
		private final String name;
		private final Map<String, Directory> subDirectories;

		public Directory(String name, Directory parent) {
			if (name == null || name.trim().length() == 0) {
				throw new IllegalArgumentException("name can not be blank");
			}
			this.parent = parent;
			this.name = name;
			this.subDirectories = new LinkedHashMap<>();
		}

		/**
		 * List sub-directories
		 *
		 * @return a {@link Set} of sub directories
		 */
		public Set<String> listSubDirectories() {
			return subDirectories.keySet();
		}

		/**
		 * Create a sub directory with a given name
		 *
		 * @param name
		 */
		public void createSubDirectory(String name) {
			if (subDirectories.containsKey(name)) {
				throw new IllegalArgumentException("directory with same name exists.");
			}
			subDirectories.put(name, new Directory(name, this));
		}

		/**
		 * Change to given sub directory
		 *
		 * @param name
		 * @return the changed directory, or null if subdirectory doesn't exist
		 */
		public Directory changeDirectory(String name) {
			if (!subDirectories.containsKey(name)) {
				throw new IllegalArgumentException("directory does not exists");
			}
			return subDirectories.get(name);
		}

		/**
		 * Get the absolute path of this directory
		 *
		 * @return the absolute path of this directory
		 */
		public String getAbsolutePath() {
			if (parent == null) {
				return name;
			}

			return new StringBuilder(parent.getAbsolutePath()).append("\\").append(name).toString();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			Directory other = (Directory) obj;
			return this.getAbsolutePath().equals(other.getAbsolutePath());
		}

		@Override
		public int hashCode() {
			int result = 1;
			result = result * 31 + this.getAbsolutePath().hashCode();
			return result;
		}

		@Override
		public String toString() {
			return this.getAbsolutePath();
		}
	}

	private Directory current;

	public CommandLine() {
		this.current = new Directory("root", null);
	}

	/**
	 * Accept the command and return the output
	 *
	 * @param command
	 *            The command
	 * @return The output
	 */
	public String accept(String command) {
		if (command == null || command.trim().isEmpty()) {
			throw new IllegalArgumentException("Please provide a command");
		}
		String[] commandAndOptions = command.split("\\s+");
		Command commandEnum = Command.valueOf(commandAndOptions[0]);
		String[] options = new String[0];
		if (commandAndOptions.length > 1) {
			options = Arrays.copyOfRange(commandAndOptions, 1, commandAndOptions.length);
		}

		return execute(command, commandEnum, options);
	}

	/**
	 * Execute the given command and return output
	 *
	 * @param command
	 * @param commandEnum
	 * @param options
	 * @return
	 */
	private String execute(String command, Command commandEnum, String[] options) {
		StringBuilder outBuilder = new StringBuilder(String.format("Command: %s", command));

		switch (commandEnum) {
		case dir:
			outBuilder.append("\n").append(current.listSubDirectories().stream().collect(Collectors.joining(" ")));
			break;
		case mkdir:
			if (options.length == 0) {
				throw new IllegalArgumentException("Please provide name of the sub sirectory");
			}
			current.createSubDirectory(options[0]);
			break;
		case cd:
			if (options.length == 0) {
				throw new IllegalArgumentException("Please provide name of the sub sirectory");
			}
			this.current = current.changeDirectory(options[0]);
			break;
		case up:
			if (current.parent == null) {
				outBuilder.append("\n").append("Can not move up from root directory");
			}
			this.current = current.parent;
			break;
		}
		return outBuilder.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CommandLine commandLine = new CommandLine();
		try (BufferedReader reader = new BufferedReader(new FileReader("/home/pribiswas/program.dat"));
				BufferedWriter writer = new BufferedWriter(new FileWriter("/home/pribiswas/program.out"))) {
			String command;
			while ((command = reader.readLine()) != null) {
				String output = commandLine.accept(command);
				writer.write(output);
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Program executed successfully.");
	}

}
