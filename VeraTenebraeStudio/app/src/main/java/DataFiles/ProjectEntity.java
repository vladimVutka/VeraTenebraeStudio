package DataFiles;

public class ProjectEntity {
    private final String projectName;
    private final String projectId;
    private final String projectDiscription;

    public ProjectEntity(String projectId, String projectName, String projectDiscription){
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDiscription = projectDiscription;
    }

    public String _projectName(){
        return projectName;
    }
    public String _projectDescription(){
        return projectDiscription;
    }
    public String _projectId(){
        return projectId;
    }

}
