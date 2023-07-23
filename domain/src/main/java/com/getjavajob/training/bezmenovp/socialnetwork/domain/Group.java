package com.getjavajob.training.bezmenovp.socialnetwork.domain;

import java.util.Objects;

public class Group {

    private int groupID;
    private String groupName;
    private String groupDescription;

    public Group() {
    }

    public Group(int groupID, String groupName, String groupDescription) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return getGroupID() == group.getGroupID() &&
                Objects.equals(getGroupName(), group.getGroupName()) &&
                Objects.equals(getGroupDescription(), group.getGroupDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupID(), getGroupName(), getGroupDescription());
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupID=" + groupID +
                ", groupName='" + groupName + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
                '}';
    }

}