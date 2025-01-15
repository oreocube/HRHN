package com.hrhn.presentation.ui.screen.main.today

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hrhn.R
import com.hrhn.presentation.ui.theme.Red02
import com.hrhn.presentation.ui.theme.SecondaryLabel
import com.hrhn.presentation.ui.theme.Typography

@Composable
fun TodayHeader(today: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.title_today),
            style = Typography.titleLarge,
            color = Red02,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = today,
            style = Typography.labelSmall,
            color = SecondaryLabel,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodayHeaderPreview() {
    TodayHeader(today = "2025.01.15")
}
