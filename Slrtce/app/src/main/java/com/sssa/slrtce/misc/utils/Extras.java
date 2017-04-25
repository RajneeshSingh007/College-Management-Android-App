package com.sssa.slrtce.misc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static com.sssa.slrtce.misc.utils.Constants.ATDATA;
import static com.sssa.slrtce.misc.utils.Constants.BRANCHCMPN;
import static com.sssa.slrtce.misc.utils.Constants.COMMONYR;
import static com.sssa.slrtce.misc.utils.Constants.COUNTER;
import static com.sssa.slrtce.misc.utils.Constants.ENDDATE;
import static com.sssa.slrtce.misc.utils.Constants.FOURTHYR;
import static com.sssa.slrtce.misc.utils.Constants.INOUROUT;
import static com.sssa.slrtce.misc.utils.Constants.NOTICETRACK;
import static com.sssa.slrtce.misc.utils.Constants.NTEACHER_LOGIN;
import static com.sssa.slrtce.misc.utils.Constants.NTEACHER_LOGIN_TRACK;
import static com.sssa.slrtce.misc.utils.Constants.ONETIMESCREEN;
import static com.sssa.slrtce.misc.utils.Constants.STARTDATE;
import static com.sssa.slrtce.misc.utils.Constants.STUDENTINIT;
import static com.sssa.slrtce.misc.utils.Constants.STUDENT_LOGIN;
import static com.sssa.slrtce.misc.utils.Constants.STUDENT_LOGIN_TRACK;
import static com.sssa.slrtce.misc.utils.Constants.SUBJECTNAME;
import static com.sssa.slrtce.misc.utils.Constants.SYLLABUS;
import static com.sssa.slrtce.misc.utils.Constants.TEACHER_LOGIN;
import static com.sssa.slrtce.misc.utils.Constants.TEACHER_LOGIN_TRACK;
import static com.sssa.slrtce.misc.utils.Constants.USERNAME;

/**
 * Created by Coolalien on 2/20/2017.
 */

public class Extras {

    public Context context;
    private SharedPreferences sharedPreferences;

    private SharedPreferences userSession;
    private SharedPreferences.Editor userSessionEdit;

    /**
     * Constructor
     * @param context
     */
    public Extras (Context context){
        this.context = context;
        userSession = context.getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        userSessionEdit = userSession.edit();
    }


    //////////////////// LOGIN SESSION //////////////////////

    /**
     * Enable userlogin Session
     * @param username
     */
    public void setuserLogginSession(String username, Boolean torf) {
        userSessionEdit.putBoolean(INOUROUT, torf);
        userSessionEdit.putString(USERNAME, username);
        userSessionEdit.commit();
    }

    /**
     * Is User Logged or not
     * @return
     */
    public boolean isLogged(){
        return userSession.getBoolean(INOUROUT, false);
    }


    /**
     * Username
     * @return
     */
    public String getUserName(){
        return userSession.getString(USERNAME, null);
    }

    public SharedPreferences.Editor getUserSessionEdit() {
        return userSessionEdit;
    }

    /////////////////////// MULTI LOGIN SYSTEM MANAGEMENT /////////////////////

