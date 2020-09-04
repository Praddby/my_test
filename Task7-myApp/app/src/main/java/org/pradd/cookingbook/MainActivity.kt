package org.pradd.cookingbook

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import kotlinx.android.synthetic.main.activity_main.bottom_navigation
import org.pradd.cookingbook.category.CategoryActivity
import org.pradd.cookingbook.database.LoginActivity
import org.pradd.cookingbook.recipes.RecipesActivity
import org.pradd.cookingbook.user.UserRecipesActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnHome = findViewById<BottomNavigationItemView>(R.id.bottom_menu_home)
        val btnCategory = findViewById<BottomNavigationItemView>(R.id.bottom_menu_category)
        val btnMyRecipe = findViewById<BottomNavigationItemView>(R.id.bottom_menu_my_recipe)
        myColorStateList(listOf(btnHome, btnCategory, btnMyRecipe))
        bottom_navigation.selectedItemId = R.id.bottom_menu_home

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bottom_menu_home -> {
                    true
                }
                R.id.bottom_menu_category -> {
                    goActivityCategory()
                    true
                }
                R.id.bottom_menu_my_recipe -> {
                    goActivityUserRecipes()
                    true
                }
                else -> false
            }
        }
    }

    fun Click(view: View) {
        when (view.id) {
            R.id.btn_breakfast -> goActivityRecipes("breakfast", 10)
            R.id.btn_lunch -> goActivityRecipes("lunch", 11)
            R.id.btn_dessert -> goActivityRecipes("dessert", 8)
            R.id.btn_dinner -> goActivityRecipes("dinner", 13)
            R.id.btn_all_recipes -> goActivityRecipes("all_recipes")
        }
    }

    private fun goActivityRecipes(name: String, categoryId: Int? = null) {
        val intent = Intent(this, RecipesActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("name", name)
        intent.putExtra("categoryId", categoryId)
        startActivity(intent)
    }

    private fun logout(){

    }

    private fun goActivityCategory(){
        val intent = Intent(this, CategoryActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun goActivityUserRecipes(){
        val intent = Intent(this, UserRecipesActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    @SuppressLint("RestrictedApi")
    private fun myColorStateList(navigationItems: List<BottomNavigationItemView>) {
        val color = ColorStateList(
            arrayOf(
                intArrayOf( android.R.attr.state_checked ),
                intArrayOf( android.R.attr.state_enabled )
            ),
            intArrayOf(
                getColor(R.color.colorAccent),
                getColor(R.color.colorBottomMenuEnabled)
            )
        )
        navigationItems.forEach {
            it.setIconTintList(color)
            it.setTextColor(color)
        }
    }
}