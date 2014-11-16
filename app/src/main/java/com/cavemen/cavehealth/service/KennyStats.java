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
            "    \"iconResourceName\": \"ic_active_foosball\",\n" +
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


    @DefaultString("[{\n" +
            "    \"activityId\": 1,\n" +
            "    \"name\": \"Walking\",\n" +
            "    \"description\": \"\",\n" +
            "    \"bigIconResourceName\": \"ic_active_walking_b\",\n" +
            "    \"iconResourceName\": \"ic_active_walking\",\n" +
            "    \"bonusPointsGranted\": 20\n" +
            "}, {\n" +
            "    \"activityId\": 2,\n" +
            "    \"name\": \"Tennis\",\n" +
            "    \"description\": \"\",\n" +
            "    \"bigIconResourceName\": \"ic_active_tennis_b\",\n" +
            "    \"iconResourceName\": \"ic_active_tennis\",\n" +
            "    \"bonusPointsGranted\": 60\n" +
            "}, {\n" +
            "    \"activityId\": 3,\n" +
            "    \"name\": \"Pull ups\",\n" +
            "    \"description\": \"\",\n" +
            "    \"bigIconResourceName\": \"ic_active_pullups_b\",\n" +
            "    \"iconResourceName\": \"ic_active_pullups\",\n" +
            "    \"bonusPointsGranted\": 50\n" +
            "}, {\n" +
            "    \"activityId\": 4,\n" +
            "    \"name\": \"Foos ball\",\n" +
            "    \"description\": \"\",\n" +
            "    \"bigIconResourceName\": \"ic_active_foosball_b\",\n" +
            "    \"iconResourceName\": \"ic_active_foosball\",\n" +
            "    \"bonusPointsGranted\": 40\n" +
            "}, {\n" +
            "    \"activityId\": 5,\n" +
            "    \"name\": \"Eye Break\",\n" +
            "    \"description\": \"\",\n" +
            "    \"bigIconResourceName\": \"ic_active_break_b\",\n" +
            "    \"iconResourceName\": \"ic_active_break\",\n" +
            "    \"bonusPointsGranted\": 10\n" +
            "}, {\n" +
            "    \"activityId\": 6,\n" +
            "    \"name\": \"Water\",\n" +
            "    \"description\": \"\",\n" +
            "    \"bigIconResourceName\": \"ic_active_water_b\",\n" +
            "    \"iconResourceName\": \"ic_active_water\",\n" +
            "    \"bonusPointsGranted\": 10\n" +
            "}]\n")
    String activities();

    @DefaultString("[{\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Walking\",\n" +
            "    \"description\": \"\",\n" +
            "    \"level\":0\n" +
            "}, {\n" +
            "    \"id\": 2,\n" +
            "    \"name\": \"Tennis\",\n" +
            "    \"description\": \"\",\n" +
            "    \"level\":0\n" +
            "}, {\n" +
            "    \"id\": 3,\n" +
            "    \"name\": \"Pull ups\",\n" +
            "    \"description\": \"\",\n" +
            "    \"level\":0\n" +
            "}, {\n" +
            "    \"id\": 4,\n" +
            "    \"name\": \"Foos ball\",\n" +
            "    \"description\": \"GOAAAAAAAAAAAAAAAAAAAAL !\",\n" +
            "    \"level\":1\n" +
            "}, {\n" +
            "    \"id\": 5,\n" +
            "    \"name\": \"Eye Break\",\n" +
            "    \"description\": \"You can see like an owl !\",\n" +
            "    \"level\":2\n" +
            "}, {\n" +
            "    \"id\": 6,\n" +
            "    \"name\": \"Water\",\n" +
            "    \"description\": \"\",\n" +
            "    \"level\":3\n" +
            "}, {\n" +
            "     \"id\": 7,\n" +
            "     \"name\": \"PVP\",\n" +
            "     \"description\": \"Master Of Duels!\",\n" +
            "     \"level\":0,\n" +
            "     \"iconResourceName\":\"ic_achievement_battle\"\n" +
            " }\n" +
            "]\n")
    String achievements();
}
