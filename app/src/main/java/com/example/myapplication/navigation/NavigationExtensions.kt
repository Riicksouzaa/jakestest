package com.example.myapplication.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController

// Dealing with issue described in this article https://medium.com/@ffvanderlaan/fixing-the-dreaded-is-unknown-to-this-navcontroller-68c4003824ce
// Navigates only if this is safely possible; when this Fragment is still the current destination.
// Not necessary in this small proj but good practice

/**
 * Navigates only if this is safely possible; when this Fragment is still the current destination.
 */
fun Fragment.navigateSafe(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val navController = findNavController()
    val currentDestination = navController.currentDestination
    val action = currentDestination?.getAction(resId) ?: navController.graph.getAction(resId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navController.navigate(resId, args, navOptions, navigatorExtras)
    }
}

/**
 * Navigates only if this is safely possible; when this Fragment is still the current destination.
 */
fun Fragment.navigateSafe(directions: NavDirections, navOptions: NavOptions? = null) {
    val navController = findNavController()
    val currentDestination = navController.currentDestination
    val action = currentDestination?.getAction(directions.actionId)
        ?: navController.graph.getAction(directions.actionId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navController.navigate(directions, navOptions)
    }
}

/**
 * Navigates only if this is safely possible; when this Fragment is still the current destination.
 */
fun Fragment.navigateSafe(
    directions: NavDirections,
    navigatorExtras: Navigator.Extras
) {
    val navController = findNavController()
    val currentDestination = navController.currentDestination
    val action = currentDestination?.getAction(directions.actionId)
        ?: navController.graph.getAction(directions.actionId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navController.navigate(directions, navigatorExtras)
    }
}

/**
 * Navigates only if this is safely possible;
 */
fun NavController.navigateSafe(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    val action = currentDestination?.getAction(resId) ?: graph.getAction(resId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navigate(resId, args, navOptions, navExtras)
    }
}

/**
 * Navigates only if this is safely possible;
 */
fun NavController.navigateSafe(
    directions: NavDirections
) {
    val action = currentDestination?.getAction(directions.actionId)
        ?: graph.getAction(directions.actionId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navigate(directions)
    }
}

fun Fragment.isFragmentInBackStack(destinationId: Int) =
    try {
        findNavController().getBackStackEntry(destinationId)
        true
    } catch (e: Exception) {
        false
    }