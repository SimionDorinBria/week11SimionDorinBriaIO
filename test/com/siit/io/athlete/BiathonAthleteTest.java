package com.siit.io.athlete;

import com.siit.io.time.SkiTimeResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.siit.io.athlete.BiathonAthlete.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BiathonAthleteTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        folder.delete();
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testAthleteNumberImport() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter WriteFileBuffer = new BufferedWriter(new FileWriter(createdFile));

        WriteFileBuffer.write("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.close();

        assertThat((readCSVFile("test.txt")).get(0).getAthleteNumber(), is(11));
    }

    @Test
    public void testAthleteNameImport() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter WriteFileBuffer = new BufferedWriter(new FileWriter(createdFile));

        WriteFileBuffer.write("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.close();

        assertThat((readCSVFile("test.txt")).get(0).getAthleteName(), is("Umar Jorgson"));
    }

    @Test
    public void testCountryCodeImport() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter WriteFileBuffer = new BufferedWriter(new FileWriter(createdFile));

        WriteFileBuffer.write("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.close();

        assertThat((readCSVFile("test.txt")).get(0).getCountryCode(), is("SK"));
    }

    @Test
    public void testSkiTimeResultMinutesImport() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter WriteFileBuffer = new BufferedWriter(new FileWriter(createdFile));

        WriteFileBuffer.write("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.close();

        assertThat(readCSVFile("test.txt").get(0).getSkiTimeResult().getMinutes(), is(30));
    }

    @Test
    public void testSkiTimeResultSecondsImport() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter WriteFileBuffer = new BufferedWriter(new FileWriter(createdFile));

        WriteFileBuffer.write("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.close();

        assertThat(readCSVFile("test.txt").get(0).getSkiTimeResult().getSeconds(), is(27));
    }

    @Test
    public void testFirstShootingRangeImport() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter WriteFileBuffer = new BufferedWriter(new FileWriter(createdFile));

        WriteFileBuffer.write("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.close();

        assertThat(readCSVFile("test.txt").get(0).getFirstShootingRange(), is("xxxox"));
    }

    @Test
    public void testSecondShootingRangeImport() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter WriteFileBuffer = new BufferedWriter(new FileWriter(createdFile));

        WriteFileBuffer.write("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.close();

        assertThat(readCSVFile("test.txt").get(0).getSecondShootingRange(), is("xxxxx"));
    }

    @Test
    public void testThirdShootingRangeImport() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter WriteFileBuffer = new BufferedWriter(new FileWriter(createdFile));

        WriteFileBuffer.write("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.close();

        assertThat(readCSVFile("test.txt").get(0).getThirdShootingeRange(), is("xxoxo"));
    }

    @Test
    public void testFinalStandingsWinner() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter WriteFileBuffer = new BufferedWriter(new FileWriter(createdFile));

        WriteFileBuffer.write("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.write("1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.write("27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx");
        WriteFileBuffer.newLine();
        WriteFileBuffer.close();

        assertThat(winner(finalStandings(readCSVFile("test.txt"))).getAthleteNumber(), is(27));
    }

    @Test
    public void testFinalStandingsRunnerUp() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter WriteFileBuffer = new BufferedWriter(new FileWriter(createdFile));

        WriteFileBuffer.write("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.write("1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.write("27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx");
        WriteFileBuffer.newLine();
        WriteFileBuffer.close();

        assertThat(BiathonAthlete.runnerUp(finalStandings(readCSVFile("test.txt"))).getAthleteNumber(),
                is(1));
    }

    @Test
    public void testFinalStandingsThird() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter WriteFileBuffer = new BufferedWriter(new FileWriter(createdFile));

        WriteFileBuffer.write("11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.write("1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo");
        WriteFileBuffer.newLine();
        WriteFileBuffer.write("27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx");
        WriteFileBuffer.newLine();
        WriteFileBuffer.close();

        assertThat(BiathonAthlete.thirdPlace(finalStandings(readCSVFile("test.txt"))).getAthleteNumber(),
                is(11));
    }

    @Test
    public void testPenaltiesAccordintToShootingsRangeMissedOne() throws IOException {
        BiathonAthlete ba = new BiathonAthlete(11, "Umar Jorgson", "SK",
                new SkiTimeResult(30, 27), "oxxxx", "oxxxx",
                "oxxxx");

        assertThat(ba.penaltiesAccordintToShootingsRange("oxxxx"), is(10));
    }

    @Test
    public void testPenaltiesAccordintToShootingsRangeMissedTwo() throws IOException {
        BiathonAthlete ba = new BiathonAthlete(11, "Umar Jorgson", "SK",
                new SkiTimeResult(30, 27), "ooxxx", "ooxxx",
                "ooxxx");

        assertThat(ba.penaltiesAccordintToShootingsRange("ooxxx"), is(20));
    }

    @Test
    public void testPenaltiesAccordintToShootingsRangeMissedThree() throws IOException {
        BiathonAthlete ba = new BiathonAthlete(11, "Umar Jorgson", "SK",
                new SkiTimeResult(30, 27), "oooxx", "oooxx",
                "oooxx");

        assertThat(ba.penaltiesAccordintToShootingsRange("oooxx"), is(30));
    }

    @Test
    public void testPenaltiesAccordintToShootingsRangeMissedFour() throws IOException {
        BiathonAthlete ba = new BiathonAthlete(11, "Umar Jorgson", "SK",
                new SkiTimeResult(30, 27), "oooox", "oooox",
                "oooox");

        assertThat(ba.penaltiesAccordintToShootingsRange("oooox"), is(40));
    }

    @Test
    public void testPenaltiesAccordintToShootingsRangeMissedFive() throws IOException {
        BiathonAthlete ba = new BiathonAthlete(11, "Umar Jorgson", "SK",
                new SkiTimeResult(30, 27), "ooooo", "ooooo",
                "ooooo");

        assertThat(ba.penaltiesAccordintToShootingsRange("ooooo"), is(50));
    }
}