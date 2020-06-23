package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

    /**
     * Top level search workflow
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * Traverse a given directory and return all the files
     * @param rootDir input directory
     * @return files under the directory.
     */
    List<File> listFiles(String rootDir);

    /**
     * Read the file and return all the liens
     * @param inputFile file to ve read
     * @return lines
     * @throws IllegalArgumentException if a given file is not a files.
     */
    List<String> readLines(File inputFile) throws IllegalArgumentException, IOException;

    /**
     * check if a line contains regex pattern
     * @param line input string
     * @return true if there is a match.
     */
    boolean containsPattern(String line);

    /**
     * writes the lines to file.
     * @param lines matched lines.
     * @throws IOException if write failed.
     */
    void writeToFile (List<String> lines) throws IOException;

    public String getRegex();

    public String getRootPath();

    public String getOutFile();

    public void setRegex(String regex);

    public void setRootPath(String rootPath);

    public void setOutFile(String outFile);

}
