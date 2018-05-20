package com.musicrecommendation.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.Test;

public class MusicCategoryEnumTest {

    @Test
    public void shouldBePartyCategoryIfTemperatureIsAbove30() {

        BigDecimal temperature = new BigDecimal("30.3");

        MusicCategoryEnum musicCategory = MusicCategoryEnum.find(temperature);

        assertEquals(MusicCategoryEnum.PARTY, musicCategory);
    }

    @Test
    public void shouldBePopCategoryIfTemperatureIsBetween15And30() {

        BigDecimal temperature = new BigDecimal("29.85");

        MusicCategoryEnum musicCategory = MusicCategoryEnum.find(temperature);

        assertEquals(MusicCategoryEnum.POP, musicCategory);
    }


    @Test
    public void shouldBeRockCategoryIfTemperatureIsBetween10And14() {

        BigDecimal temperature = new BigDecimal("10");

        MusicCategoryEnum musicCategory = MusicCategoryEnum.find(temperature);

        assertEquals(MusicCategoryEnum.ROCK, musicCategory);
    }

    @Test
    public void shouldBeClassicalCategoryIfTemperatureIsLessThan10() {

        BigDecimal temperature = new BigDecimal("9.99");

        MusicCategoryEnum musicCategory = MusicCategoryEnum.find(temperature);

        assertEquals(MusicCategoryEnum.CLASSICAL, musicCategory);
    }

}
