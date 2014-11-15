package com.cavemen.cavehealth.service;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface KennyStats {

    @DefaultInt(1)
    int currentLevel();

    @DefaultInt(10)
    int currentHp();

    @DefaultInt(1)
    int dailyChallengeProgress();

    @DefaultString("[]")
    String myGroupActivities();

    @DefaultString("[{\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Football\",\n" +
            "    \"description\": \"Football description\",\n" +
            "    \"iconResourceName\": \"ic_group_football\",\n" +
            "    \"bonusPointsGranted\": 20\n" +
            "}, {\n" +
            "    \"id\": 2,\n" +
            "    \"name\": \"Gym\",\n" +
            "    \"description\": \"Going to gym\",\n" +
            "    \"iconResourceName\": \"ic_group_gym\",\n" +
            "    \"bonusPointsGranted\": 20\n" +
            "}, {\n" +
            "    \"id\": 3,\n" +
            "    \"name\": \"Basketball\",\n" +
            "    \"description\": \"\",\n" +
            "    \"iconResourceName\": \"ic_group_basketball\",\n" +
            "    \"bonusPointsGranted\": 20\n" +
            "}, {\n" +
            "    \"id\": 4,\n" +
            "    \"name\": \"Swimming\",\n" +
            "    \"description\": \"\",\n" +
            "    \"iconResourceName\": \"ic_group_swimming\",\n" +
            "    \"bonusPointsGranted\": 20\n" +
            "}, {\n" +
            "    \"id\": 5,\n" +
            "    \"name\": \"Yoga\",\n" +
            "    \"description\": \"Yoga description\",\n" +
            "    \"iconResourceName\": \"ic_group_yoga\",\n" +
            "    \"bonusPointsGranted\": 20\n" +
            "}]")
    String groupActivities();

}