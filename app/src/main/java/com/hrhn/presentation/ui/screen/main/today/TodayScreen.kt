package com.hrhn.presentation.ui.screen.main.today

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrhn.R
import com.hrhn.domain.model.Challenge
import com.hrhn.presentation.ui.theme.CellFill
import com.hrhn.presentation.ui.theme.CellLabel
import com.hrhn.presentation.ui.theme.Gray54
import com.hrhn.presentation.ui.theme.Red02
import com.hrhn.presentation.ui.theme.SecondaryLabel
import com.hrhn.presentation.ui.theme.Typography
import com.hrhn.presentation.ui.theme.White
import com.hrhn.presentation.util.formatDateWithYearString

@Composable
fun TodayScreen(viewModel: TodayViewModel) {
    val today by viewModel.today.collectAsStateWithLifecycle()
    val todayString by remember(today) {
        derivedStateOf { today.formatDateWithYearString() }
    }
    val todayChallenge by viewModel.todayChallenge.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TodayHeader(today = todayString)
        TodayCard(
            modifier = Modifier
                .weight(1f)
                .padding(start = 40.dp, end = 40.dp, top = 16.dp, bottom = 40.dp),
            challenge = todayChallenge,
            onCardClick = viewModel::editTodayChallenge,
            onAddClick = viewModel::addTodayChallenge,
        )
    }
}

@Composable
private fun TodayHeader(today: String) {
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

@Composable
private fun TodayCard(
    modifier: Modifier = Modifier,
    challenge: Challenge?,
    onCardClick: () -> Unit,
    onAddClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(size = 16.dp))
            .background(color = CellFill)
            .clickable(
                enabled = challenge != null,
                onClick = onCardClick,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (challenge == null) {
            Text(
                text = stringResource(id = R.string.message_no_challenge),
                style = Typography.labelSmall,
                color = Gray54,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Red02)
                    .clickable(onClick = onAddClick)
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                text = stringResource(id = R.string.button_add_challenge),
                style = Typography.labelSmall,
                color = White,
            )
        } else {
            Text(
                text = challenge.content,
                style = Typography.titleLarge,
                color = CellLabel,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TodayHeaderPreview() {
    TodayHeader(today = "2025.01.15")
}

@Preview(showBackground = true)
@Composable
private fun TodayCardPreview() {
    TodayCard(
        challenge = Challenge(content = "빨래널기"),
        onCardClick = {},
        onAddClick = {},
    )
}
