package uz.gita.composeandfirebase.util.navigation

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.Flow

/**
 *   Created by Jamik on 6/14/2023 ot 5:18 PM
 **/

typealias NavigationArgs = Navigator.() -> Unit


interface NavigationHandler {
    val navigationStack: Flow<NavigationArgs>

}