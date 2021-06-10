package bogdandonduk.kotlincoroutineswrapperslib

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object KotlinCoroutinesScopes {
    private val ScopeIO = CoroutineScope(IO)
    private val ScopeIOJobs = mutableMapOf<String, Job>()

    fun launchNewIOScopeJob(
        jobKeyToSave: String,
        replaceOldJobWithSameKey: Boolean = false,
        removeOnCompletion: Boolean = true,
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

            if(removeOnCompletion) ScopeIOJobs[jobKeyToSave]!!.invokeOnCompletion {
                ScopeIOJobs.remove(jobKeyToSave)
            }
        }
    }

    fun relaunchIOScopeJobIfSaved(key: String, relaunchEvenIfAlreadyActive: Boolean = true, removeOnCompletion: Boolean = true) {
        ScopeIOJobs[key]?.run {
            if(relaunchEvenIfAlreadyActive || !isActive) {
                start()

                if(removeOnCompletion) ScopeIOJobs[key]!!.invokeOnCompletion {
                    ScopeIOJobs.remove(key)
                }
            }
        }
    }

    fun getIOScopeJob(key: String) = ScopeIOJobs[key]

    fun cancelIOScopeJob(key: String) {
        ScopeIOJobs[key]?.cancel()
        ScopeIO
    }

    fun getAllIOScopeJobs() = ScopeIOJobs

    fun cancelAllIOScopeJob() {
        ScopeIOJobs.forEach {
            it.value.cancel()
        }

        ScopeIOJobs.clear()
    }

    private val ScopeDefault = CoroutineScope(Default)
    private val ScopeDefaultJobs = mutableMapOf<String, Job>()

    fun launchNewDefaultScopeJob(
        jobKeyToSave: String,
        replaceOldJobWithSameKey: Boolean = false,
        removeOnCompletion: Boolean = true,
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

            if(removeOnCompletion) ScopeDefaultJobs[jobKeyToSave]!!.invokeOnCompletion {
                ScopeDefaultJobs.remove(jobKeyToSave)
            }
        }
    }

    fun relaunchDefaultScopeJobIfSaved(key: String, relaunchEvenIfAlreadyActive: Boolean = true, removeOnCompletion: Boolean = true) {
        ScopeDefaultJobs[key]?.run {
            if(relaunchEvenIfAlreadyActive || !isActive) {
                start()

                if(removeOnCompletion) ScopeIOJobs[key]!!.invokeOnCompletion {
                    ScopeDefaultJobs.remove(key)
                }
            }
        }
    }

    fun getDefaultScopeJob(key: String) = ScopeDefaultJobs[key]

    fun cancelDefaultScopeJob(key: String) {
        ScopeDefaultJobs[key]?.cancel()
    }

    fun getAllDefaultScopeJobs() = ScopeDefaultJobs

    fun cancelAllDefaultScopeJob() {
        ScopeDefaultJobs.forEach {
            it.value.cancel()
        }

        ScopeDefaultJobs.clear()
    }

    private val ScopeMain = CoroutineScope(Main)
    private val ScopeMainJobs = mutableMapOf<String, Job>()

    fun launchNewMainScopeJob(
        jobKeyToSave: String,
        replaceOldJobWithSameKey: Boolean = false,
        removeOnCompletion: Boolean = true,
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

            if(removeOnCompletion) ScopeMainJobs[jobKeyToSave]!!.invokeOnCompletion {
                ScopeMainJobs.remove(jobKeyToSave)
            }
        }
    }

    fun relaunchMainScopeJobIfSaved(key: String, relaunchEvenIfAlreadyActive: Boolean = true, removeOnCompletion: Boolean = true) {
        ScopeMainJobs[key]?.run {
            if(relaunchEvenIfAlreadyActive || !isActive) {
                start()

                if(removeOnCompletion) ScopeMainJobs[key]!!.invokeOnCompletion {
                    ScopeMainJobs.remove(key)
                }
            }
        }
    }

    fun getMainScopeJob(key: String) = ScopeMainJobs[key]

    fun cancelMainScopeJob(key: String) {
        ScopeMainJobs[key]?.cancel()
    }

    fun getAllMainScopeJobs() = ScopeMainJobs

    fun cancelAllMainScopeJob() {
        ScopeMainJobs.forEach {
            it.value.cancel()
        }

        ScopeMainJobs.clear()
    }

    private val ScopeMainImmediate = CoroutineScope(Main.immediate)
    private val ScopeMainImmediateJobs = mutableMapOf<String, Job>()

    fun launchNewMainImmediateScopeJob(
        jobKeyToSave: String,
        replaceOldJobWithSameKey: Boolean = false,
        removeOnCompletion: Boolean = true,
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

            if(removeOnCompletion) ScopeMainImmediateJobs[jobKeyToSave]!!.invokeOnCompletion {
                ScopeMainImmediateJobs.remove(jobKeyToSave)
            }
        }
    }

    fun relaunchMainImmediateScopeJobIfSaved(key: String, relaunchEvenIfAlreadyActive: Boolean = true, removeOnCompletion: Boolean = true) {
        ScopeMainImmediateJobs[key]?.run {
            if(relaunchEvenIfAlreadyActive || !isActive) {
                start()

                if(removeOnCompletion) ScopeMainImmediateJobs[key]!!.invokeOnCompletion {
                    ScopeMainImmediateJobs.remove(key)
                }
            }
        }
    }

    fun getMainImmediateScopeJob(key: String) = ScopeMainJobs[key]

    fun cancelMainImmediateScopeJob(key: String) {
        ScopeMainImmediateJobs[key]?.cancel()
    }

    fun getAllMainImmediateScopeJobs() = ScopeMainImmediateJobs

    fun cancelAllMainImmediateScopeJob() {
        ScopeMainImmediateJobs.forEach {
            it.value.cancel()
        }

        ScopeMainImmediateJobs.clear()
    }
}