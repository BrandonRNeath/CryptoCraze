package com.nemesisprotocol.cryptocraze.presentation.wallet_screen.add_wallet

import androidx.compose.foundation.layout.*
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
fun AddPaymentCardDialog(
    addPaymentCardDialog: MutableState<Boolean>,
    navController: NavHostController
) {
    Column {

        if (addPaymentCardDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    addPaymentCardDialog.value = false
                },
                title = {
                    Text(text = "Your Fiat Wallet")
                },
                text = {
                    Text("You do not have a Fiat Wallet. \nSelect Create to add a Fiat Wallet")
                },
                confirmButton = {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            addPaymentCardDialog.value = false
                            navController.navigate(
                                Screen.AddPaymentCard.route
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
                            addPaymentCardDialog.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }, shape = RoundedCornerShape(30.dp)
                )
            }
        }
    }
    