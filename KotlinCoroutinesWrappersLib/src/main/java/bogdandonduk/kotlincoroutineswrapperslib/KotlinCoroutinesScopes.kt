package bogdandonduk.kotlincoroutineswrapperslib

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object KotlinCoroutinesScopes {
    /** IO Scope */
            private val ScopeIO = CoroutineScope(IO)
            private val ScopeIOJobs = mutableMapOf<String, Job>()

            fun launchNewIOScopeJob(
                jobKeyToSave: String,
                replaceOldJobWithSameKey: Boolean = false,
                deleteOnCompletion: Boolean = true,
                job: (CoroutineScope) -> Unit
            ) {
                if(replaceOldJobWithSameKey){
                    ScopeIOJobs[jobKeyToSave]?.cancel()
                    ScopeIOJobs.remove(jobKeyToSave)
                }

                if(!ScopeIOJobs.containsKey(jobKeyToSave)) {
                    ScopeIOJobs[jobKeyToSave] = ScopeIO.launch {
                        job.invoke(this)
                    }

                    if(deleteOnCompletion) ScopeIOJobs[jobKeyToSave]!!.invokeOnCompletion {
                        ScopeIOJobs.remove(jobKeyToSave)
                    }
                }
            }

            fun relaunchIOScopeJobIfSaved(
                key: String,
                relaunchEvenIfAlreadyActive: Boolean = false,
                deleteOnCompletion: Boolean = true
            ) {
                ScopeIOJobs[key]?.run {
                    if(relaunchEvenIfAlreadyActive || !isActive) {
                        start()

                        if(deleteOnCompletion) ScopeIOJobs[key]!!.invokeOnCompletion {
                            ScopeIOJobs.remove(key)
                        }
                    }
                }
            }

            fun getIOScopeJob(key: String) = ScopeIOJobs[key]

            fun cancelIOScopeJob(key: String, delete: Boolean = true) {
                ScopeIOJobs[key]?.cancel()

                if(delete) ScopeIOJobs.remove(key)
            }

            fun getAllIOScopeJobs() = ScopeIOJobs

            fun cancelAllIOScopeJob(delete: Boolean = true) {
                ScopeIOJobs.forEach {
                    it.value.cancel()
                }

                if(delete) ScopeIOJobs.clear()
            }

            fun getIOScope() = ScopeIO
    /** */

    /** Default Scope */
            private val ScopeDefault = CoroutineScope(Default)
            private val ScopeDefaultJobs = mutableMapOf<String, Job>()

            fun launchNewDefaultScopeJob(
                jobKeyToSave: String,
                replaceOldJobWithSameKey: Boolean = false,
                deleteOnCompletion: Boolean = true,
                job: (CoroutineScope) -> Unit
            ) {
                if(replaceOldJobWithSameKey){
                    ScopeDefaultJobs[jobKeyToSave]?.cancel()
                    ScopeDefaultJobs.remove(jobKeyToSave)
                }

                if(!ScopeDefaultJobs.containsKey(jobKeyToSave)) {
                    ScopeDefaultJobs[jobKeyToSave] = ScopeDefault.launch {
                        job.invoke(this)
                    }

                    if(deleteOnCompletion) ScopeDefaultJobs[jobKeyToSave]!!.invokeOnCompletion {
                        ScopeDefaultJobs.remove(jobKeyToSave)
                    }
                }
            }

            fun relaunchDefaultScopeJobIfSaved(
                key: String,
                relaunchEvenIfAlreadyActive: Boolean = false,
                deleteOnCompletion: Boolean = true
            ) {
                ScopeDefaultJobs[key]?.run {
                    if(relaunchEvenIfAlreadyActive || !isActive) {
                        start()

                        if(deleteOnCompletion) ScopeDefaultJobs[key]!!.invokeOnCompletion {
                            ScopeDefaultJobs.remove(key)
                        }
                    }
                }
            }

            fun getDefaultScopeJob(key: String) = ScopeDefaultJobs[key]

            fun cancelDefaultScopeJob(key: String, delete: Boolean = true) {
                ScopeDefaultJobs[key]?.cancel()

                if(delete) ScopeDefaultJobs.remove(key)
            }

            fun getAllDefaultScopeJobs() = ScopeDefaultJobs

            fun cancelAllDefaultScopeJob(delete: Boolean = true) {
                ScopeDefaultJobs.forEach {
                    it.value.cancel()
                }

                if(delete) ScopeDefaultJobs.clear()
            }

            fun getDefaultScope() = ScopeDefault

    /** */

    /** Main Scope */
            private val ScopeMain = CoroutineScope(Main)
            private val ScopeMainJobs = mutableMapOf<String, Job>()

            fun launchNewMainScopeJob(
                jobKeyToSave: String,
                replaceOldJobWithSameKey: Boolean = false,
                deleteOnCompletion: Boolean = true,
                job: (CoroutineScope) -> Unit
            ) {
                if(replaceOldJobWithSameKey){
                    ScopeMainJobs[jobKeyToSave]?.cancel()
                    ScopeMainJobs.remove(jobKeyToSave)
                }

                if(!ScopeMainJobs.containsKey(jobKeyToSave)) {
                    ScopeMainJobs[jobKeyToSave] = ScopeMain.launch {
                        job.invoke(this)
                    }

                    if(deleteOnCompletion) ScopeMainJobs[jobKeyToSave]!!.invokeOnCompletion {
                        ScopeMainJobs.remove(jobKeyToSave)
                    }
                }
            }

            fun relaunchMainScopeJobIfSaved(
                key: String,
                relaunchEvenIfAlreadyActive: Boolean = false,
                deleteOnCompletion: Boolean = true
            ) {
                ScopeMainJobs[key]?.run {
                    if(relaunchEvenIfAlreadyActive || !isActive) {
                        start()

                        if(deleteOnCompletion) ScopeMainJobs[key]!!.invokeOnCompletion {
                            ScopeMainJobs.remove(key)
                        }
                    }
                }
            }

            fun getMainScopeJob(key: String) = ScopeMainJobs[key]

            fun cancelMainScopeJob(key: String, delete: Boolean = true) {
                ScopeMainJobs[key]?.cancel()

                if(delete) ScopeMainJobs.remove(key)
            }

            fun getAllMainScopeJobs() = ScopeMainJobs

            fun cancelAllMainScopeJob(delete: Boolean = true) {
                ScopeMainJobs.forEach {
                    it.value.cancel()
                }

                if(delete) ScopeMainJobs.clear()
            }

            fun getMainScope() = ScopeMain
    /** */

    /** Main.immediate Scope */
            private val ScopeMainImmediate = CoroutineScope(Main.immediate)
            private val ScopeMainImmediateJobs = mutableMapOf<String, Job>()

            fun launchNewMainImmediateScopeJob(
                jobKeyToSave: String,
                replaceOldJobWithSameKey: Boolean = false,
                deleteOnCompletion: Boolean = true,
                job: (CoroutineScope) -> Unit
            ) {
                if(replaceOldJobWithSameKey){
                    ScopeMainImmediateJobs[jobKeyToSave]?.cancel()
                    ScopeMainImmediateJobs.remove(jobKeyToSave)
                }

                if(!ScopeMainImmediateJobs.containsKey(jobKeyToSave)) {
                    ScopeMainImmediateJobs[jobKeyToSave] = ScopeMainImmediate.launch {
                        job.invoke(this)
                    }

                    if(deleteOnCompletion) ScopeMainImmediateJobs[jobKeyToSave]!!.invokeOnCompletion {
                        ScopeMainImmediateJobs.remove(jobKeyToSave)
                    }
                }
            }

            fun relaunchMainImmediateScopeJobIfSaved(
                key: String,
                relaunchEvenIfAlreadyActive: Boolean = false,
                deleteOnCompletion: Boolean = true
            ) {
                ScopeMainImmediateJobs[key]?.run {
                    if(relaunchEvenIfAlreadyActive || !isActive) {
                        start()

                        if(deleteOnCompletion) ScopeMainImmediateJobs[key]!!.invokeOnCompletion {
                            ScopeMainImmediateJobs.remove(key)
                        }
                    }
                }
            }

            fun getMainImmediateScopeJob(key: String) = ScopeMainImmediateJobs[key]

            fun cancelMainImmediateScopeJob(key: String, delete: Boolean = true) {
                ScopeMainImmediateJobs[key]?.cancel()

                if(delete) ScopeMainImmediateJobs.remove(key)
            }

            fun getAllMainImmediateScopeJobs() = ScopeMainImmediateJobs

            fun cancelAllMainImmediateScopeJob(delete: Boolean = true) {
                ScopeMainImmediateJobs.forEach {
                    it.value.cancel()
                }

                if(delete) ScopeMainImmediateJobs.clear()
            }

            fun getMainImmediateScope() = ScopeMainImmediate
    /** */
}