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
        System.out.println("General template: " + main.buildGeneralTemplateForRequirementsUpload("src/main/java/Plugin"));
        System.out.println("Application template: " + main.buildTemplateForApplication("src/main/java/Plugin", set));
    }
}
