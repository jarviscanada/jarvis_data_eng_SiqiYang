package ca.jrvs.apps.grep;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JavaGrepImp implements JavaGrep {
    private String regex;
    private String rootPath;
    private String outFile;
    final Logger logger = LoggerFactory.getLogger(JavaGrepImp.class);
    /**
     * Top level search workflow
     *
     * @throws IOException
     */
    @Override
    public void process() throws IOException {
        List<File>files = listFiles(this.rootPath);
        List<String> matchedLines = new ArrayList<String>();
        for (File i : files) {
            for(String j : readLines(i)) {
                if (containsPattern(j)) {
                    matchedLines.add(j);
                }
            }
        }
        writeToFile(matchedLines);
    }

    /**
     * Traverse a given directory and return all the files
     *
     * @param rootDir input directory
     * @return files under the directory.
     */
    @Override
    public List<File> listFiles(String rootDir) {
        File files = new File(rootDir);
        List<File> filesList = Arrays.asList(files.listFiles());
        return filesList;
    }

    /**
     * Read the file and return all the liens
     *
     * @param inputFile file to ve read
     * @return lines
     * @throws IllegalArgumentException if a given file is not a files.
     */
    @Override
    public List<String> readLines(File inputFile) throws IllegalArgumentException, IOException {
        List<String> lines = new ArrayList<String>();
        try {
            FileReader reader = new FileReader(inputFile);
            BufferedReader bfReader = new BufferedReader(reader);
            String line = bfReader.readLine();
            while(line!= null){
                lines.add(line);
                line = bfReader.readLine();
            }
            bfReader.close();
            reader.close();
        }catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("the given file is not a file",e);
        }catch (IOException e) {
            throw new IOException("could not open or read a file",e);
        }
        return lines;
    }

    /**
     * check if a line contains regex pattern
     *
     * @param line input string
     * @return true if there is a match.
     */
    @Override
    public boolean containsPattern(String line) {
        return line.matches(this.regex);
    }

    /**
     * writes the lines to file.
     *
     * @param lines matched lines.
     * @throws IOException if write failed.
     */
    @Override
    public void writeToFile(List<String> lines) throws IOException {
        try{
            File desFile = new File(this.outFile);
            FileWriter desFileWriter = new FileWriter(desFile);
            BufferedWriter bfDesWriter = new BufferedWriter(desFileWriter);
            for(String i : lines) {
                bfDesWriter.write(i);
                bfDesWriter.newLine();
            }
            bfDesWriter.close();
            desFileWriter.close();
        }catch (IOException e) {
            throw new IOException("could not write to file", e);
        }


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

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE JavaGrep regex rootPath outFile");
        }
        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        }catch (Exception ex) {

        }
    }
}
