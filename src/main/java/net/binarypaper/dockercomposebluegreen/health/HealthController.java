package net.binarypaper.dockercomposebluegreen.health;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "health")
@CrossOrigin(originPatterns = { "${application.cors.origins}" })
@Tag(name = "Health API", description = "Manage the application health status")
@RequiredArgsConstructor
public class HealthController {

    private final ApplicationContext applicationContext;

    @GetMapping(path = "liveness/{state}")
    @Operation(summary = "Set the application liveness state", description = "Set the application liveness state")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Application liveness state set") })
    public void setLivenessState(@PathVariable(name = "state") LivenessState livenessState) {
        AvailabilityChangeEvent.publish(applicationContext, livenessState);
    }

    @GetMapping(path = "readiness/{state}")
    @Operation(summary = "Set the application readiness state", description = "Set the application readiness state")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Application readiness state set") })
    public void setReadinessState(@PathVariable(name = "state") ReadinessState readinessState) {
        AvailabilityChangeEvent.publish(applicationContext, readinessState);
    }
}