package com.createvision.sivilima.tableModel;

public enum JobStatus {
    CREATE("CREATE",1),
    START("START",2),
    FINISHED("FINISHED",3),
    PENDING("PENDING",4),
    CANCELED("CANCELED",5);

    String name;
    int id;
    JobStatus(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;
    }

    public static JobStatus getJobType(int id) {
        for (JobStatus e : values()) {
            System.out.printf(" Come loop== "+e.getId());
            if(e.id == id) {
                return e;
            }

        }
        return null;
    }
}
