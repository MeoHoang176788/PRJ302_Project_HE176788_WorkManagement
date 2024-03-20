/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author LAPTOP 247
 */
public class Work {
    private String workid;
    private String worktitle;
    private String workrequest;
    private String worksubmit;
    private Date workstartdate;
    private String workstatus;
    private String uid;

    public Work() {
    }

    public Work(String workid, String worktitle, String workrequest, String worksubmit, Date workstartdate, String workstatus) {
        this.workid = workid;
        this.worktitle = worktitle;
        this.workrequest = workrequest;
        this.worksubmit = worksubmit;
        this.workstartdate = workstartdate;
        this.workstatus = workstatus;
    }

    public Work(String workid, String worktitle, String workrequest, String worksubmit, Date workstartdate, String workstatus, String uid) {
        this.workid = workid;
        this.worktitle = worktitle;
        this.workrequest = workrequest;
        this.worksubmit = worksubmit;
        this.workstartdate = workstartdate;
        this.workstatus = workstatus;
        this.uid = uid;
    }
    
    

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }

    public String getWorktitle() {
        return worktitle;
    }

    public void setWorktitle(String worktitle) {
        this.worktitle = worktitle;
    }

    public String getWorkrequest() {
        return workrequest;
    }

    public void setWorkrequest(String workrequest) {
        this.workrequest = workrequest;
    }

    public String getWorksubmit() {
        return worksubmit;
    }

    public void setWorksubmit(String worksubmit) {
        this.worksubmit = worksubmit;
    }

    public Date getWorkstartdate() {
        return workstartdate;
    }

    public void setWorkstartdate(Date workstartdate) {
        this.workstartdate = workstartdate;
    }

    public String getWorkstatus() {
        return workstatus;
    }

    public void setWorkstatus(String workstatus) {
        this.workstatus = workstatus;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    @Override
    public String toString() {
        return "Work{" + "workid=" + workid + ", worktitle=" + worktitle + ", workrequest=" + workrequest + ", worksubmit=" + worksubmit + ", workstartdate=" + workstartdate + ", workstatus=" + workstatus + '}';
    }

    
    
    

   
    
}
