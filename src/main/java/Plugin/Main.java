package Plugin;

import Plugin.antlr4.CustomListener;
import Plugin.antlr4.GrammarLexer;
import Plugin.antlr4.GrammarParser;
import eapli.jobs4u.app.plugin.Application.ApplicationImporter;
import eapli.jobs4u.app.requirements.domain.Requirement;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
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
    public String buildGeneralTemplateForRequirementsUpload(String s) {
        return "";
    }

    @Override
    public String buildTemplateForApplication(String s, Set<Requirement> set) {
        return "";
    }

    @Override
    public boolean checkRequirements(Set<Requirement> set, Set<Requirement> set1) {
        return false;
    }
}