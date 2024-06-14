package Project.VirtualBanking.services;

import Project.VirtualBanking.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }
}
