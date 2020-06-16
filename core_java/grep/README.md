# Introduction
This project is mainly about simulating the grep command functionality using Java. It takes three arguments as the parameters, root directory path, regular expression to be matched, and the output file path. It recursively walks through the root directory then finds the files in which contents contain the given regular expression then put those lines to the output file. Its first version is using standard Java IO operation to perform the functionality. The second version is using the lambda function and stream API from Java to optimize the performance.


# Usage
It takes three arguments.
1. rootDirectory: the root directory path to do the recursive operations.
2. regex pattern: the regular expression to be matched in files.
3. output file path: the result will be redirected to this file.
```
Example: Three arguments 1. ./Desktop 2. ".*public.*"  3. ./home/a.txt

Desktop
├── dir_one
│   ├── dir_two
│   │   └── HelloWorld_three.java
│   └── HelloWorld_two.java
├── HelloWorld_one.java
└── RegexExc.java

Output:
    public static void main(String args[]) {
public interface RegexExc {
    public boolean matchJpeg(String filename);
    public boolean matchI(String filename);
    public boolean isEmptyLine(String line);
    public static void main(String args[]) {
    public static void main(String args[]) {
	public testmessgae
```

# Pseudocode
```
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```

# Performance Issue
If we are dealing with the large files, our program will not have a bad performance, and we may encounter the out of memory problem. This is because we keep the files all in the memory since we store them in the list.But when we choose to use the stream along with lambda, we can avoid storing all the lines in the memory.

# Improvement
1. Since we have two versions for the application, we can set a pre-option for use. If users are dealing the large files, choose the stream and lambda version. Otherwise, they can use the standard Java I/O version for the files.
2. We can modify the interface to allow some of the methods to return the stream rather than a list to improve the performance furthermore.
3. Adding more flag options for user to achieve the furthermore grep functionalities.