package com.example.reviewmileage.interfaces;

import com.example.reviewmileage.application.review.ReviewFacade;
import com.example.reviewmileage.common.response.CommonResponse;
import com.example.reviewmileage.interfaces.dto.EventDto;
import com.example.reviewmileage.interfaces.dto.EventDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewApiController {

    private final ReviewFacade reviewFacade;
    private final EventDtoMapper eventDtoMapper;

    @PostMapping("/events")
    public CommonResponse handleEvents(@RequestBody EventDto.EventRequest eventRequest) {
        CommonResponse result = null;
        switch (eventRequest.getAction()) {
            case ("ADD") : result = registerEvent(eventRequest);
            break;
            case ("MOD") : result = modEvent(eventRequest);
            break;
            case ("DELETE") : result = deleteEvent(eventRequest);
            break;
        }
        if(result == null) {
            return CommonResponse.fail("내부 로직 오류");
        }
        return result;
    }

    public CommonResponse registerEvent(EventDto.EventRequest eventRequest) {
        var command = eventDtoMapper.toReviewRegisterCommand(eventRequest);
        var review = reviewFacade.registerReview(command);
        var response = eventDtoMapper.toEventResponse(review.getUser());
        return CommonResponse.success(response);
    }

    public CommonResponse modEvent(EventDto.EventRequest eventRequest) {
        var command = eventDtoMapper.toReviewModCommand(eventRequest);
        var review = reviewFacade.modReview(command);
        var response = eventDtoMapper.toEventResponse(review.getUser());
        return CommonResponse.success(response);
    }

    public CommonResponse deleteEvent(EventDto.EventRequest eventRequest) {
        var command = eventDtoMapper.toReviewDeleteCommand(eventRequest);
        var user = reviewFacade.deleteReview(command);
        var response = eventDtoMapper.toEventResponse(user);
        return CommonResponse.success(response);
    }

}
