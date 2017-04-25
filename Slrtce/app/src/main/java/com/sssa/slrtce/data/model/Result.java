package com.sssa.slrtce.data.model;

/**
 * Created by Coolalien on 2/26/2017.
 */


public class Result {

    private String id;
    private String syllabus;
    private String workingdata;
    private String checked;
    private boolean ischecked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getWorkingdata() {
        return workingdata;
    }

    public void setWorkingdata(String workingdata) {
        this.workingdata = workingdata;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public boolean ischecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }
}