    /**
     * Set Student Values
     * @param student
     */
    public void setStudent(String student){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STUDENT_LOGIN, student);
        editor.apply();
    }

    /**
     * Set Teacher Values
     * @param teacher
     */
    public void setTeacher(String teacher){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEACHER_LOGIN, teacher);
        editor.apply();
    }

    /**
     * Set NTeacher Values
     * @param nTeacher
     */
    public void setNTeacher(String nTeacher){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NTEACHER_LOGIN, nTeacher);
        editor.apply();
    }
    /**
     * Student
     * @return
     */
    public String isStudent(){
        return sharedPreferences.getString(STUDENT_LOGIN, null);
    }

    /**
     * Teacher
     * @return
     */
    public String isTeacher(){
        return sharedPreferences.getString(TEACHER_LOGIN, null);
    }

    /**
     * NTeacher
     * @return
     */
    public String isNTeacher(){
        return sharedPreferences.getString(NTEACHER_LOGIN, null);
    }


    ///////////////////////////////// TRACKING PREF //////////////////////

    /**
     * Set Student Values
     * @param studentTrack
     */
    public void setStudentTrack(boolean studentTrack){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(STUDENT_LOGIN_TRACK, studentTrack);
        editor.apply();
    }

    /**
     * Set Teacher Values
     * @param teacherTrack
     */
    public void setTeacherTrack(boolean teacherTrack){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(TEACHER_LOGIN_TRACK, teacherTrack);
        editor.apply();
    }

    /**
     * Set NTeacher Values
     * @param nTeacherTrack
     */
    public void setNTeacherTrack(boolean nTeacherTrack){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(NTEACHER_LOGIN_TRACK, nTeacherTrack);
        editor.apply();
    }
    /**
     * Student Track
     * @return
     */
    public boolean studentTrack(){
        return sharedPreferences.getBoolean(STUDENT_LOGIN_TRACK, true);
    }

    /**
     * Teacher Track
     * @return
     */
    public boolean teacherTrack(){
        return sharedPreferences.getBoolean(TEACHER_LOGIN_TRACK, true);
    }

    /**
     * NTeacher Track
     * @return
     */
    public boolean nteacherTrack(){
        return sharedPreferences.getBoolean(NTEACHER_LOGIN_TRACK, true);
    }


    ////////////////////////////////// STUDENT INIT PREF //////////////

    /**
     * Save initStudent pref
     * @param studentyrbranch
     */
    public void saveinitStudent(String studentyrbranch){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STUDENTINIT, studentyrbranch);
        editor.apply();
    }

    /**
     * init student
     * @return
     */
    public String getinitStudent(){
        return sharedPreferences.getString(STUDENTINIT, null);
    }


    ////////////////////////// OTHER STUFF /////////////////

    /**
     * put data
     * @param count
     */
    public void putinitScreen(int count){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ONETIMESCREEN, count);
        editor.apply();
    }

    /**
     * Init Screen
     * @return
     */
    public int getfirstScreen(){
        return sharedPreferences.getInt(ONETIMESCREEN, 0);
    }



    /////////////////////// SyallbusTracker ///////////////////////

    /**
     * put cmpn branch
     * @param branchcmpn
     */
    public void branchCmpn(String branchcmpn){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BRANCHCMPN, branchcmpn);
        editor.apply();
    }


    /**
     * get cmpn Branch
     * @return
     */
    public String getBranchCmpn(){
        return sharedPreferences.getString(BRANCHCMPN, null);
    }

    /**
     * Put yr
     * @param year
     */
    public void setyr(String year){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(COMMONYR, year);
        editor.apply();
    }

    /**
     * Get common yr
     * @return
     */
    public String getYr(){
        return sharedPreferences.getString(COMMONYR, null);
    }


    /////////////////////////// Tracker as per year /////////////////////////////////


    /**
     * Set fourth year
     * @param trackyr
     */
    public void setfourthYear(boolean trackyr){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FOURTHYR, trackyr);
        editor.apply();
    }

    /**
     * Track about fourth year
     * @return
     */
    public boolean getfourthYear(){
        return sharedPreferences.getBoolean(FOURTHYR, false);
    }


    ////////////////////////// Attendance tracking detail ///////////////////////

    /**
     * Set subject name
     * @param subjectName
     */
    public void setSubjectName(String subjectName){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SUBJECTNAME, subjectName);
        editor.apply();
    }


    /**
     * Return subjectname
     * @return
     */
    public String getsubjectName(){
        return sharedPreferences.getString(SUBJECTNAME, null);
    }


    /////////////////////// Attendance date between save ///////////////

    /**
     * Set start date
     * @param startDate
     */
    public void setStartDate(String startDate){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STARTDATE, startDate);
        editor.apply();
    }

    /**
     * Return start date
     * @return
     */
    public String getStartDate(){
        return sharedPreferences.getString(STARTDATE, null);
    }


    /**
     * Set end date
     * @param endDate
     */
    public void setEndDate(String endDate){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ENDDATE, endDate);
        editor.apply();
    }

    /**
     * Return end date
     * @return
     */
    public String getEndDate(){
        return sharedPreferences.getString(ENDDATE, null);
    }


    /////////////////////// Attendance Work ////////////////////////

    /**
     * Save checked Attendance data
     * @param list
     */
    public void saveatData(List<Integer> list) {
        String s = "";
        for (Integer i : list) {
            s += i + ",";
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ATDATA, s);
        editor.commit();
    }

    /**
     * Return checked Attendance data
     * @return
     */
    public ArrayList<Integer> getatData() {
        String s = sharedPreferences.getString(ATDATA, "");
        StringTokenizer st = new StringTokenizer(s, ",");
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (st.hasMoreTokens()) {
            result.add(Integer.parseInt(st.nextToken()));
        }
        return result;
    }


    /////////////// Counter record ///////////////

    /**
     * Save onclick int value for attendance
     * @param value
     */
    public void saveCounter(int value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COUNTER, value);
        editor.apply();
    }

    /**
     * Return counter value
     * @return
     */
    public int getCounter(){
        return sharedPreferences.getInt(COUNTER, 0);
    }


    /**
     * Return sharedpref
     * @return
     */
    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    /////////////////////// Syllabus Work ////////////////////////

    /**
     * Save checked Syllabus data
     * @param list
     */
    public void saveSyllabusData(List<String> list) {
        String s = "";
        for (String i : list) {
            s += i + ",";
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SYLLABUS, s);
        editor.apply();
    }

    /**
     * Return checked Syllabus data
     * @return
     */
    public ArrayList<String> getSyllabusData() {
        String s = sharedPreferences.getString(SYLLABUS, "");
        StringTokenizer st = new StringTokenizer(s, ",");
        ArrayList<String> result = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }
        return result;
    }


    ///////////////// Snotice upload work //////////////////////

    public void setnoticeTrack(Boolean torf){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(NOTICETRACK, torf);
        editor.apply();
    }

    public boolean getNoticeTrack(){
        return sharedPreferences.getBoolean(NOTICETRACK, false);
    }

}

