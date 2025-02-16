import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Program1 {
    public static void main(String[] args) {
        
        String inputFilePath = "input.txt";
        String cleanedInputFilePath = "inputCleaned.txt";
        System.out.println("Hello, VS Code!");
        String cleanedInputFileData="";
        char delimiter1='^';
        char delimiter2='=';

        try (Scanner scanner = new Scanner(new File(inputFilePath))) {
            while (scanner.hasNextLine()) {
                
                String dataInCurrentLine=scanner.nextLine();
                StringBuilder dataInCleanedLine = new StringBuilder();
                System.out.println(dataInCurrentLine);


                for (int index=0; index<dataInCurrentLine.length(); index++) {
                    if(dataInCurrentLine.charAt(index)==delimiter1)
                    {
                        if(dataInCurrentLine.charAt(index+1)==delimiter1)
                        {
                            dataInCleanedLine.append(delimiter1);
                            dataInCleanedLine.append(delimiter1);
                            index++;
                        }
                    }
                    else if(dataInCurrentLine.charAt(index)==delimiter2)
                    {
                        if(index==2)
                        {
                            if(dataInCurrentLine.substring(index-2, index).equals("id"))
                            {
                                dataInCleanedLine.append(delimiter2);
                            }
                        }
                        else if(index>4)
                        {
                            if(dataInCurrentLine.substring(index-4, index).equals("name") || 
                            dataInCurrentLine.substring(index-4, index).equals("city"))
                            {
                                dataInCleanedLine.append(delimiter2);
                            }
                        }
                    }
                    else{
                        dataInCleanedLine.append(dataInCurrentLine.charAt(index));
                    }
                    
                }
                cleanedInputFileData=cleanedInputFileData+dataInCleanedLine.toString();
                cleanedInputFileData+='\n';

                // int firstIndex = dataInCurrentLine.indexOf(delimiter1);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(cleanedInputFilePath)) { // Create FileWriter
            writer.write(cleanedInputFileData); // Write input to file
            System.out.println("Cleaned data written to " + cleanedInputFilePath + " successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(new File(cleanedInputFilePath))) {
            System.out.println("\noutput:");
            System.out.println("id|name|city");
            while (scanner.hasNextLine()) {
                String dataInCurrentLine = scanner.nextLine();

                int indexOfEquals1=dataInCurrentLine.indexOf(delimiter2);
                int indexOfEquals2=0, indexOfCaret2=0, indexOfEquals3=0, indexOfCaret3=0;
                //print id
                System.out.print(dataInCurrentLine.substring(indexOfEquals1+1, dataInCurrentLine.indexOf(delimiter1)));
                System.out.print("|");

                indexOfEquals2=dataInCurrentLine.indexOf(delimiter2, indexOfEquals1+1);
                indexOfCaret2=dataInCurrentLine.indexOf(delimiter1, indexOfEquals2+1);
                //print name
                System.out.print(dataInCurrentLine.substring(indexOfEquals2+1, indexOfCaret2));
                System.out.print("|");

                indexOfEquals3=dataInCurrentLine.lastIndexOf(delimiter2);
                //print city
                System.out.println(dataInCurrentLine.substring(indexOfEquals3+1, dataInCurrentLine.length()));
                // System.out.println("\n");
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}