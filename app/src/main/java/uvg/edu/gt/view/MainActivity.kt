package uvg.edu.gt.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {
    val context = LocalContext.current
    val today = LocalDate.now()
    val dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE", Locale("es", "ES"))
    val dateFormatter = DateTimeFormatter.ofPattern("dd 'de' MMMM", Locale("es", "ES"))
    val dayOfWeek = today.format(dayOfWeekFormatter)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    val formattedDate = today.format(dateFormatter)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Actualización disponible") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFB3E5FC)), // Corregido aquí
                actions = {
                    TextButton(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"))
                        ContextCompat.startActivity(context, intent, null)
                    }) {
                        Text("Descargar", color = Color(0xFF03A9F4))
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = dayOfWeek,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                    Text(
                        text = formattedDate,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
                Button(
                    onClick = { /* Acción para "Terminar jornada" */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFF673AB7))
                ) {
                    Text("Terminar jornada", color = Color(0xFF673AB7))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Tre Fratelli Cayalá",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Paseo Cayalá - Edificio F1, 05",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "7:00AM - 11:00PM",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                    IconButton(
                        onClick = {
                            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/epgrnZDrFGCP8FqS9?g_st=ac"))
                            ContextCompat.startActivity(context, mapIntent, null)
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Direcciones",
                            tint = Color(0xFF673AB7)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            Toast.makeText(context, "Hola, soy Pablo José Méndez Alvarado", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7))
                    ) {
                        Text("Iniciar", color = Color.White)
                    }
                    TextButton(onClick = {
                        Toast.makeText(context, "Comida italiana /// Precios normales de 75 a 180 GTQ", Toast.LENGTH_SHORT).show()
                    }) {
                        Text("Detalles", color = Color(0xFF673AB7))
                    }
                }
            }
        }
    }
}
