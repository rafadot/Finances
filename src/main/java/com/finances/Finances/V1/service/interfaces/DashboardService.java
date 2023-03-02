package com.finances.Finances.V1.service.interfaces;

import com.finances.Finances.V1.dto.dashboard.DashboardResponse;
import com.finances.Finances.V1.model.User;

import java.util.UUID;

public interface DashboardService {

    DashboardResponse getUserDashboardLogin(User user);

    DashboardResponse getUserDashboard(UUID userID);
}
