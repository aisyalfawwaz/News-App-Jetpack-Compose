package com.github.user.newsapp.ui.screen.ui.theme
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.github.user.newsapp.R

@Composable
fun ProfileFragment() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Foto lingkaran
        val imageUrl =
            "https://media.licdn.com/dms/image/D5603AQF4itgSTERqkA/profile-displayphoto-shrink_800_800/0/1673328963665?e=2147483647&v=beta&t=h4ejdRhNXqvN8vQzaHkJsp-4hsCWAr68cxrTL9ErtR8"
        val painter = rememberImagePainter(
            data = imageUrl,
            builder = {

            }
        )

        var showHearts by remember { mutableStateOf(false) }

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .clickable { showHearts = !showHearts }
                .animateContentSize(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nama
        Text(
            text = "Aisy Al Fawwaz",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Alamat email
        Text(
            text = "aisyalfawwaz111@gmail.com",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // LinkedIn and Instagram icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val linkedInUrl = "https://www.linkedin.com/in/aisy-al-fawwaz-14937723a/"
            val instagramUrl = "https://www.instagram.com/fawwaz_mee/"

            Image(
                painter = rememberImagePainter("https://static-00.iconduck.com/assets.00/linkedin-icon-1024x1024-z5dvl47c.png"),
                contentDescription = "LinkedIn",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        // Open LinkedIn profile
                        openUrl(linkedInUrl, context)
                    }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = rememberImagePainter("https://cdn3.iconfinder.com/data/icons/popular-services-brands/512/instagram-512.png"),
                contentDescription = "Instagram",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        // Open LinkedIn profile
                        openUrl(instagramUrl, context)
                    }
            )
        }

        if (showHearts) {
            RepeatHeartAnimation()
        }
    }
}

@Composable
fun RepeatHeartAnimation() {
    // Add your heart animation code here
    // This is just a placeholder
    Text(text = "❤️", fontSize = 24.sp)
}

private fun openUrl(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
