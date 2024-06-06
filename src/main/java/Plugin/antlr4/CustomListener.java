package Plugin.antlr4;

import eapli.jobs4u.app.jobopening.domain.JobOpeningReference;
import eapli.jobs4u.app.requirements.domain.Requirement;
import eapli.jobs4u.app.requirements.domain.RequirementBuilder;
import eapli.jobs4u.app.requirements.domain.RequirementType;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomListener extends GrammarBaseListener {
    private JobOpeningReference jobOpeningReference;
    private String body;
    private RequirementType requirementType;
    private List<String> requirements;

    private Set<Requirement> requirementSet;

    private void addRequirementByBuilder() {
        RequirementBuilder requirementBuilder = new RequirementBuilder();
        requirementBuilder.withBody(body);
        requirementBuilder.withRequirementType(requirementType);
        requirementBuilder.withJobOpeningReference(jobOpeningReference);
        requirementBuilder.withRequirements(requirements);
        Requirement r = requirementBuilder.build();
        requirementSet.add(requirementBuilder.build());
    }


    @Override
    public void enterStart(GrammarParser.StartContext ctx) {
        this.requirementSet = new HashSet<>();
    }

    //@Override public void exitStart(GrammarParser.StartContext ctx) { }

    @Override
    public void enterJobOpeningReference(GrammarParser.JobOpeningReferenceContext ctx) {
        this.jobOpeningReference = new JobOpeningReference(ctx.STRING().getText());
    }

    @Override
    public void enterBody(GrammarParser.BodyContext ctx) {
        this.body = ctx.STRING().getText().replaceAll("\"", "");
    }
    @Override
    public void enterRequirement(GrammarParser.RequirementContext ctx) {
        this.requirements = new ArrayList<>();
    }

    @Override
    public void exitRequirement(GrammarParser.RequirementContext ctx) {
        addRequirementByBuilder();
    }

    @Override
    public void enterMinimumRequirement(GrammarParser.MinimumRequirementContext ctx) {
        this.requirementType = RequirementType.Minimum;
    }

    @Override
    public void enterRequirementAnswer(GrammarParser.RequirementAnswerContext ctx) {
        for (TerminalNode terminalNode : ctx.STRING()) {
            if (!terminalNode.getText().isBlank() && !terminalNode.getText().isEmpty()) {
                requirements.add(terminalNode.getText().replaceAll("\"", ""));
            }
        }

        for (TerminalNode terminalNode : ctx.NUMBER()) {
            if (!terminalNode.getText().isBlank() && !terminalNode.getText().isEmpty()) {
                requirements.add(terminalNode.getText().replaceAll("\"", ""));
            }
        }
    }

    @Override
    public void enterSpecificRequirement(GrammarParser.SpecificRequirementContext ctx) {
        this.requirementType = RequirementType.Specific;
    }

    public Set<Requirement> requirementSet() {
        return this.requirementSet;
    }
}
