package com.siit.io.athlete;

import com.siit.io.time.SkiTimeResult;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BiathonAthlete implements Comparable<BiathonAthlete> {
    private int AthleteNumber;
    private String AthleteName;
    private String CountryCode;
    private SkiTimeResult skiTimeResult;
    private String FirstShootingRange;
    private String SecondShooting;
    private String ThirdShootingeRane;

    public BiathonAthlete(int athleteNumber, String athleteName, String countryCode, SkiTimeResult skiTimeResult,
                          String firstShootingRange, String secondShooting, String thirdShootingeRane) {
        AthleteNumber = athleteNumber;
        AthleteName = athleteName;
        CountryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        FirstShootingRange = firstShootingRange;
        SecondShooting = secondShooting;
        ThirdShootingeRane = thirdShootingeRane;
    }

    public int getAthleteNumber() {
        return AthleteNumber;
    }

    public void setAthleteNumber(int athleteNumber) {
        AthleteNumber = athleteNumber;
    }

    public String getAthleteName() {
        return AthleteName;
    }

    public void setAthleteName(String athleteName) {
        AthleteName = athleteName;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public SkiTimeResult getSkiTimeResult() {
        return skiTimeResult;
    }

    public void setSkiTimeResult(SkiTimeResult skiTimeResult) {
        this.skiTimeResult = skiTimeResult;
    }

    public String getFirstShootingRange() {
        return FirstShootingRange;
    }

    public void setFirstShootingRange(String firstShootingRange) {
        FirstShootingRange = firstShootingRange;
    }

    public String getSecondShootingRange() {
        return SecondShooting;
    }

    public void setSecondShootingRange(String secondShooting) {
        SecondShooting = secondShooting;
    }

    public String getThirdShootingeRange() {
        return ThirdShootingeRane;
    }

    public void setThirdShootingeRange(String thirdShootingeRane) {
        ThirdShootingeRane = thirdShootingeRane;
    }

    public static List<BiathonAthlete> readCSVFile(String sourceFile) throws FileNotFoundException {

        List<BiathonAthlete> items = new ArrayList<>();

        Scanner fileReader = new Scanner(new FileReader("resources\\biathlonAthlete.csv"));
        fileReader.useDelimiter("\\s");

        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine().trim();
            String[] words = line.split(",");

            int AthleteNumber = Integer.parseInt(words[0]);
            String AthleteName = words[1];
            String CountryCode = words[2];
            SkiTimeResult skiTimeResult = fromStringToSkiTimeResult(words[3]);
            String FirstShootingRange = words[4];
            String SecondShootingRange = words[5];
            String ThirdShootingeRange = words[6];

            items.add(new BiathonAthlete(AthleteNumber, AthleteName, CountryCode, skiTimeResult, FirstShootingRange,
                    SecondShootingRange, ThirdShootingeRange));
        }

        fileReader.close();
        return items;
    }

    public static SkiTimeResult fromStringToSkiTimeResult(String string) {
        String[] stringSplited = string.split(":");
        int firstInt = Integer.parseInt(stringSplited[0]);
        int secondInt = Integer.parseInt(stringSplited[1]);
        return new SkiTimeResult(firstInt, secondInt);
    }

    public int timeInSeconds() {
        return this.getSkiTimeResult().getSeconds() + this.getSkiTimeResult().getMinutes() * 60;
    }

    public static List<BiathonAthlete> finalStandings(List<BiathonAthlete> list) {
        for (BiathonAthlete athlete : list) {
            int initialTimeInSeconds = athlete.timeInSeconds();
            int finalTimeInSeconds = initialTimeInSeconds +
                    athlete.penaltiesAccordintToShootingsRange(athlete.getFirstShootingRange()) +
                    athlete.penaltiesAccordintToShootingsRange(athlete.getSecondShootingRange()) +
                    athlete.penaltiesAccordintToShootingsRange(athlete.getThirdShootingeRange());
            athlete.setSkiTimeResult(new SkiTimeResult(finalTimeInSeconds / 60,
                    finalTimeInSeconds - ((finalTimeInSeconds / 60) * 60)));
        }
        return list;
    }

    public int penaltiesAccordintToShootingsRange(String string) {

        return string.replace("x", "").length() * 10;
    }

    public static BiathonAthlete winner(List<BiathonAthlete> list) {
        Collections.sort(list);
        return list.get(0);
    }

    public static BiathonAthlete runnerUp(List<BiathonAthlete> list) {
        Collections.sort(list);
        return list.get(1);
    }

    public static BiathonAthlete thirdPlace(List<BiathonAthlete> list) {
        Collections.sort(list);
        return list.get(2);
    }

    public static void showList(List<BiathonAthlete> list) {
        for (BiathonAthlete athlete : list) {
            System.out.println(athlete);
        }
    }

    @Override
    public String toString() {
        return "BiathonAthlete{" +
                "AthleteNumber=" + AthleteNumber +
                ", AthleteName='" + AthleteName + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", skiTimeResult=" + skiTimeResult +
                ", FirstShootingRange='" + FirstShootingRange + '\'' +
                ", SecondShooting='" + SecondShooting + '\'' +
                ", ThirdShootingeRane='" + ThirdShootingeRane + '\'' +
                '}';
    }

    @Override
    public int compareTo(BiathonAthlete o) {
        if (this.timeInSeconds() < o.timeInSeconds()) {
            return -1;
        }
        if (this.timeInSeconds() > o.timeInSeconds()) {
            return 1;
        } else {
            return 0;
        }
    }
}