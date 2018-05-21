package com.musicrecommendation.model;

import java.math.BigDecimal;
import java.util.Arrays;

public enum MusicCategoryEnum {

    PARTY("party", new PartyCategory()),
    POP("pop", new PopCategory()),
    ROCK("rock", new RockCategory()),
    CLASSICAL("classical", new ClassicalCategory());

    private final String value;
    private final MusicCategoryRule musicCategoryRule;

    MusicCategoryEnum(String value, MusicCategoryRule musicCategoryRule) {
        this.value = value;
        this.musicCategoryRule = musicCategoryRule;
    }

    public String getValue() {
        return value;
    }

    public static MusicCategoryEnum find(BigDecimal temperature) {
        if (temperature == null) {
            throw new IllegalArgumentException("temperature can not be null");
        }

        return Arrays.stream(MusicCategoryEnum.values())
            .filter(musicCategory -> musicCategory.musicCategoryRule.is(temperature)).findFirst()
            .orElseThrow(() ->
                new IllegalArgumentException("Can not be found a music style for the temperature " + temperature)
            );
    }

    interface MusicCategoryRule {

        boolean is(BigDecimal temperature);
    }

    static class PartyCategory implements MusicCategoryRule {

        @Override
        public boolean is(BigDecimal temperature) {
            return temperature.compareTo(new BigDecimal("30")) > 0;
        }
    }

    static class PopCategory implements MusicCategoryRule {

        @Override
        public boolean is(BigDecimal temperature) {
            return temperature.compareTo(new BigDecimal("15")) >= 0 && temperature.compareTo(new BigDecimal("30")) <= 0;
        }
    }

    static class RockCategory implements MusicCategoryRule {

        @Override
        public boolean is(BigDecimal temperature) {
            return temperature.compareTo(new BigDecimal("10")) >= 0 && temperature.compareTo(new BigDecimal("14")) <= 0;
        }
    }

    static class ClassicalCategory implements MusicCategoryRule {

        @Override
        public boolean is(BigDecimal temperature) {
            return temperature.compareTo(new BigDecimal("10")) < 0;
        }
    }
}
