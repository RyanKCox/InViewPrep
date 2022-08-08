package com.revature.inviewprep.view.home.model

import android.view.View
import androidx.constraintlayout.motion.utils.ViewState
import com.bluelinelabs.conductor.Controller
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.revature.inviewprep.R
import com.revature.inviewprep.databinding.ButtonRowItemBinding
import com.revature.inviewprep.view.navigation.NavScreens
import com.xwray.groupie.viewbinding.BindableItem
import io.reactivex.Observable

sealed class HomeState: ViewState() {
    object Loading:HomeState()
    data class DisplayScreens(val data:List<NavItem>):HomeState()

}
interface HomeView:MvpView{
    fun menuItemIntent(): Observable<NavScreens<out Controller>>
    fun render(viewState:HomeState)
}
class NavItem(val nav:NavScreens<out Controller>): BindableItem<ButtonRowItemBinding>() {
    override fun bind(viewBinding: ButtonRowItemBinding, position: Int) {
        viewBinding.navButton.text = nav.name
    }

    override fun getLayout() = R.layout.button_row_item

    override fun initializeViewBinding(view: View) = ButtonRowItemBinding.bind(view)


}
