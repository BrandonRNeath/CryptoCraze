package com.nemesisprotocol.cryptocraze.presentation.wallet_screen.add_wallet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nemesisprotocol.cryptocraze.Screen

@Composable
fun AddCryptoCrazeVisaCardDialog(
    addCryptoCrazeVisaCardDialog: MutableState<Boolean>,
    navController: NavHostController
) {
    Column {

        if (addCryptoCrazeVisaCardDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    addCryptoCrazeVisaCardDialog.value = false
                },
                title = {
                    Text(text = "Your Crypto Craze Visa Card")
                },
                text = {
                    Text("You do not have a Crypto Craze Visa Card. \nSelect Create to add a Crypto Craze Visa Card")
                },
                confirmButton = {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            addCryptoCrazeVisaCardDialog.value = false
                            navController.navigate(
                                Screen.AddCryptoCrazeVisaCard.route
                            )
                        }
                    ) {
                        Text("Create")
                    }
                },
                dismissButton = {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            addCryptoCrazeVisaCardDialog.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }, shape = RoundedCornerShape(30.dp)
                )
            }
        }
    }
    