package com.skrzypczyk.meetings.service.place;

import com.skrzypczyk.meetings.model.Place;
import com.skrzypczyk.meetings.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    PlaceRepository placeRepository;

    @Override
    public void save(Place place) {
        placeRepository.save(place);
    }
}
