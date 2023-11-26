package ru.demidov.VPNoutline.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.demidov.VPNoutline.service.RecheckingSubscriptionPeriodService;

@Component
public class SchedulerJob {

    private final RecheckingSubscriptionPeriodService recheckingSubscriptionPeriodService;
    private static final long ONE_MINUTES = 1000 * 60 * 1;

    @Autowired
    public SchedulerJob(RecheckingSubscriptionPeriodService recheckingSubscriptionPeriodService) {
        this.recheckingSubscriptionPeriodService = recheckingSubscriptionPeriodService;
    }
    @Scheduled(fixedRate = ONE_MINUTES)
    public void recheckingPeriod(){
        recheckingSubscriptionPeriodService.recheckingPeriod();
    }
}
