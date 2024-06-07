package Plugin;

import Plugin.antlr4.CustomListener;
import Plugin.antlr4.GrammarLexer;
import Plugin.antlr4.GrammarParser;
import eapli.jobs4u.app.plugin.Application.ApplicationImporter;
import eapli.jobs4u.app.requirements.domain.Requirement;
import eapli.jobs4u.app.requirements.domain.RequirementType;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.ref.Reference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

public class Main implements ApplicationImporter {
    @Override
    public Set<Requirement> parseRequirementsFile(String inputFilePath) {
        Set<Requirement> setToReturn = new HashSet<>();
        try {
            // Create a lexer that feeds off of input CharStream
            GrammarLexer lexer = new GrammarLexer(CharStreams.fromFileName(inputFilePath));
            // Create a buffer of tokens between the lexer and parser
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // Create a parser that feeds off the tokens buffer
            GrammarParser parser = new GrammarParser(tokens);
            // Begin parsing at the start rule
            ParseTree tree = parser.start();
            // Create a walker for walking the parse tree
            ParseTreeWalker walker = new ParseTreeWalker();
            // Walk the tree with our custom listener
            CustomListener listener = new CustomListener();
            walker.walk(listener, tree);
            setToReturn = listener.requirementSet();
            System.out.println("REQUIREMENTS");
            System.out.println(setToReturn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return setToReturn;
    }

    @Override
    public String buildGeneralTemplateForRequirementsUpload(String outputFolderPath) {
        String outputFilePath;
        try {
            File file = new File(outputFolderPath);
            if (!file.exists() || file.isFile()) {
                throw new IllegalArgumentException("Invalid file path. HINT: Should be a directory.");
            }
            outputFilePath = outputFolderPath.concat(File.separator).concat("ImportRequirementTemplate.txt");
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputFilePath), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            writer.write("@start-reference@ \"Job Reference (beetween \")\" @end-reference@");
            writer.newLine();
            writer.newLine();

            writer.write("@start-requirement@\n" +
                    "    @start-type@ minimum @end-type@\n" +
                    "    @start-requirement-body@ \"Requirement body between quotes\" @end-requirement-body@\n" +
                    "    @start-answer@  Number(s) separated by commas or Words between quotes and seperated commas @end-answer@\n" +
                    "@end-requirement@\n");
            writer.newLine();
            writer.write("@start-requirement@\n" +
                    "    @start-type@ specific @end-type@\n" +
                    "    @start-requirement-body@ \"Requirement body between quotes\" @end-requirement-body@\n" +
                    "    @start-answer@  Number(s) separated by commas or Words between quotes and seperated commas @end-answer@\n" +
                    "@end-requirement@\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputFilePath;
    }

    @Override
    public String buildTemplateForApplication(String outputFolderPath, Set<Requirement> requirementSet) {
        String outputFilePath = "";
        try {
            File file = new File(outputFolderPath);
            if (!file.exists() || file.isFile()) {
                throw new IllegalArgumentException("Invalid file path. HINT: Should be a directory.");
            }
            outputFilePath = outputFolderPath.concat(File.separator).concat("template_for_application_of_"+requirementSet.iterator().next().jobOpeningReference().toString().replaceAll("\"", "")+".txt");

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputFilePath), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write("// --------------------------------------------------------------------------------");
            writer.newLine();
            writer.write("// ONLY WRITE ON THE ANSWER FIElDS.");
            writer.newLine();
            writer.write("// DO NOT CHANGE THE ORDER OR ADD SPACES, TABS OR OTHER CHARACTERS IN THE DOCUMENT");
            writer.newLine();
            writer.write("// --------------------------------------------------------------------------------");
            writer.newLine();
            writer.newLine();
            writer.write("@start-reference@ \"" + requirementSet.iterator().next().jobOpeningReference().toString() +"\" @end-reference@");
            writer.newLine();
            writer.newLine();
            for (Requirement requirement : requirementSet){
                writer.write("@start-requirement@");
                writer.newLine();
                String type = "";
                switch (requirement.requirementType()){
                    case Minimum:
                        type = "minimum";
                        break;
                    case Specific:
                        type = "specific";
                        break;
                }
                writer.write("    @start-type@ " + type + " @end-type@");
                writer.newLine();
                writer.write("    @start-requirement-body@ \"" + requirement.body() + "\" @end-requirement-body@");
                writer.newLine();
                writer.write("    @start-answer@  Number(s) separated by commas or Words between quotes and seperated commas @end-answer@");
                writer.newLine();
                writer.write("@end-requirement@");
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputFilePath;
    }

    @Override
    public boolean checkRequirements(Set<Requirement> requirementSet, Set<Requirement> requirementAnswersSet) {
        for (Requirement requirement : requirementSet) {
            boolean foundMatch = false;
            for (Requirement requirementAnswer : requirementAnswersSet) {
                if (requirementAnswer.body().equals(requirement.body())) {
                    foundMatch = true;
                    if (requirement.requirementType() == RequirementType.Specific) {
                        for (String specific : requirement.specificRequirements()) {
                            if (requirement.specificRequirements().size() != requirementAnswer.specificRequirements().size()){
                                return false;
                            }
                            if (!requirementAnswer.specificRequirements().contains(specific)) {
                                return false;
                            }
                        }
                    } else if (requirement.requirementType() == RequirementType.Minimum) {
                        for (String minimum : requirement.minimumRequirements()) {
                            if (!requirementAnswer.minimumRequirements().contains(minimum)) {
                                return false;
                            }
                        }
                    }
                    break;
                }
            }
            if (!foundMatch) {
                return false;
            }
        }
        return true;
    }

}