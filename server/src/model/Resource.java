package model;

public class Resource {

    private Resource(){}
    private static Resource instance;
    public static Resource getInstance() {
        if(instance == null)
            instance = new Resource();
        return instance;
    }

    private String resource = "";

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
