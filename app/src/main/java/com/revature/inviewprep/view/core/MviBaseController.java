package com.revature.inviewprep.view.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.rxlifecycle2.RxRestoreViewOnCreateController;
import com.hannesdorfmann.mosby3.MviConductorDelegateCallback;
import com.hannesdorfmann.mosby3.MviConductorLifecycleListener;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.ivianuu.contributer.conductor.HasControllerInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Inject;

/**
 * Abstract Controller class for use with dagger injection.Automatically injects the presenter.
 * Override onViewBound() for initial bindings.
 *
 * @param <V> The MvpView implemented view for the controller
 * @param <P> The MviPresenter<MvpView,ViewState> for the controller
 */
public abstract class MviBaseController<V extends MvpView,P extends MviPresenter<V,?>>
    extends
        RxRestoreViewOnCreateController/*MviController<V,P>()*/
    implements
    HasControllerInjector,
    MvpView,
    MviConductorDelegateCallback<V,P> {

    // Dagger controller injector
    @Inject
    DispatchingAndroidInjector<Controller> controllerInjector;

    private boolean shouldSetLifecycleListeners = true;
    private boolean restoringViewState;

    //Calls onViewBound after onCreateView
    private final LifecycleListener viewBoundControllerLifecycleListener =
        new LifecycleListener() {
            @Override public void postCreateView(
                    @NonNull Controller controller,
                    @NonNull View view){
                onViewBound(view);
            }
        };

    private final Controller.LifecycleListener mviConductorLifecycleListener =
            new MviConductorLifecycleListener<>(this);


    /**
     * Called after the view has been created. Override for initial view bindings
     *
     * @param view The view created for the controller
     */
    protected void onViewBound(View view) {
    }

    //Presenter to be Injected
    @Inject
    P presenter;

    /**
     * Overridden createPresenter from MviController. Presenter is Injected and then passed to the MviController
     *
     * @return The Injected Presenter
     */
    @NonNull
    @Override
    public P createPresenter(){
        return presenter;
    }

    public MviBaseController(){
        this(null);
    }

    /**
     * Constructor, Adds LifecycleListeners
     *
     * @param args arguments to be retained
     */
    public MviBaseController(Bundle args){
        super(args);
        addLifecycleListener(new LifecycleListener() {
            @Override public void preCreateView(@NonNull Controller controller){
                super.preCreateView(controller);
                if(shouldSetLifecycleListeners){
                    addLifecycleListener(viewBoundControllerLifecycleListener);
                    addLifecycleListener(mviConductorLifecycleListener);
                    shouldSetLifecycleListeners = false;
                }
            }
        });
    }

    /**
     * Overridden onCreateView, injects dependencies and inflates view
     *
     * @param inflater controller view inflater
     * @param container controller view container
     * @param savedViewState previously held ViewState
     * @return inflated view
     */
    @NonNull
    @Override
    protected final View onCreateView(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup container,
            @Nullable Bundle savedViewState){
        if(controllerInjector == null){
            injectDependencies();
        }
        return inflater.inflate(getLayoutId(),container,false);
    }

    /**
     * Layout of the Controller
     *
     * @return Layout ID
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * Uses the InViewConductorInjection to inject the controller with dependencies
     */
    private void injectDependencies(){
        InViewConductorInjection.Companion.inject(this);
    }

    /**
     * Overridden onDestroy() clears the controllerInjector from memory
     */
    @Override protected void onDestroy(){
        controllerInjector = null;
        super.onDestroy();
    }

    /**
     * Retrieves the controllerInjector
     *
     * @return controllerInjector
     */
    @Override
    public DispatchingAndroidInjector<Controller> controllerInjector(){
        return controllerInjector;
    }

    /**
     * Fetches the MvpView implemented view or throws and error if not the correct type
     *
     * @return The MvpView of the controller
     */
    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public V getMvpView(){
        try {
            return (V) this;
        } catch (ClassCastException e){
            String msg = "Couldn't cast View to View interfaces. Controller may not implement your MvView";
            throw new RuntimeException(msg,e);
        }
    }

    /**
     * Boolean if ViewState is being Restored from destroy
     *
     * @param restoringViewState boolean
     */
    @Override
    public void setRestoringViewState(boolean restoringViewState){
        this.restoringViewState = restoringViewState;
    }

    /**
     * Fetch if the ViewState is to be restored from destroy
     *
     * @return boolean
     */
    public boolean isRestoringViewState(){
        return restoringViewState;
    }

}