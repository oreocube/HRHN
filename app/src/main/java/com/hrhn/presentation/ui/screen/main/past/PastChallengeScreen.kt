package com.hrhn.presentation.ui.screen.main.past

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.hrhn.R
import com.hrhn.domain.model.Challenge
import com.hrhn.domain.model.Emoji
import com.hrhn.presentation.ui.theme.CellFill
import com.hrhn.presentation.ui.theme.CellLabel
import com.hrhn.presentation.ui.theme.None
import com.hrhn.presentation.ui.theme.SecondaryLabel
import com.hrhn.presentation.ui.theme.Typography
import com.hrhn.presentation.ui.theme.White
import com.hrhn.presentation.util.fakePagingItems
import com.hrhn.presentation.util.formatDateString
import java.time.LocalDateTime

@Composable
fun PastChallengeScreen(
    viewModel: PastChallengeViewModel,
    onItemClick: (Challenge) -> Unit,
) {
    val challenges = viewModel.challengesFlow.collectAsLazyPagingItems()

    PastChallengeList(
        challenges = challenges,
        onItemClick = onItemClick,
    )
}

@Composable
fun PastChallengeList(
    challenges: LazyPagingItems<Challenge>,
    onItemClick: (Challenge) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.menu_title_past_challenge),
                    style = Typography.titleLarge,
                )
            }

            items(
                count = challenges.itemCount,
                key = challenges.itemKey { item -> item.id }
            ) { index ->
                challenges[index]?.run {
                    ChallengeItem(
                        challenge = this,
                        onClick = onItemClick,
                    )
                }
            }
        }

        if (challenges.loadState.append.endOfPaginationReached && challenges.itemCount == 0) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.message_empty_challenges),
                    color = SecondaryLabel,
                )
            }
        }
    }
}

private val ReenieBeanie = FontFamily(
    Font(R.font.reenie_beanie, FontWeight.Normal),
)

@Composable
private fun ChallengeItem(
    challenge: Challenge,
    onClick: (Challenge) -> Unit,
) {
    val date = remember(challenge.date) {
        challenge.date.formatDateString()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = CellFill)
            .clickable { onClick(challenge) }
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ReviewEmoji(emoji = challenge.emoji)
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = date,
                style = Typography.labelSmall,
                color = SecondaryLabel
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = challenge.content,
            color = CellLabel,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun ReviewEmoji(
    emoji: Emoji?,
) {
    val backgroundColor = remember(emoji?.color) {
        emoji?.color?.let { Color(android.graphics.Color.parseColor(it)) } ?: None
    }

    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = backgroundColor)
            .padding(5.dp),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = emoji?.label.orEmpty(),
            fontSize = 24.sp,
            fontFamily = ReenieBeanie,
            color = White
        )
    }
}

@Composable
@Preview
private fun PastChallengeItemPreview() {
    ChallengeItem(
        challenge = Challenge(
            date = LocalDateTime.now(),
            content = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
            emoji = Emoji.RED
        ),
        onClick = {},
    )
}

@Composable
@Preview(showBackground = true)
private fun PastChallengeListPreview() {
    val fakeData = listOf(
        Challenge(
            id = 1,
            date = LocalDateTime.now(),
            content = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
            emoji = Emoji.RED
        ),
        Challenge(
            id = 2,
            date = LocalDateTime.now(),
            content = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
            emoji = Emoji.GREEN
        ),
        Challenge(
            id = 3,
            date = LocalDateTime.now(),
            content = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
            emoji = Emoji.BLUE
        ),
    )

    val pagingItems = fakePagingItems(fakeData)
    PastChallengeList(
        challenges = pagingItems,
        onItemClick = {},
    )
}
