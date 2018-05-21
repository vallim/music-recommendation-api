package com.musicrecommendation.model;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrackContainerDto {

    private Collection<TrackDto> tracks;

}
