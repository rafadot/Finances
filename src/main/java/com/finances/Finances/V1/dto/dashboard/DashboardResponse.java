package com.finances.Finances.V1.dto.dashboard;

import com.finances.Finances.V1.dto.billing.BillingResponse;
import com.finances.Finances.V1.dto.type_spent.TypeSpentResponse;
import com.finances.Finances.V1.dto.user.UserResponse;
import com.finances.Finances.V1.dto.wallet.WalletResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardResponse {

    private UserResponse user;

    private WalletResponse wallet;

    private List<BillingResponse> nextBilling;

    private List<TypeSpentResponse> typeSpent;

    private List<String> graphicLine;

}
