package Plugin;

import eapli.jobs4u.app.requirements.domain.Requirement;

import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Main main = new Main();
        Set<Requirement> set = main.parseRequirementsFile("src/main/java/Plugin/requirements1.txt");
        for (Requirement requirement : set){
            System.out.println(requirement);
        }
        Set<Requirement> set2 = main.parseRequirementsFile("src/main/java/Plugin/requirements3.txt");
        System.out.println("General template: " + main.buildGeneralTemplateForRequirementsUpload("src/main/java/Plugin"));
        System.out.println("Application template: " + main.buildTemplateForApplication("src/main/java/Plugin", set));
        System.out.println(main.checkRequirements(set, set2));
    }
}
