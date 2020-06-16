package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp{
    /**
     * Traverse a given directory and return all the files
     *
     * @param rootDir input directory
     * @return files under the directory.
     */
    @Override
    public List<File> listFiles(String rootDir) {
        List<File> files = new ArrayList<>();
       try {
            Stream<Path> path = Files.walk(Paths.get(rootDir));
            path.filter(Files :: isRegularFile).map(file -> file.toFile()).forEach(member -> files.add(member));
       } catch (IOException e) {
           e.printStackTrace();
       }
       return files;
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
        List<String> finalList = new ArrayList<>();
        try{
            Stream<String> lines = Files.lines(Paths.get(inputFile.getAbsolutePath()));
            lines.forEach(element -> finalList.add(element));
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Illegal argument passed",e);
        }catch (IOException ex) {
            throw new IOException("can not open files", ex);
        }
        return finalList;
    }


    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE JavaGrep regex rootPath outFile");
        }
        JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
        javaGrepLambdaImp.setRegex(args[0]);
        javaGrepLambdaImp.setRootPath(args[1]);
        javaGrepLambdaImp.setOutFile(args[2]);

        try {
            javaGrepLambdaImp.process();
        }catch (Exception ex) {
            javaGrepLambdaImp.logger.error("Usage JavaGrep regex rootPath outFile", ex);
        }
    }

}
