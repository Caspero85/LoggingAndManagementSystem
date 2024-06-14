package Project.VirtualBanking.controllers;

import Project.VirtualBanking.services.SubscriptionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

    SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
}
