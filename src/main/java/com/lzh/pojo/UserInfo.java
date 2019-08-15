package com.lzh.pojo;


/**
 * @author lizhenhao
 * @date 2019/07/12 20:53:32
 */

public class UserInfo {

    private String name;

    private String gender;

    private String date;

    private String studyLevel;

    private String marry;

    private String nationality;

    private String profession;

    private String school;

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", date=" + date +
                ", studyLevel='" + studyLevel + '\'' +
                ", marry='" + marry + '\'' +
                ", nationality='" + nationality + '\'' +
                ", profession='" + profession + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
