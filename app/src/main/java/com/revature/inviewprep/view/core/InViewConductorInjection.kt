package com.revature.inviewprep.view.core

import com.bluelinelabs.conductor.Controller
import com.ivianuu.contributer.conductor.HasControllerInjector
import dagger.internal.Preconditions

/**
 * Controller Injection Class for use with MviBaseController
 */
class InViewConductorInjection {
    companion object{
        /**
         * Checks if the passed in controller has a Dispatcher and if so, injects dependencies
         */
        fun inject(controller: Controller){
            Preconditions.checkNotNull(controller,"controller")
            val hasDispatchingControllerInjector = findHasControllerInjector(controller)
            if(hasDispatchingControllerInjector.controllerInjector() ==null && hasDispatchingControllerInjector is Controller)
                inject(hasDispatchingControllerInjector as Controller)
        }

        /**
         * Loops through the controller's target controllers for one that implements HasControllerInjector.
         */
        private fun findHasControllerInjector(controller: Controller):HasControllerInjector{
            var superController:Controller? = controller
            do {
                superController = superController?.targetController
                if(superController == null){
                    val activity = controller.activity
                    if(activity is HasControllerInjector)
                        return activity as HasControllerInjector
                    if(activity?.application is HasControllerInjector)
                        return activity.application as HasControllerInjector

                    throw IllegalArgumentException("No inject was found for ${controller.javaClass.canonicalName}")
                }
            } while(superController !is HasControllerInjector)
            return superController as HasControllerInjector
        }
    }

}