package com.example.coldsounds.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

interface Screen {

    fun show(containerId: Int, supportFragmentManager: FragmentManager)

    object Empty : Screen {
        override fun show(containerId: Int, supportFragmentManager: FragmentManager) = Unit
    }

    abstract class Replace(private val clasz: Class<out Fragment>) : Screen {
        override fun show(containerId: Int, supportFragmentManager: FragmentManager) {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                supportFragmentManager.popBackStack()
            }
            supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment())
                .addToBackStack(null)
                .commit()
        }

        protected open fun fragment(): Fragment = clasz.getDeclaredConstructor().newInstance()
    }

    abstract class Splash(private val clasz: Class<out Fragment>) : Screen {
        override fun show(containerId: Int, supportFragmentManager: FragmentManager) {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                supportFragmentManager.popBackStack()
            }
            supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment())
                .commit()

        }

        protected open fun fragment(): Fragment = clasz.getDeclaredConstructor().newInstance()
    }

    abstract class Add(private val clasz: Class<out Fragment>) : Screen {
        override fun show(containerId: Int, supportFragmentManager: FragmentManager) {
            supportFragmentManager.beginTransaction()
                .add(containerId, fragment())
                .addToBackStack(clasz.simpleName)
                .commit()
        }

        protected open fun fragment(): Fragment = clasz.getDeclaredConstructor().newInstance()
    }

    abstract class BottomSheet(private val clasz: Class<out Fragment>) : Screen {

        override fun show(containerId: Int, supportFragmentManager: FragmentManager) {
            fragment().show(
                supportFragmentManager,
                clasz.simpleName
            )
        }

        abstract fun fragment(): BottomSheetDialogFragment
    }

    object Pop : Screen {
        override fun show(containerId: Int, supportFragmentManager: FragmentManager) {
            supportFragmentManager.popBackStack()
        }
    }

}