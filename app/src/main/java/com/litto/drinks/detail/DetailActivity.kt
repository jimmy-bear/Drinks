package com.litto.drinks.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.litto.drinks.R
import com.litto.drinks.data.Drink
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import java.util.concurrent.TimeUnit

class DetailActivity : AppCompatActivity() {
    companion object {
        const val DRINK_NAME = "DRINK_NAME"
    }
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val drinkName = intent.getStringExtra(DRINK_NAME)!!
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val factory = DetailViewModelFactory.createFactory(this)
        detailViewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        detailViewModel.drink.observe(this, Observer { drink ->
            this.showDrinkDetail(drink)
        })
        detailViewModel.glassImage.observe(this, Observer {detailModel ->
            glass_image.setImageDrawable(resources.getDrawable(detailModel.imageId, null))
        })
    }

    private fun showDrinkDetail(detail: Drink?) {
        if (detail == null) {
            return
        }
        collapsing_toolbar.title = detail.name
        make_time.text = getString(
            R.string.make_time_seconds,
            TimeUnit.MILLISECONDS.toSeconds(detail.time)
        )
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorites, menu)

        val menuItem = menu.findItem(R.id.action_favorite)
        detailViewModel.drink.observe(this, Observer { drink ->
            if (drink.favorite) {
                menuItem.icon = resources.getDrawable(R.drawable.ic_favorite, null)
            } else {
                menuItem.icon = resources.getDrawable(R.drawable.ic_favorite_border, null)
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_favorite) {
            detailViewModel.setFavorite()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}