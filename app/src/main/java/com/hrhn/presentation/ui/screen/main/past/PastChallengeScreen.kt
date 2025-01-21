package com.hrhn.presentation.ui.screen.main.past

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrhn.R
import com.hrhn.domain.model.Challenge
import com.hrhn.domain.model.Emoji
import com.hrhn.presentation.ui.theme.CellFill
import com.hrhn.presentation.ui.theme.CellLabel
import com.hrhn.presentation.ui.theme.None
import com.hrhn.presentation.ui.theme.SecondaryLabel
import com.hrhn.presentation.ui.theme.Typography
import com.hrhn.presentation.ui.theme.White
import com.hrhn.presentation.util.formatDateString
import java.time.LocalDateTime

@Composable
fun PastChallengeList(
    list: List<Challenge>,
) {

}

private val ReenieBeanie = FontFamily(
    Font(R.font.reenie_beanie, FontWeight.Normal),
)

@Composable
private fun ChallengeItem(
    challenge: Challenge,
) {
    val date = remember(challenge.date) {
        challenge.date.formatDateString()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = CellFill)
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
        )
    )
}

@Composable
@Preview(showBackground = true)
private fun PastChallengeListPreview() {
    PastChallengeList(list = listOf())
}
