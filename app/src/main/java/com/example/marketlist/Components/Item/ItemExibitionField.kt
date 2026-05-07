package com.example.marketlist.Components.Item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.marketlist.Models.Item.ItemModel
import com.example.marketlist.R
import com.example.marketlist.ui.theme.Black
import com.example.marketlist.ui.theme.GreenPrimary
import com.example.marketlist.ui.theme.RedPrimary
import com.example.marketlist.ui.theme.RedSecondary
import com.example.marketlist.ui.theme.White
import com.example.marketlist.ui.theme.YellowPrimary

@Composable
fun ItemExibitionField(
    item: ItemModel,
    toggleDone : ()-> Unit,
    onEdit: () -> Unit,
    onDelete : () -> Unit
) {

    val category = item.category

    Spacer(modifier = Modifier.height(5.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{ toggleDone() }
            .background(color = White, shape = RoundedCornerShape(20.dp))
            .border(2.dp, color = category.backgroundColor, RoundedCornerShape(20.dp))
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .border(1.dp, color = category.mainColor, CircleShape)
                    .background(
                        color = if (item.checked) category.mainColor else White
                    ),
                contentAlignment = Alignment.Center
            ) {
                if(item.checked) {
                    Icon(
                        painter = painterResource(R.drawable.check),
                        contentDescription = "Check Icon",
                        tint = White
                    )
                }
            }
        }

        Spacer(Modifier.width(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "${item.quantity}x ${item.name}",
                color = Black,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Clip
            )

            Box {
                Row (
                    modifier = Modifier
                        .width(105.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    IconButton (
                        onClick = onEdit,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = YellowPrimary)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_mode_edit_24),
                            contentDescription = "Check Icon"
                        )
                    }

                    IconButton (
                        onClick = onDelete,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = RedPrimary)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_delete_24),
                            contentDescription = "Check Icon"
                        )
                    }
                }
            }
        }
    }
}