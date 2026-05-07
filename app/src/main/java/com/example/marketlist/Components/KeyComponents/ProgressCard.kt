package com.example.marketlist.Components.KeyComponents

import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketlist.R
import com.example.marketlist.ui.theme.BrownPrimary
import com.example.marketlist.ui.theme.BrownSecondary
import com.example.marketlist.ui.theme.InterFamily

@Composable
fun ProgressCard(
    total: Int,
    checked: Int,
) {
    val context = LocalContext.current

    val progress = if (total > 0) checked / total.toFloat() else 0f
    var played by rememberSaveable() { mutableStateOf(false) }
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.success_sound) }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    LaunchedEffect(progress) {

        if (progress < 1f) {
            played = false
        }

        if (progress >= 1f && !played) {
            played = true
            mediaPlayer.seekTo(0)
            mediaPlayer.start()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(BrownSecondary)
            .border(1.dp, BrownPrimary, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {

        Text(
            text = "Progresso do Carrinho",
            fontFamily = InterFamily,
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            color = BrownPrimary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = if (progress >= 1f) "Completa!" else "Quase lá hein!",
                fontFamily = InterFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = BrownPrimary
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(BrownSecondary)
                    .border(1.dp, BrownPrimary, RoundedCornerShape(50))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$checked/$total",
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = BrownPrimary
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .height(10.dp)
                .clip(RoundedCornerShape(50))
                .background(BrownPrimary.copy(alpha = 0.2f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress.coerceIn(0f, 1f))
                    .fillMaxHeight()
                    .background(BrownPrimary)
            )
        }
    }
}