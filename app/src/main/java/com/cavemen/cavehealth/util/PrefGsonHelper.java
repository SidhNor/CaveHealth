package com.cavemen.cavehealth.util;

import com.cavemen.cavehealth.model.Achievement;
import com.cavemen.cavehealth.model.Activity;
import com.cavemen.cavehealth.model.GroupActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PrefGsonHelper {

    public static List<GroupActivity> getListOfGroupActivities(String list) {
        Type listOfActivities = new TypeToken<List<GroupActivity>>() {
        }.getType();
        return new Gson().fromJson(list, listOfActivities);
    }

    public static List<Achievement> getListOfGroupAchievemnts(String list) {
        Type listOfActivities = new TypeToken<List<Achievement>>() {
        }.getType();
        return new Gson().fromJson(list, listOfActivities);
    }

    public static List<Activity> getListOfActivities(String list) {
        Type listOfActivities = new TypeToken<List<Activity>>() {
        }.getType();
        return new Gson().fromJson(list, listOfActivities);
    }

    public static String saveListOfGroupActivities(List<GroupActivity> list) {
        return new Gson().toJson(list);
    }

    public static List<Integer> getListOfInts(String list) {
        Type listOfInts = new TypeToken<List<Integer>>() {
        }.getType();
        return new Gson().fromJson(list, listOfInts);
    }

    public static String saveListOfInts(List<Integer> list) {
        return new Gson().toJson(list);
    }
}
