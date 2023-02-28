package com.finances.Finances.V1.dto.dashboard;

import com.finances.Finances.V1.dto.billing.BillingResponse;
import com.finances.Finances.V1.dto.wallet.WalletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {

    private WalletResponse wallet;



}
