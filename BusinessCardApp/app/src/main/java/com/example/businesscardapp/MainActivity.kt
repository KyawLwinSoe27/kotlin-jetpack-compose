package com.example.businesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscardapp.ui.theme.BusinessCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCardAppLayout()
                }
            }
        }
    }
}

@Composable
fun BusinessCardAppLayout() {
    Column(verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        BusinessLogoNameAndTitle(name = "Kyaw Lwin Soe", title = "Software Developer")
        Spacer(modifier = Modifier.height(20.dp))
        ContactInformation(phone = "123-456-7890", email = "soek38902@gmial.com" , socialMedia = "soek38902")
    }
}

@Composable
fun BusinessLogoNameAndTitle(name: String, title: String) {
    val image = painterResource(R.drawable.user)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = image, contentDescription = "User Image")
        Text(text = name, fontSize = 30.sp)
        Text(text = title)
    }
}

@Composable
fun ContactInformation(phone: String, email: String, socialMedia : String) {
    Column {
        val phoneIcon = painterResource(R.drawable.phone_call)
        val emailIcon = painterResource(R.drawable.mail)
        val socialMediaIcon = painterResource(R.drawable.instagram)

        IconAndText(image = phoneIcon, text = "Phone: $phone")
        IconAndText(image = emailIcon, text = "Email: $email")
        IconAndText(image = socialMediaIcon, text = "Social Media: $socialMedia")
    }
}

@Composable
fun IconAndText(image: Painter , text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .width(50.dp)
            .height(50.dp)) {
            Image(painter = image, contentDescription = text, contentScale = ContentScale.Fit)
        }
        Spacer(modifier = Modifier.width(12.dp)) // Add some spacing between the icon and text
        Text(text = text)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardAppTheme {
        BusinessCardAppLayout()
    }
}