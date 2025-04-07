package com.example.retrofit.presentation.ui.user_list_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retrofit.domain.model.User

@Composable
fun UserItem(user: User, onUpdate: (User) -> Unit, onDelete: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = user.id.toString(), style = MaterialTheme.typography.bodyLarge)
            Text(text = user.name, style = MaterialTheme.typography.bodyMedium)
            Text(text = user.email, style = MaterialTheme.typography.bodySmall)
            Row {
                Button(onClick = { onUpdate(user.copy(name = "Updated Name")) }) {
                    Text("Update")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { user.id?.let { onDelete(it) } }) {
                    Text("Delete")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserItem() {
    val sampleUser = User(
        id = 1,
        name = "John Doe",
        email = "johndoe@example.com"
    )

    UserItem(
        user = sampleUser,
        onUpdate = { /* Do nothing in preview */ },
        onDelete = { /* Do nothing in preview */ }
    )
}
