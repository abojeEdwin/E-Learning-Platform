package com.elearningplatform.dto.response.RatingRes;

import com.elearningplatform.data.enums.Rating;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class RatingSummary {

    @NotNull(message = "This field is required")
    private double averageRating;
    @NotNull(message = "This field is required")
    private long totalRatings;
    @NotNull(message = "This field is required")
    private Map<Rating, Long> ratingDistribution;

}
