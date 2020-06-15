package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JavaGrepImp implements JavaGrep {
    private String regex;
    private String rootPath;
    private String outFile;
    /**
     * Top level search workflow
     *
     * @throws IOException
     */
    @Override
    public void process() throws IOException {

    }

    /**
     * Traverse a given directory and return all the files
     *
     * @param rootDir input directory
     * @return files under the directory.
     */
    @Override
    public List<File> istFiles(String rootDir) {
        return null;
    }

    /**
     * Read the file and return all the liens
     *
     * @param inputFile file to ve read
     * @return lines
     * @throws IllegalArgumentException if a given file is not a files.
     */
    @Override
    public List<String> readLines(File inputFile) throws IllegalArgumentException {
        return null;
    }

    /**
     * check if a line contains regex pattern
     *
     * @param line input string
     * @return true if there is a match.
     */
    @Override
    public boolean containsPattern(String line) {
        return false;
    }

    /**
     * writes the lines to file.
     *
     * @param lines matched lines.
     * @throws IOException if write failed.
     */
    @Override
    public void writeToFile(List<String> lines) throws IOException {

    }

    public String getRegex() {
        return regex;
    }

    public String getRootPath() {
        return rootPath;
    }

    public String getOutFile() {
        return outFile;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}
