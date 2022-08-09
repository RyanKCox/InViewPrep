package com.revature.inviewprep.view.home.model

import android.util.Log
import android.view.View
import androidx.constraintlayout.motion.utils.ViewState
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.revature.inviewprep.R
import com.revature.inviewprep.databinding.ButtonRowItemBinding
import com.revature.inviewprep.view.navigation.NavScreens
import com.xwray.groupie.viewbinding.BindableItem
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

sealed class HomeState: ViewState() {
    object Loading:HomeState()
    data class DisplayScreens(val data:List<NavScreens<out Controller>>):HomeState()

}
interface HomeView:MvpView{
//    fun menuItemIntent(): Observable<NavScreens<out Controller>>
    fun render(viewState:HomeState)
}
class NavItem(private val nav:NavScreens<out Controller>, val router: Router): BindableItem<ButtonRowItemBinding>() {
    override fun bind(viewBinding: ButtonRowItemBinding, position: Int) {
        viewBinding.navButton.text = nav.name
        viewBinding.navButton.setOnClickListener {
            Log.d("NavItem","Click Inside bind function")

        router.pushController(
            RouterTransaction.with(nav.controller.newInstance())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
        }
    }

    override fun getLayout() = R.layout.button_row_item

    override fun initializeViewBinding(view: View) = ButtonRowItemBinding.bind(view)


}
