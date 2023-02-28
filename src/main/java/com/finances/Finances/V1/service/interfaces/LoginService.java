package com.finances.Finances.V1.service.interfaces;

import com.finances.Finances.V1.dto.dashboard.DashboardResponse;
import com.finances.Finances.V1.model.Login;

public interface LoginService {
    DashboardResponse login(Login login);
}
